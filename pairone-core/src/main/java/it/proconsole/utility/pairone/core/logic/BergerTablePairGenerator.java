package it.proconsole.utility.pairone.core.logic;

import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BergerTablePairGenerator implements PairsGenerator {
  private final DeveloperRepository developerRepository;

  public BergerTablePairGenerator(DeveloperRepository developerRepository) {
    this.developerRepository = developerRepository;
  }

  @Override
  public List<Sprint> generateFor(Long teamId) {
    var developers = developerRepository.findByTeamId(teamId);
    var a = new ArrayList<>(developers);
    if (a.size() % 2 != 0) {
      a.add(new Developer(99L, "DUMMY"));
    }

    var n = a.size();
    var numberOfRounds = n - 1;
    var gamesPerRound = n / 2;

    var columnA = new LinkedList<>(a.subList(0, gamesPerRound));
    var columnB = new LinkedList<>(a.subList(gamesPerRound, a.size()));
    var fixed = a.get(0);

    var acc = new ArrayList<Sprint>();
    for (int round = 0; round < numberOfRounds; round++) {
      var gameCount = 1;
      for (int i = 0; i < gamesPerRound; i++) {
        //if (columnA.get(i).id() != 99L && columnB.get(i).id() != 99L) {
          acc.add(new Sprint((long) (round + 1), (long) gameCount, new Pair("", List.of(columnA.get(i), columnB.get(i)))));
          gameCount++;
        //}
      }

      var newColumnA = new LinkedList<Developer>();
      newColumnA.add(fixed);
      newColumnA.add(columnB.get(0));
      columnB.remove(0);
      newColumnA.addAll(columnA.subList(1, columnA.size()));
      columnA = newColumnA;
      var newColumnB = new LinkedList<>(columnB);
      newColumnB.add(columnA.get(columnA.size()-1));
      columnA.remove(columnA.size()-1);
      columnB = newColumnB;
    }

    return acc;

/*
    return Array.from({length: numberOfRounds}).map((_, i) => {
        let gameCount = 1;
        let round = Array.from({length: gamesPerRound}).reduce((acc, _, k) => {
            if (useDummy || (columnA[k] !== dummy && columnB[k] !== dummy)) {
                acc.push({
                    round: i+1,
                    game: gameCount,
                    teamA: columnA[k],
                    teamB: columnB[k]
                });
                gameCount++;
            }
            return acc;
        }, []);

        // rotate elements
        columnA = [fixed, columnB.shift(), ...columnA.slice(1)];
        columnB.push(columnA.pop());
        return round;
    });



 */
  }
}
