package it.proconsole.scheduler.model;

import org.springframework.lang.Nullable;

public record Match<P>(Long id, P firstPlayer, @Nullable P secondPlayer) {
  public Match(Long id, P firstPlayer) {
    this(id, firstPlayer, null); //NOSONAR issue with record
  }
}
