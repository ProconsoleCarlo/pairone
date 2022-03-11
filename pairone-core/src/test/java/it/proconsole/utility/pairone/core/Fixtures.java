package it.proconsole.utility.pairone.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Fixtures {
  private static final ObjectMapper objectMapper = new ObjectMapper()
          .findAndRegisterModules()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

  public static ObjectMapper springObjectMapper() {
    return objectMapper;
  }

  public static String readFromClasspath(String path) {
    try {
      return new String(Objects.requireNonNull(Fixtures.class.getResourceAsStream(path)).readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T readFromClasspath(String path, Class<T> clazz) {
    try {
      return objectMapper.readValue(Fixtures.class.getResourceAsStream(path), clazz);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> List<T> readListFromClasspath(String path, Class<T> clazz) {
    try {
      var type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
      return objectMapper.readValue(Fixtures.class.getResourceAsStream(path), type);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
