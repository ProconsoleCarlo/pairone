package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.exception.EntityNotSavedException;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.TeamAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.model.Team;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class DatastoreTeamRepository implements TeamRepository {
  private final TeamEntityRepository teamEntityRepository;
  private final DeveloperEntityRepository developerEntityRepository;
  private final TeamAdapter teamAdapter;
  private final DeveloperAdapter developerAdapter;

  public DatastoreTeamRepository(
          TeamEntityRepository teamEntityRepository,
          DeveloperEntityRepository developerEntityRepository,
          TeamAdapter teamAdapter,
          DeveloperAdapter developerAdapter
  ) {
    this.teamEntityRepository = teamEntityRepository;
    this.developerEntityRepository = developerEntityRepository;
    this.teamAdapter = teamAdapter;
    this.developerAdapter = developerAdapter;
  }

  @Override
  public List<Team> findAll() {
    throw new NotImplementedException();
  }

  @Override
  public Optional<Team> findById(Long id) {
    return teamEntityRepository.findById(id)
            .map(it -> teamAdapter.toDomain(it, developerEntityRepository.findByTeamId(id)));
  }

  @Override
  public Team save(Team team) {
    var teamEntity = teamAdapter.fromDomain(team);
    var savedTeam = teamEntityRepository.save(teamEntity);
    var savedDevelopers = Optional.ofNullable(savedTeam.getId())
            .map(teamId -> {
                      var developerEntities = developerAdapter.fromDomain(team.developers(), savedTeam.getId());
                      return developerEntityRepository.saveAll(developerEntities);
                    }
            ).orElseThrow(() -> new EntityNotSavedException("TeamEntity"));
    return teamAdapter.toDomain(savedTeam, savedDevelopers);
  }
}
