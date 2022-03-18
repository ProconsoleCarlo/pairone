package it.proconsole.utility.pairone.adapter.datastore.exception;

public class EntityNotSavedException extends RuntimeException {
  public EntityNotSavedException(String entityName) {
    super("Entity not saved on DB " + entityName);
  }
}
