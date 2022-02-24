package env;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentConsumer {
  public static Dotenv getInstance(String settings) {
    return Dotenv.configure().load();
  }
}
