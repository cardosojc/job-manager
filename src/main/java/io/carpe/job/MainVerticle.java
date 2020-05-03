package io.carpe.job;

import io.carpe.job.engine.JobDataManagerVerticle;
import io.carpe.job.engine.JobServiceManagerVerticle;
import io.carpe.job.web.HttpServerVerticle;
import io.reactivex.Single;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.RxHelper;

public class MainVerticle extends AbstractVerticle {
  public static void main(final String[] args) {
    Launcher.executeCommand("run", MainVerticle.class.getName());
  }

  @Override
  public void start(Promise<Void> start) {
    Logger logger = LoggerFactory.getLogger(MainVerticle.class);
    Single<String> httpDeployment = RxHelper.deployVerticle(vertx, new HttpServerVerticle());
    httpDeployment.subscribe(id -> logger.info("HttpServerVerticle deployed with success - " + id), err -> logger.info("HttpServerVerticle deployment error - " + err));
    Single<String> serviceManagerDeployment = RxHelper.deployVerticle(vertx, new JobServiceManagerVerticle());
    serviceManagerDeployment.subscribe(id -> logger.info("JobServiceManagerVerticle deployed with success - " + id), err -> logger.info("JobManagerServiceVertice deployment error - " + err));
    Single<String> dataManagerDeployment = RxHelper.deployVerticle(vertx, new JobDataManagerVerticle());
    dataManagerDeployment.subscribe(id -> logger.info("JobDataManagerVerticle deployed with success - " + id), err -> logger.info("JobDataManagerVerticle deployment error - " + err));
  }
}
