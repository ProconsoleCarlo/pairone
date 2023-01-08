package it.proconsole.utility.pairone.core.model;

import org.springframework.lang.Nullable;

import java.util.List;

public record Team(@Nullable Long id, String name, List<Developer> developers) {

}
