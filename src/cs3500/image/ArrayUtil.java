package cs3500.image;

import java.awt.Point;
import java.util.List;

public class ArrayUtil {

  public static String toStringList(List<Point> list) {
    String result = "";

    for (Point p : list) {
      result += p.x + ", " + p.y + "   ";
    }
    return result + "\n\n";
  }

}
