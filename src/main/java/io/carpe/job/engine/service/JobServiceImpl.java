package io.carpe.job.engine.service;

import io.carpe.job.dto.JobRegisterRequest;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

public class JobServiceImpl implements JobService {
  private final Vertx vertx;

  public JobServiceImpl(Vertx vertx, Handler<AsyncResult<JobService>> readyHandler) {
    this.vertx = vertx;
    readyHandler.handle(Future.succeededFuture(this));
  }

  @Override
  public JobService getAllJobEvents(String code, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
    vertx.eventBus().request("job.getallevents", code, response -> resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(response.result().body())))));
    return this;
  }

  @Override
  public JobService registerJob(JobRegisterRequest jobRegisterRequest, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
    vertx.eventBus().request("job.register", jobRegisterRequest.toJson(), response -> resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(response.result().body())))));
    return null;
  }

  /*
  @Override
  public JobService triggerJob(code){
    //Creates a new instance of the jobs and starts it. It will deploy the corresponding verticles (will contain a handler for each job step) based on job code/ verticle name mapping
  }
  @Override
  public JobService cancelJob(code){
    //Creates a new instance of the jobs and starts it. It will deploy the corresponding verticles (will contain a handler for each job step) based on job code/ verticle name mapping
  }
  */
}
