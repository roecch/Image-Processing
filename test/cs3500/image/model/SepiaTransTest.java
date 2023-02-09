package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SepiaTrans: Ensures that the methods in the SepiaTrans class are behaving correctly.
 */

public class SepiaTransTest {

  AColorTransform sepia = new SepiaTrans();
  IImage fullBlue;
  IImage fullBlack;
  IImage fullWhite;
  IImage dark;

  @Before
  public void setUp() throws Exception {
    Color[] blues = {new Color(0, 0, 255)};
    fullBlue = new SelfImage(9, 1, blues);

    Color[] b = {Color.BLACK};
    fullBlack = new SelfImage(9, 2, b);

    Color[] w = {Color.WHITE};
    fullWhite = new SelfImage(9, 2, w);

    Color[] darks = {new Color(12, 5, 9)};
    dark = new SelfImage(25, 1, darks);
  }

  /**
   * Tests sepia apply for ability to effect image of some non-zero RGB.
   */

  @Test
  public void sepiaFullBlue() {
    sepia.apply(fullBlue);
    IPixel blueAfter = new PixelImpl(48, 43, 33);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : fullBlue.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(blueAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests sepia apply for ability to effect image of all zero RGB.
   */

  @Test
  public void sepiaFullBlack() {
    sepia.apply(fullBlack);
    IPixel blackAfter = new PixelImpl(0, 0, 0);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : fullBlack.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(blackAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests sepia apply for ability to effect image of max RGB.
   */

  @Test
  public void sepiaFullWhite() {
    sepia.apply(fullWhite);
    IPixel whiteAfter = new PixelImpl(255, 255, 239);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : fullWhite.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(whiteAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests sepia apply for ability to effect image of all non-zero RGB.
   */

  @Test
  public void sepiaFullDark() {
    sepia.apply(dark);
    IPixel darkAfter = new PixelImpl(10, 9, 7);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : dark.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(darkAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }
}