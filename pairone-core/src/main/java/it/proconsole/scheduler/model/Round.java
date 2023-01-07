package it.proconsole.scheduler.model;

import java.util.List;

public record Round<P>(Long id, List<Match<P>> matches) {

}
