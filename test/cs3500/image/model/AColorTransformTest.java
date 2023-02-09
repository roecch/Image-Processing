package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for AColorTransform: Ensures that the methods in the AColorTransform class are behaving
 * correctly.
 */

public class AColorTransformTest {

  AColorTransform transform = new SepiaTrans();
  IImage fullBlue;
  IImage fullBlack;
  IImage fullWhite;
  IImage dark;

  private static final double[][] RANDOMTRANSFORM =
      {{0.839, 0.5322, 1.34}, {4.532, 0.3432, 0.0342}, {0.8626, 0.1426, 0.0856}};

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
   * Tests applyTransformation for ability to apply matrix to image of some zero RGB.
   */

  @Test
  public void tranFullBlue() {
    transform.applyTransformation(fullBlue, RANDOMTRANSFORM);
    IPixel blueAfter = new PixelImpl(342, 9, 22);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : fullBlue.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(blueAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests applyTransformation for ability to apply matrix to image of all zero RGB.
   */

  @Test
  public void tranFullBlack() {
    transform.applyTransformation(fullBlack, RANDOMTRANSFORM);
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
   * Tests applyTransformation for ability to apply matrix to image of all max RGB.
   */

  @Test
  public void tranFullWhite() {
    transform.applyTransformation(fullWhite, RANDOMTRANSFORM);
    IPixel whiteAfter = new PixelImpl(691, 1252, 278);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : fullWhite.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(whiteAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests applyTransformation for ability to apply matrix to image of all non-zero RGB.
   */

  @Test
  public void tranFullDark() {
    transform.applyTransformation(dark, RANDOMTRANSFORM);
    IPixel darkAfter = new PixelImpl(25, 56, 12);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : dark.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(darkAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }
}