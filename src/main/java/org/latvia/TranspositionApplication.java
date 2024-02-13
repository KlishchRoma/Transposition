package org.latvia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.Scanner;
import org.latvia.transposition.Convertor;
import org.latvia.transposition.NoteTransposer;
import org.latvia.transposition.PianoNoteTransposes;
import org.latvia.transposition.RequestReader;

public class TranspositionApplication {

  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();
    Convertor convertor = new Convertor(objectMapper);
    RequestReader requestReader = new RequestReader(new Scanner(System.in), convertor);
    NoteTransposer noteTransposer = new PianoNoteTransposes(objectMapper);

    ArrayNode jsonNodes = noteTransposer.transposeMusic(requestReader.readRequest());
    System.out.println("Transposed music: " + jsonNodes);
  }

}
