package it.proconsole.utility.pairone.adapter.datastore.exception;

import org.springframework.lang.Nullable;

public class EntityNotFoundException extends RuntimeException {
  private EntityNotFoundException(String message) {
    super(message);
  }

  public static EntityNotFoundException forTeam(@Nullable Long teamId) {
    return new EntityNotFoundException("Team not found for id " + teamId);
  }
}
