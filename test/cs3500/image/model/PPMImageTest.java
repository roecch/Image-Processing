package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for PPMImage: Ensures that the methods in the
 * PPMImage class are behaving correctly.
 */

public class PPMImageTest {
  PPMImage image;

  @Before
  public void setUp() {
    image = new PPMImage("res\\originalFlowers.ppm");
  }

  /**
   * Tests SelfImage constructor for valid file.
   */

  @Test
  public void testConstructor() {
    assertEquals(421, image.getPixels().length);
  }

  /**
   * Tests SelfImage constructor for null file.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new PPMImage((String) null);
  }

  /**
   * Tests SelfImage constructor for invalid file.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNotFound() {
    new PPMImage("res\\landscape.ppm");
  }

  /**
   * Tests SelfImage getPixels.
   */

  @Test
  public void getPixelsPPM() {
    assertEquals(new PixelImpl(171, 173, 168), image.getPixels()[1][1]);
  }

  /**
   * Tests SelfImage export.
   */

  @Test
  public void exportPPM() throws FileNotFoundException {
    String test = new Scanner(new FileInputStream("res\\originalFlowers.ppm")).nextLine();
    assertEquals("P3" , test);
  }
}