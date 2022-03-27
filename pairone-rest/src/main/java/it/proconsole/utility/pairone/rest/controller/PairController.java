package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.repository.PairRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PairController {
  private final PairRepository pairRepository;

  public PairController(PairRepository datastorePairRepository) {
    this.pairRepository = datastorePairRepository;
  }

  @GetMapping("/team/{teamId}/pair")
  public List<Pair> getPairs(@PathVariable Long teamId) {
    return pairRepository.findByTeamId(teamId);
  }

  @PostMapping("/team/{teamId}/pair")
  public List<Pair> savePairs(@PathVariable Long teamId, @RequestBody List<Pair> pairs) {
    return pairRepository.saveAll(teamId, pairs);
  }
}