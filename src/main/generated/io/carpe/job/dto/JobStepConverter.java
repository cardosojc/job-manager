package io.carpe.job.dto;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.carpe.job.dto.JobStep}.
 * NOTE: This class has been automatically generated from the {@link io.carpe.job.dto.JobStep} original class using Vert.x codegen.
 */
public class JobStepConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, JobStep obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "code":
          if (member.getValue() instanceof String) {
            obj.setCode((String)member.getValue());
          }
          break;
        case "description":
          if (member.getValue() instanceof String) {
            obj.setDescription((String)member.getValue());
          }
          break;
      }
    }
  }

  public static void toJson(JobStep obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(JobStep obj, java.util.Map<String, Object> json) {
    if (obj.getCode() != null) {
      json.put("code", obj.getCode());
    }
    if (obj.getDescription() != null) {
      json.put("description", obj.getDescription());
    }
  }
}
