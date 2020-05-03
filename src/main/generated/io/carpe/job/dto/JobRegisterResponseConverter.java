package io.carpe.job.dto;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.carpe.job.dto.JobRegisterResponse}.
 * NOTE: This class has been automatically generated from the {@link io.carpe.job.dto.JobRegisterResponse} original class using Vert.x codegen.
 */
public class JobRegisterResponseConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, JobRegisterResponse obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
      }
    }
  }

  public static void toJson(JobRegisterResponse obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(JobRegisterResponse obj, java.util.Map<String, Object> json) {
  }
}
