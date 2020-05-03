package io.carpe.job.dto;

import io.carpe.job.domain.JobState;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@DataObject(generateConverter = true)
public class JobEvents {
  UUID id;
  String code;
  Integer currentStep;
  List<JobState> jobStates;

  public JobEvents() {
  }

  public JobEvents(JsonObject jsonObject) {
    JobEventsConverter.fromJson(jsonObject, this);
  }

  public JsonObject toJson() {
    return JsonObject.mapFrom(this);
  }

}
