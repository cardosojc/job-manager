package io.carpe.job.engine;

import io.carpe.job.dto.JobRegisterRequest;
import io.carpe.job.dto.JobRegisterResponse;
import io.carpe.job.dto.JobStep;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Tuple;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobDataManagerVerticle extends AbstractVerticle {
  private final Logger logger = LoggerFactory.getLogger(JobDataManagerVerticle.class);
  private final PgPool dbClient = getDbClient();

  @Override
  public void start(Promise<Void> promise) {

    EventBus eventBus = vertx.eventBus();
    eventBus.consumer("job.register", request -> registerJob(request));
    eventBus.consumer("job.getallevents", request -> registerJob(request));
  }

  private PgPool getDbClient() {
    PgConnectOptions connectOptions = new PgConnectOptions()
      .setPort(5432)
      .setHost("localhost")
      .setDatabase("carpe_job")
      .setUser("carpe")
      .setPassword("carpe");
    PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
    return PgPool.pool(vertx, connectOptions, poolOptions);
  }

  private void getAllJobEvents(String code) {
    //TODO
  }

  private void registerJob(Message<Object> request) {
    JobRegisterRequest jobRegisterRequest = new JobRegisterRequest(JsonObject.mapFrom(request.body()));
    JobRegisterResponse jobRegisterResponse = new JobRegisterResponse();
    // Various statements
    dbClient.preparedQuery("INSERT INTO jobs (code,description,created_date) VALUES ($1,$2,$3) RETURNING created_date, id")
      .execute(Tuple.of(jobRegisterRequest.getCode(), jobRegisterRequest.getDescription(), OffsetDateTime.now()), ar -> {
        if (ar.succeeded()) {
          jobRegisterResponse.setCreationDate(ar.result().iterator().next().getOffsetDateTime("created_date"));
          UUID jobId = ar.result().iterator().next().getUUID("id");
          List<Tuple> batch = new ArrayList<>();
          for (JobStep jobStep : jobRegisterRequest.getSteps()) {
            batch.add(Tuple.of(jobStep.getCode(), jobStep.getDescription(), jobId));
          }
          dbClient.preparedQuery("INSERT INTO job_steps (code,description,job_id) VALUES ($1,$2,$3)").executeBatch(batch, res -> {
            if (res.succeeded()) {
              jobRegisterResponse.setSuccess(true);
              request.reply(jobRegisterResponse.toJson());
            } else {
              logger.error("Batch failed " + res.cause().getMessage());
              request.reply(res.cause());
            }
          });
        } else {
          logger.error(ar.cause());
        }
      });
  }
}
