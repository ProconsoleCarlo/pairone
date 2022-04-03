package it.proconsole.scheduler.model;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Optional;

public class Match<P> {
  private final Long id;
  private final P firstPlayer;
  @Nullable
  private final P secondPlayer;

  public Match(Long id, P firstPlayer, @Nullable P secondPlayer) {
    this.id = id;
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }

  public Match(Long id, P firstPlayer) {
    this(id, firstPlayer, null);
  }

  public Long id() {
    return id;
  }

  public P firstPlayer() {
    return firstPlayer;
  }

  public Optional<P> secondPlayer() {
    return Optional.ofNullable(secondPlayer);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Match)) return false;
    Match<?> match = (Match<?>) o;
    return id.equals(match.id) && firstPlayer.equals(match.firstPlayer) && Objects.equals(secondPlayer, match.secondPlayer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstPlayer, secondPlayer);
  }
}
