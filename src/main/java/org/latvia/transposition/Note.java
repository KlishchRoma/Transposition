package org.latvia.transposition;

public record Note(
    int octaveNumber,
    int noteNumber) {
  public Note {
    if (octaveNumber < -3 || octaveNumber > 5) {
      throw new IllegalArgumentException("Octave number must be between -3 and 5");
    }
    if (noteNumber < 1 || noteNumber > 11) {
      throw new IllegalArgumentException("Note number must be between 0 and 11");
    }
  }
}
