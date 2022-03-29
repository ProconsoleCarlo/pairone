package it.proconsole.scheduler.logic;

import it.proconsole.scheduler.model.Match;
import it.proconsole.scheduler.model.Round;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CircleRoundRobinScheduler<P> implements Scheduler<P> {
  @Override
  public List<Round<P>> scheduleFor(List<P> players) {
    var allPlayers = new LinkedList<>(players);
    if (areOdd(allPlayers)) {
      allPlayers.add(null);
    }
    return getRoundsFor(allPlayers);
  }

  private boolean areOdd(List<P> players) {
    return players.size() % 2 != 0;
  }

  private List<Round<P>> getRoundsFor(List<P> players) {
    var numberOfRounds = players.size() - 1;
    return IntStream.range(0, numberOfRounds).boxed()
            .map(round -> new Round<>((long) round + 1, getMatchesFor(players)))
            .collect(Collectors.toList());
  }

  private List<Match<P>> getMatchesFor(List<P> players) {
    var matchesPerRound = players.size() / 2;
    var matches = IntStream.range(0, matchesPerRound).boxed()
            .map(match -> {
              var matchId = (long) match + 1;
              var firstPlayer = players.get(match);
              var secondPlayer = players.get(players.size() - match - 1);
              if (firstPlayer == null) {
                return new Match<>(matchId, secondPlayer);
              } else if (secondPlayer == null) {
                return new Match<>(matchId, firstPlayer);
              } else {
                return new Match<>(matchId, firstPlayer, secondPlayer);
              }
            }).collect(Collectors.toList());
    Collections.rotate(players.subList(1, players.size()), 1);
    return matches;
  }
}
