package io.carpe.job.dto;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@DataObject(generateConverter = true)
public class JobRegisterResponse {
  Boolean success;
  OffsetDateTime creationDate;

  public JobRegisterResponse() {
  }

  public JobRegisterResponse(JsonObject jsonObject) {
    JobRegisterResponseConverter.fromJson(jsonObject, this);
  }

  public JsonObject toJson() {
    return JsonObject.mapFrom(this);
  }

}
