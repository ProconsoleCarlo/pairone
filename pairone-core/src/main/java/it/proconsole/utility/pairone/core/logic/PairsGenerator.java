package it.proconsole.utility.pairone.core.logic;

import it.proconsole.utility.pairone.core.model.Sprint;

import java.util.List;

public interface PairsGenerator {
  List<Sprint> generateFor(Long teamId);
}
