package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Developer;

import java.util.List;

public interface DeveloperRepository {
  List<Developer> findAll();

  List<Developer> saveAll(List<Developer> developers);
}
