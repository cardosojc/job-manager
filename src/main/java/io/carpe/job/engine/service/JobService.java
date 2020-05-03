package io.carpe.job.engine.service;

import io.carpe.job.dto.JobRegisterRequest;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

@ProxyGen
@WebApiServiceGen
public interface JobService {
  @GenIgnore
  static JobService create(Vertx vertx, Handler<AsyncResult<JobService>> readyHandler) {
    return new JobServiceImpl(vertx, readyHandler);
  }

  @Fluent
  JobService getAllJobEvents(String code, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

  @Fluent
  JobService registerJob(JobRegisterRequest body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);
}
