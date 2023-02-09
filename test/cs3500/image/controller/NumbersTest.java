package cs3500.image.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Testing class for Numbers.
 */

public class NumbersTest {

  @Test
  public void getWordToNum0() {
    assertEquals(0, Numbers.getWordToNum("zero"));
  }

  @Test
  public void getWordToNum1() {
    assertEquals(1, Numbers.getWordToNum("one"));
  }


  @Test
  public void getWordToNumDoubleDigit() {
    assertEquals(20, Numbers.getWordToNum("twenty"));
  }

  @Test
  public void getWordToNumCombined() {
    assertEquals(22, Numbers.getWordToNum("twenty two"));
  }

  @Test
  public void getWordToNumCombinedWithHyphen() {
    assertEquals(64, Numbers.getWordToNum("sixty-four"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void getWordToNumInvalid() {
    Numbers.getWordToNum("twentjdweowo");
  }
}