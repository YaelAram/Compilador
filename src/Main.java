import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class Main {
  public static void main(String[] args) {
    String inputFile = "test.txt";

    try {
      System.out.println("Iniciando Analizador Lexico...");

      String tokens = "";
      String data = new String(Files.readAllBytes(Paths.get(inputFile))).replaceAll("[ \t" + System.lineSeparator() + "]+", " ");

      Optional<String> tokensOpt = Arrays.stream(data.split(" ")).map((palabra) -> {
        String palabraClave = palabra.toLowerCase();
        if (palabraClave.matches("(crear|input|titulo|con|fin|btn)")) return "<" + palabraClave.toUpperCase() + ">";
        else if (palabraClave.matches("[a-z]{2,}=[a-z-]+")) return "<PROP," + palabra + ">";
        return "";
      }).reduce((palabra1, palabra2) -> palabra1 + palabra2);

      if (tokensOpt.isPresent()) tokens = tokensOpt.get();

      System.out.println(tokens);
    } catch (IOException error) {
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }
}