package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for BlurFilter: Ensures that the methods in the BlurFilter class are behaving correctly.
 */

public class BlurFilterTest {

  AFilter blur = new BlurFilter();
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
   * Tests blur apply for ability to effect image of some non-zero RGB.
   */

  @Test
  public void blurApplyOnFullBlue() {
    blur.apply(fullBlue);
    IPixel blueCorner = new PixelImpl(0, 0, 144);
    IPixel blueMid = new PixelImpl(0, 0, 192);
    IPixel blueCenter = new PixelImpl(0, 0, 255);
    IPixel[][] postApplyFullBlue
        = new IPixel[][]{{blueCorner, blueMid, blueCorner},
          {blueMid, blueCenter, blueMid}, {blueCorner, blueMid, blueCorner}};

    boolean checkIfAllAppliedCorrectly = true;
    for (int x = 0; x < fullBlue.getPixels().length; x++) {
      for (int y = 0; y < fullBlue.getPixels()[x].length; y++) {
        checkIfAllAppliedCorrectly &= fullBlue.getPixels()[x][y].equals(postApplyFullBlue[x][y]);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests blur apply for ability to effect image of all zero RGB.
   */

  @Test
  public void blurApplyOnFullBlack() {
    blur.apply(fullBlack);
    IPixel black = new PixelImpl(0, 0, 0);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow : fullBlack.getPixels()) {
      for (IPixel p : pRow) {
        checkIfAllAppliedCorrectly &= p.equals(black);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests blur apply for ability to effect image of all max RGB.
   */

  @Test
  public void blurApplyOnFullWhite() {
    blur.apply(fullWhite);
    IPixel whiteCorner = new PixelImpl(144, 144, 144);
    IPixel whiteMid = new PixelImpl(192, 192, 192);
    IPixel whiteCenter = new PixelImpl(255, 255, 255);

    IPixel[][] postApplyFullWhite = new IPixel[][]{
        {whiteCorner, whiteMid, whiteMid, whiteMid, whiteMid, whiteCorner},
        {whiteMid, whiteCenter, whiteCenter, whiteCenter, whiteCenter, whiteMid},
        {whiteMid, whiteCenter, whiteCenter, whiteCenter, whiteCenter, whiteMid},
        {whiteMid, whiteCenter, whiteCenter, whiteCenter, whiteCenter, whiteMid},
        {whiteMid, whiteCenter, whiteCenter, whiteCenter, whiteCenter, whiteMid},
        {whiteCorner, whiteMid, whiteMid, whiteMid, whiteMid, whiteCorner}};

    boolean checkIfAllAppliedCorrectly = true;
    for (int x = 0; x < fullWhite.getPixels().length; x++) {
      for (int y = 0; y < fullWhite.getPixels()[x].length; y++) {
        checkIfAllAppliedCorrectly &= fullWhite.getPixels()[x][y].equals(postApplyFullWhite[x][y]);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  /**
   * Tests blur apply for ability to effect image of all non-zero RGB.
   */

  @Test
  public void blurApplyOnFullDark() {
    blur.apply(dark);
    IPixel darkCorner = new PixelImpl(8, 3, 5);
    IPixel darkMid = new PixelImpl(11, 4, 7);
    IPixel darkCenter = new PixelImpl(15, 5, 10);

    IPixel[][] postApplyDark = new IPixel[][]{
        {darkCorner, darkMid, darkMid, darkMid, darkCorner},
        {darkMid, darkCenter, darkCenter, darkCenter, darkMid},
        {darkMid, darkCenter, darkCenter, darkCenter, darkMid},
        {darkMid, darkCenter, darkCenter, darkCenter, darkMid},
        {darkCorner, darkMid, darkMid, darkMid, darkCorner}};

    boolean checkIfAllAppliedCorrectly = true;
    for (int x = 0; x < dark.getPixels().length; x++) {
      for (int y = 0; y < dark.getPixels()[x].length; y++) {
        checkIfAllAppliedCorrectly &= dark.getPixels()[x][y].equals(postApplyDark[x][y]);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

}