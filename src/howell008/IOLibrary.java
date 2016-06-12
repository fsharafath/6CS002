package howell008;
import java.io.*;
/**
 * @author Kevan Buckley, maintained by Matthew Howell
 * @version 2.0, 2014
 */

public final class IOLibrary {
  public static String getString() {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    do {
      try {
        return r.readLine();
      } catch (Exception e) {
      }
    } while (true);
  }

}
