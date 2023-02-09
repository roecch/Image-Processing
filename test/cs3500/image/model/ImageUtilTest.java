package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;
import org.junit.Test;

/**
 * Tests of the ImageUtil class: Ensure the methods of the ImageUtil class behaves properly.
 */

public class ImageUtilTest {

  /**
   * Tests that a jpeg file is properly read.
   */
  @Test
  public void testReadJPG() {
    IPixel[][] pixels = new IPixel[5][5];
    for (int i = 0; i < 5; i += 1) {
      for (int j = 0; j < 5; j += 1) {
        pixels[i][j] = new PixelImpl(15, 91, 201);
      }
    }

    assertEquals(pixels, ImageUtil.readJPGPNG("src\\blue.jpg"));
  }

  /**
   * Tests that a png file is properly read.
   */
  @Test
  public void testReadPNG() {

    IPixel[][] pixels = new IPixel[5][5];
    for (int i = 0; i < 5; i += 1) {
      for (int j = 0; j < 5; j += 1) {
        pixels[i][j] = new PixelImpl(15, 91, 201);
      }
    }

    assertEquals(pixels, ImageUtil.readJPGPNG("src\\blue.png"));
  }


  /**
   * Tests read if given unknown filename.
   */

  @Test
  public void testReadNonexistent() {
    try {
      ImageUtil.readPPM("src//unknown.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("File src//unknown.ppm not found!", e.getMessage());
    }
  }

  /**
   * Tests scan if given null filename.
   */

  @Test(expected = NullPointerException.class)
  public void testNullPath() {
    ImageUtil.scanPPM(null);
  }

  /**
   * Tests read if given unknown filename.
   */

  @Test(expected = NullPointerException.class)
  public void testNullPathRead() {
    ImageUtil.readPPM(null);
  }

  /**
   * Tests for the max value of null file.
   */

  @Test(expected = NullPointerException.class)
  public void testNullPathGetMax() {
    ImageUtil.getMaxValue(null);
  }

  /**
   * Tests scanPPM for ability to retrieve file.
   */

  @Test
  public void testScanner() {
    String result = "";
    Scanner scan = ImageUtil.scanPPM("src//testing.ppm");
    while (scan.hasNext()) {
      result += scan.next();
    }

    assertEquals(
        "P3662551741581691561491391581541421721651591861811781851791811541431391491431291"
            + "74168156189184180186182183181170176140133107154150141187182178198191185177169166163"
            + "15515214614412117516916919218718418817917216315714315315013516616014619318418719318"
            + "41851701611541511461271521511311871791771911831801771691671591501531641541621721611"
            + "65",
        result);
  }

  /**
   * Tests readPPM if receives bad ppm file.
   */

  @Test
  public void badP6() {
    try {
      ImageUtil.readPPM("src//bad.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3", e.getMessage());
    }
  }

  /**
   * Tests readPPM if receives valid ppm file.
   */

  @Test
  public void readPPM() {
    assertEquals(427, ImageUtil.readPPM("res//originalLandscape.ppm").length);
    assertEquals(640, ImageUtil.readPPM("res//originalLandscape.ppm")[0].length);
  }

  /**
   * Tests for the max value of valid file.
   */

  @Test
  public void testGetMax() {
    assertEquals(255, ImageUtil.getMaxValue("res//originalLandscape.ppm"));
  }

  /**
   * Tests for the max value of unknown file.
   */

  @Test
  public void testGetMaxNonexistent() {
    try {
      ImageUtil.getMaxValue("src//unknown.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("File src//unknown.ppm not found!", e.getMessage());
    }
  }

  /**
   * Tests to see if the given file path already exists.
   */
  @Test
  public void testExportMulti() {
    assertEquals(true, ImageUtil.attemptNewFileMulti("res\\originalLandscape.jpg"));
  }
}