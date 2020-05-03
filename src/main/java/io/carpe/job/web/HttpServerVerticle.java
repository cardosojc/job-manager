package io.carpe.job.web;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.healthchecks.Status;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.healthchecks.HealthCheckHandler;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;

public class HttpServerVerticle extends AbstractVerticle {
  Logger logger = LoggerFactory.getLogger(HttpServerVerticle.class);

  @Override
  public void start(Promise<Void> promise) {
    OpenAPI3RouterFactory
      .rxCreate(vertx, "/jobManager-spec.yaml")
      .flatMap(routerFactory -> {
        // Add an handler with operationId
        routerFactory.mountServicesFromExtensions();

        // Add a security handler
        //TODO: still to implement
        //routerFactory.addSecurityHandler("api_key", JWTAuthHandler.create(jwtAuth));

        // Now you have to generate the router
        Router router = routerFactory.getRouter();

        //Health check
        HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);
        healthCheckHandler.register("job-manager-health-check", future -> future.complete(Status.OK()));
        router.get("/health").handler(healthCheckHandler);

        // Now you can use your Router instance
        HttpServer server = vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost("localhost"));
        return server.requestHandler(router).rxListen().doOnError(throwable -> logger.error("ERROR :" + throwable));
      }).subscribe(httpServer -> logger.info("http server running"), throwable -> logger.error("error starting http server - " + throwable.getMessage()));
  }
}
