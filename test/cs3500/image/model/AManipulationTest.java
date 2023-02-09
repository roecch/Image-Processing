package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for AManipulation: Ensures that the methods in the
 * AManipulation class are behaving correctly.
 */

public class AManipulationTest {
  AManipulation manipulation = new SharpenFilter();
  SelfImage imageNormal;

  @Before
  public void setUp() throws Exception {
    imageNormal =
        new SelfImage(9, 1, new Color[]{Color.MAGENTA});
  }

  /**
   * Tests clamp method for image of all valid RGB values.
   */

  @Test
  public void testClamp() {
    manipulation.clamp(imageNormal);
    IPixel magAfter = new PixelImpl(255, 0, 255);
    boolean checkIfAllAppliedCorrectly = true;
    for (IPixel[] pRow: imageNormal.getPixels()) {
      for (IPixel p: pRow) {
        checkIfAllAppliedCorrectly &= p.equals(magAfter);
      }
    }
    assertEquals(true, checkIfAllAppliedCorrectly);
  }

  // AManipulation clamp method's ability to clamp PixelImpl when its RGB is negative or
  // over 255 can be implied by the success of the tests of
  // BlurFilter, Greyscale, SepiaTrans, and SharpenFilter
}