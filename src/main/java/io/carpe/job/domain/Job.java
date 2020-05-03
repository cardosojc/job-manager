package io.carpe.job.domain;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public abstract class Job {
  private UUID id;

  private String code;

  private String description;

  private Map<JobState, OffsetDateTime> stateList;

  abstract void run();

  abstract void cancel();
}
