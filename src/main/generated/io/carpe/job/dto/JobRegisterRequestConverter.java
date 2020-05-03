package io.carpe.job.dto;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.carpe.job.dto.JobRegisterRequest}.
 * NOTE: This class has been automatically generated from the {@link io.carpe.job.dto.JobRegisterRequest} original class using Vert.x codegen.
 */
public class JobRegisterRequestConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, JobRegisterRequest obj) {
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
        case "steps":
          if (member.getValue() instanceof JsonArray) {
            java.util.ArrayList<io.carpe.job.dto.JobStep> list =  new java.util.ArrayList<>();
            ((Iterable<Object>)member.getValue()).forEach( item -> {
              if (item instanceof JsonObject)
                list.add(new io.carpe.job.dto.JobStep((JsonObject)item));
            });
            obj.setSteps(list);
          }
          break;
      }
    }
  }

  public static void toJson(JobRegisterRequest obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(JobRegisterRequest obj, java.util.Map<String, Object> json) {
    if (obj.getCode() != null) {
      json.put("code", obj.getCode());
    }
    if (obj.getDescription() != null) {
      json.put("description", obj.getDescription());
    }
    if (obj.getSteps() != null) {
      JsonArray array = new JsonArray();
      obj.getSteps().forEach(item -> array.add(item.toJson()));
      json.put("steps", array);
    }
  }
}
