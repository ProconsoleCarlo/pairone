package it.proconsole.utility.pairone.rest.exception;

public class TeamNotFoundException extends RuntimeException{
  public TeamNotFoundException(Long teamId) {
    super("Team not found for id " + teamId);
  }
}
