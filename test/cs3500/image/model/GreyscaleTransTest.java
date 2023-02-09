package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for GreyscaleTrans: Ensures that the methods in the
 * GreyscaleTrans class are behaving correctly.
 */

public class GreyscaleTransTest {

  AColorTransform grey = new GreyscaleTrans();
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
   * Tests GreyscaleTrans apply for ability to effect image of some non-zero RGB.
   */

  @Test
  public void greyFullBlue() {
    grey.apply(fullBlue);
    IPixel blueAfter = new PixelImpl(18, 18, 18);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: fullBlue.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(blueAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests GreyscaleTrans apply for ability to effect image of all zero RGB.
   */

  @Test
  public void greyFullBlack() {
    grey.apply(fullBlack);
    IPixel blackAfter = new PixelImpl(0, 0, 0);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: fullBlack.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(blackAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests GreyscaleTrans apply for ability to effect image of max RGB.
   */

  @Test
  public void greyFullWhite() {
    grey.apply(fullWhite);
    IPixel whiteAfter = new PixelImpl(255, 255, 255);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: fullWhite.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(whiteAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests GreyscaleTrans apply for ability to effect image of all non-zero RGB.
   */

  @Test
  public void greyFullDark() {
    grey.apply(dark);
    IPixel darkAfter = new PixelImpl(7, 7, 7);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: dark.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(darkAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }
}