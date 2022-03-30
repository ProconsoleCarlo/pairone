package it.proconsole.utility.pairone.adapter.datastore.exception;

import it.proconsole.utility.pairone.adapter.datastore.model.SprintEntity;
import it.proconsole.utility.pairone.core.model.Team;

public class EntityNotSavedException extends RuntimeException {
  private EntityNotSavedException(String message) {
    super(message);
  }

  public static EntityNotSavedException forTeam(Team team) {
    return new EntityNotSavedException("Team not saved on DB: team name " + team.name());
  }

  public static EntityNotSavedException forSprint(SprintEntity sprint) {
    return new EntityNotSavedException("Sprint not saved on DB: " + sprint.getId());
  }

  public static EntityNotSavedException forSprint() {
    return new EntityNotSavedException("Sprint not saved on DB");
  }
}
