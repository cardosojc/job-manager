package io.carpe.job.dto;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.carpe.job.dto.JobEvents}.
 * NOTE: This class has been automatically generated from the {@link io.carpe.job.dto.JobEvents} original class using Vert.x codegen.
 */
public class JobEventsConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, JobEvents obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
      }
    }
  }

  public static void toJson(JobEvents obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(JobEvents obj, java.util.Map<String, Object> json) {
  }
}
