package io.carpe.job.dto;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class JobStep {
  private String code;
  private String description;

  public JobStep() {
  }

  public JobStep(JsonObject jsonObject) {
    JobStepConverter.fromJson(jsonObject, this);
  }

  public JsonObject toJson() {
    return JsonObject.mapFrom(this);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
