package org.latvia.transposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.latvia.exception.UnreadableJsonException;

public class Convertor {
  Logger logger = Logger.getLogger(getClass().getName());

  private final ObjectMapper objectMapper;

  public Convertor(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public List<Note> readJsonArrayFromString(String jsonString) {
    ArrayList<Note> notes = new ArrayList<>();
    try {
      objectMapper.readTree(jsonString)
          .elements()
          .forEachRemaining(jsonNode -> notes.add(convertToNote(jsonNode)));
    } catch (JsonProcessingException e) {
      logger.log(Level.INFO, "Error while reading JSON array from string", e);
      throw new UnreadableJsonException("Unexpected json format {}", jsonString);
    }
    return notes;
  }

  private Note convertToNote(JsonNode jsonNode) {
    return new Note(jsonNode.get(0).asInt(), jsonNode.get(1).asInt());
  }
}
