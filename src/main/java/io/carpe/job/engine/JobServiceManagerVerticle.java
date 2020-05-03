package io.carpe.job.engine;

import io.carpe.job.engine.service.JobService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.serviceproxy.ServiceBinder;

public class JobServiceManagerVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> promise) {
    JobService.create(vertx, ready -> {
      if (ready.succeeded()) {
        ServiceBinder binder = new ServiceBinder(vertx);
        binder
          .setAddress("job.queue")
          .register(JobService.class, ready.result());
        promise.complete();
      } else {
        promise.fail(ready.cause().fillInStackTrace());
      }
    });
  }
}
