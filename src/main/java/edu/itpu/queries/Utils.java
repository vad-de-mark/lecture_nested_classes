package edu.itpu.queries;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public final class Utils {
  
  private Utils() {}

  public static String[][] readCsvFile(String name) {
    try {
      var url = Utils.class.getClassLoader().getResource(name);
      if (url == null) throw new NullPointerException();
      var uri = url.toURI();
      var file = Path.of(uri);
      return Files.lines(file)
          .map(line -> {
            var bldr = Stream.<String>builder();
            try (var sc = new Scanner(line)) {
              sc.useDelimiter(";");
              while (sc.hasNext()) {
                bldr.add(sc.next());
              }
            }
            return bldr.build();
          })
          .map(s -> s.toArray(String[]::new))
          .toArray(String[][]::new);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static void print(QueryResult result) {
    while (result.current() < result.total()) {
      System.out.println(Arrays.toString(result.next()));
    }
  }
}
