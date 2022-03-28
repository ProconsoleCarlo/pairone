package it.proconsole.utility.pairone.core.logic;

import it.proconsole.utility.pairone.core.model.Pair;

import java.util.List;

public interface PairGenerator {
  List<Pair> generateFor(Long teamId);
}
