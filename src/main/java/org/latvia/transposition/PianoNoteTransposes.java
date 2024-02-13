package org.latvia.transposition;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import org.latvia.exception.OutOfRangeOctaveException;

public class PianoNoteTransposes extends NoteTransposer {

  private final int MAX_OCTAVE_NUMBER = 5;
  private final int MAX_NOTE_NUMBER = 1;
  private final int MIN_OCTAVE_NUMBER = -3;
  private final int MIN_NOTE_NUMBER = 10;

  public PianoNoteTransposes(ObjectMapper objectMapper) {
    super(objectMapper);
  }

  protected void validate(int octave, int noteNumber) {
    if ((octave < MIN_OCTAVE_NUMBER || octave > MAX_OCTAVE_NUMBER)
        || (octave == MIN_OCTAVE_NUMBER && noteNumber < MAX_NOTE_NUMBER)
        || (octave == MAX_OCTAVE_NUMBER && noteNumber > MIN_NOTE_NUMBER)
    ) {
      logger.log(Level.INFO, "Octave number must be between -3 and 5 and note number between 1 and 11");
      throw new OutOfRangeOctaveException("Out of range {}", octave);
    }
  }
}
