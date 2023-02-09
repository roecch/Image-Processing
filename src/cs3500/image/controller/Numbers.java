package cs3500.image.controller;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A Numbers class to represent all the numbers from 0 - 99 by its string and value.
 */

public class Numbers {

  private static final HashMap<String, Integer> numbers = new HashMap<String, Integer>();

  static {
    numbers.put("one", 1);
    numbers.put("two", 2);
    numbers.put("three", 3);
    numbers.put("four", 4);
    numbers.put("five", 5);
    numbers.put("six", 6);
    numbers.put("seven", 7);
    numbers.put("eight", 8);
    numbers.put("nine", 9);
    numbers.put("ten", 10);
    numbers.put("eleven", 11);
    numbers.put("twelve", 12);
    numbers.put("thirteen", 13);
    numbers.put("fourteen", 14);
    numbers.put("fifteen", 15);
    numbers.put("sixteen", 16);
    numbers.put("seventeen", 17);
    numbers.put("eighteen", 18);
    numbers.put("nineteen", 19);
    numbers.put("twenty", 20);
    numbers.put("thirty", 30);
    numbers.put("forty", 40);
    numbers.put("fifty", 50);
    numbers.put("sixty", 60);
    numbers.put("seventy", 70);
    numbers.put("eighty", 80);
    numbers.put("ninety", 90);
  }

  /**
   * Converts the written number from of the string to an integer.
   *
   * @param num number written in String form
   * @return the integer form of the given number
   */

  public static int getWordToNum(String num) {
    Scanner sc = new Scanner(num.replace("-", " "));
    int intNum = 0;
    while (sc.hasNext()) {
      try {
        intNum += numbers.get(sc.next());
      }
      catch (NullPointerException e) {
        throw new IllegalArgumentException();
      }
    }
    return intNum;
  }

}