package org.latvia.transposition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.latvia.exception.OutOfRangeOctaveException;

public class NoteTransposer {
  Logger logger = Logger.getLogger(getClass().getName());

  private final ObjectMapper objectMapper;

  public NoteTransposer(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public ArrayNode transposeMusic(RequestReader.TranspositionRequest request) {
    ArrayNode transposedMusic = objectMapper.createArrayNode();
    int semitonesToTranspose = request.semitonesToTranspose();
    for (Note note : request.notes()) {
      int noteNumber = note.noteNumber();
      int octave = note.octaveNumber();
      noteNumber += semitonesToTranspose;
      while (noteNumber < 1) {
        noteNumber += 12;
        octave--;
      }
      while (noteNumber > 12) {
        noteNumber -= 12;
        octave++;
      }
      if (octave < -3 || octave > 5) {
        logger.log(Level.INFO, "Octave number must be between -3 and 5");
        throw new OutOfRangeOctaveException("Out of range {}", octave);
      }
      transposedMusic.add(objectMapper.createArrayNode().add(octave).add(noteNumber));
    }
    return transposedMusic;
  }
}
