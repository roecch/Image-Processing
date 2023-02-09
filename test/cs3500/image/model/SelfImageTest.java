package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Test;

/**
 * Tests for user-made checkerboard images: Ensures that the methods in the
 * SelfImage class are behaving correctly.
 */
public class SelfImageTest {

  /**
   * Tests SelfImage constructor for non square number of tiles input.
   */

  @Test
  public void nonSquareInput() {
    Color[] colors1 = {Color.GRAY, Color.GREEN};

    try {
      new SelfImage(12, 2, colors1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Number of Tiles or Size", e.getMessage());
    }
  }

  /**
   * Tests SelfImage constructor for negative number of tiles.
   */

  @Test(expected = IllegalArgumentException.class)
  public void negativeInput() {
    Color[] colors1 = {Color.GRAY, Color.GREEN};
    new SelfImage(-1, 2, colors1);
  }

  /**
   * Tests SelfImage constructor for negative size.
   */

  @Test(expected = IllegalArgumentException.class)
  public void negativeSizeInput() {
    Color[] colors1 = {Color.GRAY, Color.GREEN};
    new SelfImage(9, -2, colors1);
  }

  /**
   * Tests SelfImage constructor for empty array of colors.
   */

  @Test(expected = IllegalArgumentException.class)
  public void emptyColors() {
    Color[] colors = {};
    new SelfImage(4, 4, colors);
  }

  /**
   * Tests SelfImage constructor for null array of colors.
   */

  @Test(expected = NullPointerException.class)
  public void nullColors() {
    new SelfImage(4, 4, null);
  }

  /**
   * Tests getPixels to retrieve pixels of image.
   */

  @Test
  public void getPixels() {
    Color[] colors1 = {Color.GRAY};
    IImage self = new SelfImage(1, 2, colors1);

    IPixel[][] pixels = {{new PixelImpl(128, 128, 128), new PixelImpl(128, 128, 128)},
        {new PixelImpl(128, 128, 128), new PixelImpl(128, 128, 128)}};
    assertEquals(pixels, self.getPixels());
  }
}