package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Test;

/**
 * Tests for PNGImage: Ensures that the methods in the
 * PNGImage class are behaving correctly.
 */

public class PNGImageTest {
  @Test
  public void testReading() {
    assertEquals("15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    ",
        new PNGImage("src//blue.png").toString());
  }

  @Test (expected = NullPointerException.class)
  public void nullConstructor() {
    new PNGImage((String) null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidConstructor() {
    new PNGImage("src\\random.jpg");
  }

  @Test
  public void getPixels() {
    Color blueCol = new Color(15, 91, 201);
    IPixel[][] blue = {{new PixelImpl(blueCol), new PixelImpl(blueCol), new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol)}, {new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol)},{new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol)}, {new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol)}, {new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol),
        new PixelImpl(blueCol), new PixelImpl(blueCol)}};

    assertEquals(blue, new PNGImage("src//blue.png").getPixels());
  }

}
