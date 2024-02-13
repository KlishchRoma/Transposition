package org.latvia.transposition;



import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestReader {
  Logger logger = Logger.getLogger(getClass().getName());

  private final Scanner scanner;
  private final Convertor convertor;

  public RequestReader(Scanner scanner, Convertor convertor) {
    this.scanner = scanner;
    this.convertor = convertor;
  }

  public TranspositionRequest readRequest() {
    logger.log(Level.INFO,"Enter the JSON array representing the music: ");
    String inputJsonString = scanner.nextLine();

    logger.log(Level.INFO,"Enter the number of semitones to transpose: ");
    int semitonesToTranspose = scanner.nextInt();
    return new TranspositionRequest(
        convertor.readJsonArrayFromString(inputJsonString),
        semitonesToTranspose);
  }

  public record TranspositionRequest(
      List<Note> notes,
      int semitonesToTranspose) {
  }

}
