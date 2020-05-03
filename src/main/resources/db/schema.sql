CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table jobs (
  id uuid default uuid_generate_v4(),
  description text not null,
  code text not null,
  created_date timestamptz not null,
  primary key (id)
);

create table job_steps (
  id uuid default uuid_generate_v4(),
  job_id uuid,
  step_number serial not null,
  code text not null,
  description text not null,
  foreign key (job_id) references jobs (id),
  primary key (id)
);

create table job_states (
  id uuid default uuid_generate_v4(),
  code text not null,
  primary key (id)
);

create table job_instances (
  id uuid default uuid_generate_v4(),
  job_id uuid,
  primary key (id),
  foreign key (job_id) references jobs (id)
);

create table job_instance_events (
  id uuid default uuid_generate_v4(),
  job_instance_id uuid,
  created_date timestamptz not null,
  primary key (id),
  foreign key (job_instance_id) references job_instances (id)
);

create table job_step_events (
  id uuid default uuid_generate_v4(),
  step_id uuid,
  event_id uuid,
  created_date timestamptz,
  primary key (id),
  foreign key (event_id) references job_instance_events (id)
);

create table job_state_events (
  id uuid default uuid_generate_v4(),
  state_id uuid,
  instance_id uuid,
  created_date timestamptz not null,
  primary key (id),
  foreign key (instance_id) references job_instance_events (id),
  foreign key (state_id) references job_states (id)
);
