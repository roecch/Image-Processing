package cs3500.image.model;

import static org.junit.Assert.assertEquals;
import java.awt.Color;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SharpenFilter: Ensures that the methods in the
 * SharpenFilter class are behaving correctly.
 */

public class SharpenFilterTest {
  AFilter sharpen = new SharpenFilter();
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
   * Tests sharpen apply for ability to effect image of some non-zero RGB.
   */

  @Test
  public void sharpenApplyOnFullBlue() {
    sharpen.apply(fullBlue);
    IPixel blue = new PixelImpl(0, 0, 255);
    IPixel[][] postApplyFullBlue
        = new IPixel[][]{{blue, blue, blue},
          {blue, blue, blue}, {blue, blue, blue}};

    boolean checkIfAllAppliedCorrectly = true;
    for (int x = 0; x < fullBlue.getPixels().length; x++) {
      for (int y = 0; y < fullBlue.getPixels()[x].length; y++) {
        checkIfAllAppliedCorrectly &= fullBlue.getPixels()[x][y].equals(postApplyFullBlue[x][y]);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests sharpen apply for ability to effect image of all zero RGB.
   */

  @Test
  public void sharpenApplyOnFullBlack() {
    sharpen.apply(fullBlack);
    IPixel black = new PixelImpl(0, 0, 0);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: fullBlack.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(black);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests sharpen apply for ability to effect image of all max RGB.
   */

  @Test
  public void sharpenApplyOnFullWhite() {
    sharpen.apply(fullWhite);
    IPixel white = new PixelImpl(255, 255, 255);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: fullWhite.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(white);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests sharpen apply for ability to effect image of all non-zero RGB.
   */

  @Test
  public void sharpenApplyOnFullDark() {
    sharpen.apply(dark);
    IPixel darkLeftCorner = new PixelImpl(13, 4, 11);
    IPixel darkRightCorner = new PixelImpl(11, 3, 10);
    IPixel darkInnerLeft = new PixelImpl(17, 5, 14);
    IPixel darkInnerRight = new PixelImpl(15, 4, 13);
    IPixel darkMidTopBttm = new PixelImpl(11, 2, 11);
    IPixel darkMidLeft = new PixelImpl(24, 7, 19);
    IPixel darkMidRight = new PixelImpl(22, 6, 18);
    IPixel darkMid = new PixelImpl(16, 3, 15);

    IPixel[][] postApplyDark = new IPixel[][]{
        {darkLeftCorner, darkInnerLeft, darkMidTopBttm, darkInnerRight, darkRightCorner},
        {darkInnerLeft, darkMidLeft, darkMid, darkMidRight, darkInnerRight},
        { new PixelImpl(13, 3, 12),  new PixelImpl(18, 4, 16),
            new PixelImpl(8, 0, 11),  new PixelImpl(14, 2, 14),  new PixelImpl(9, 1, 10)},
        {darkInnerLeft, darkMidLeft, darkMid, darkMidRight, darkInnerRight},
        {darkLeftCorner, darkInnerLeft, darkMidTopBttm, darkInnerRight, darkRightCorner}};

    System.out.println(Arrays.deepToString(dark.getPixels()));

    boolean checkIfAllAppliedCorrectly = true;
    for (int x = 0; x < dark.getPixels().length; x++) {
      for (int y = 0; y < dark.getPixels()[x].length; y++) {
        checkIfAllAppliedCorrectly &= dark.getPixels()[x][y].equals(postApplyDark[x][y]);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }
}