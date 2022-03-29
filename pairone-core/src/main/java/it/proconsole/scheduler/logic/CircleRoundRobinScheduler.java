package it.proconsole.scheduler.logic;

import it.proconsole.scheduler.model.Match;
import it.proconsole.scheduler.model.Round;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class CircleRoundRobinScheduler<P> implements Scheduler<P> {
  @Override
  public List<Round<P>> scheduleFor(List<P> a) {
    var players = new LinkedList<>(a);
    if (players.size() % 2 != 0) {
      players.add(null);
    }
    var n = players.size();
    var numberOfRounds = n - 1;
    var gamesPerRound = n / 2;
    var rounds = new LinkedList<Round<P>>();

    IntStream.range(0, numberOfRounds).forEach(i -> {
      var matches = new ArrayList<Match<P>>();
      IntStream.range(0, gamesPerRound).forEach(j -> {
        if (players.get(j) == null) {
          matches.add(new Match<>((long) j + 1, players.get(n - j - 1)));
        } else if (players.get(n - j - 1) == null) {
          matches.add(new Match<>((long) j + 1, players.get(j)));
        } else {
          matches.add(new Match<>((long) j + 1, players.get(j), players.get(n - j - 1)));
        }
      });
      rounds.add(new Round<>((long) (i + 1), matches));
      Collections.rotate(players.subList(1, players.size()), 1);  //last element inserted at index 1
    });
    return rounds;
  }

}
