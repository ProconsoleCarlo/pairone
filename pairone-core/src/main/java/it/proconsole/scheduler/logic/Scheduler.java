package it.proconsole.scheduler.logic;

import it.proconsole.scheduler.model.Round;

import java.util.List;

public interface Scheduler<P> {
  List<Round<P>> scheduleFor(List<P> players);
}
