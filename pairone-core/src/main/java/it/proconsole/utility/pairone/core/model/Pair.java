package it.proconsole.utility.pairone.core.model;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Pair(@Nullable Long id, List<Developer> members) {
  public Pair(List<Developer> members) {
    this(null, members); //NOSONAR issue with record
  }

  public Long firstId() {
    return Objects.requireNonNull(members.get(0).id());
  }

  public Optional<Long> secondId() {
    return members.size() > 1
            ? Optional.ofNullable(members.get(1).id())
            : Optional.empty();
  }
}
