package cs3500.image.model;

import static org.junit.Assert.assertEquals;
import java.awt.Color;
import org.junit.Test;

/**
 * Tests for JPGImage: Ensures that the methods in the
 * JPGImage class are behaving correctly.
 */

public class JPGImageTest {

  @Test
  public void testReading() {
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

    assertEquals("15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n",
        new JPGImage("src//blue.jpg").toString());
  }

  @Test (expected = NullPointerException.class)
  public void nullConstructor() {
    new JPGImage((JPGImage) null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidPathConstructor() {
    new JPGImage("panda.jpg");
  }

  @Test
  public void testGetPixels() {
    Color blueCol = new Color(15, 91, 201);

    boolean checkBlue = true;

    for (IPixel[] row : new JPGImage("src//blue.jpg").getPixels()) {
      for (IPixel p : row) {
        checkBlue &= p.getRValue() == blueCol.getRed();
        checkBlue &= p.getGValue() == blueCol.getGreen();
        checkBlue &= p.getBValue() == blueCol.getBlue();
      }
    }
    assertEquals(true, checkBlue);
  }
}
