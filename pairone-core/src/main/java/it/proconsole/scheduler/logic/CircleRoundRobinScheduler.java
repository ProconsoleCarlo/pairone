package it.proconsole.scheduler.logic;

import it.proconsole.scheduler.model.Match;
import it.proconsole.scheduler.model.Round;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CircleRoundRobinScheduler<P> implements Scheduler<P> {
  @Override
  public List<Round<P>> scheduleFor(List<P> players) {
    var rounds = new ArrayList<Round<P>>();
    var a = new ArrayList<>(players);
    if (a.size() % 2 != 0) {
      a.add(null);
    }

    var n = a.size();
    var numberOfRounds = n - 1;
    var gamesPerRound = n / 2;

    var columnA = new LinkedList<>(a.subList(0, gamesPerRound));
    var columnB = new LinkedList<>(a.subList(gamesPerRound, a.size()));
    var fixed = a.get(0);

    for (int round = 0; round < numberOfRounds; round++) {
      var matches = new ArrayList<Match<P>>();
      for (int i = 0; i < gamesPerRound; i++) {
        if (columnA.get(i) == null) {
          matches.add(new Match<>((long) i + 1, columnB.get(i)));
        } else if (columnB.get(i) == null) {
          matches.add(new Match<>((long) i + 1, columnA.get(i)));
        } else {
          matches.add(new Match<>((long) i + 1, columnA.get(i), columnB.get(i)));
        }
      }
      rounds.add(new Round<>((long) (round + 1), matches));

      var newColumnA = new LinkedList<P>();
      newColumnA.add(fixed);
      newColumnA.add(columnB.get(0));
      columnB.remove(0);
      newColumnA.addAll(columnA.subList(1, columnA.size()));
      columnA = newColumnA;
      var newColumnB = new LinkedList<>(columnB);
      newColumnB.add(columnA.get(columnA.size() - 1));
      columnA.remove(columnA.size() - 1);
      columnB = newColumnB;
    }
    return rounds;
  }

  private boolean isOdd(int n) {
    return n % 2 != 0;
  }
}
