package org.latvia.transposition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.latvia.exception.OutOfRangeOctaveException;

public abstract class NoteTransposer {
  Logger logger = Logger.getLogger(getClass().getName());

  private final ObjectMapper objectMapper;

  NoteTransposer(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  protected abstract void validate(int octave, int noteNumber);

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
      validate(octave, noteNumber);
      transposedMusic.add(objectMapper.createArrayNode().add(octave).add(noteNumber));
    }
    return transposedMusic;
  }
}
