package io.carpe.job.dto;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.List;

@DataObject(generateConverter = true)
public class JobRegisterRequest {
  private String description;
  private String code;
  private List<JobStep> steps;

  public JobRegisterRequest(JsonObject jsonObject) {
    JobRegisterRequestConverter.fromJson(jsonObject, this);
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

  public List<JobStep> getSteps() {
    return steps;
  }

  public void setSteps(List<JobStep> steps) {
    this.steps = steps;
  }

  public JsonObject toJson() {
    return JsonObject.mapFrom(this);
  }
}
