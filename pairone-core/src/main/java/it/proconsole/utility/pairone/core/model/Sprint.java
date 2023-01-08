package it.proconsole.utility.pairone.core.model;

import org.springframework.lang.Nullable;

import java.util.List;

public record Sprint(@Nullable Long id, Long round, List<Pair> pairs) {
  public Sprint(Long round, List<Pair> pairs) {
    this(null, round, pairs); //NOSONAR issue with record
  }
}
