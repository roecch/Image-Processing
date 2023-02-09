package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for PixelImpl: Ensures that the methods in the PixelImpl class are behaving correctly.
 */

public class PixelImplTest {

  IPixel p1;
  IPixel allZero;
  IPixel all255;
  IPixel negatives;
  IPixel above255;

  @Before
  public void setUp() throws Exception {
    p1 = new PixelImpl(12, 5, 9);
    allZero = new PixelImpl(0, 0, 0);
    all255 = new PixelImpl(255, 255, 255);
    negatives = new PixelImpl(-1, -20, 34);
    above255 = new PixelImpl(34, 256, 2);
  }

  /**
   * Tests getRValue for ability to return rValue.
   */

  @Test
  public void getRValue() {
    assertEquals(12, p1.getRValue());
  }

  /**
   * Tests getGValue for ability to return gValue.
   */

  @Test
  public void getGValue() {
    assertEquals(5, p1.getGValue());
  }

  /**
   * Tests getBValue for ability to return bValue.
   */

  @Test
  public void getBValue() {
    assertEquals(9, p1.getBValue());
  }

  /**
   * Tests applyKernelFactor for ability to effect with factor of zero.
   */

  @Test
  public void applyKernelFactorZero() {
    IPixel p2 = new PixelImpl(55, 255, 2);
    p2 = p2.applyKernelFactor(0.0);

    assertEquals(0, p2.getRValue());
    assertEquals(0, p2.getGValue());
    assertEquals(0, p2.getBValue());
  }

  /**
   * Tests applyKernelFactor for ability to effect with negative decimal factor.
   */

  @Test
  public void applyKernelFactorNegativeDecimal() {
    p1 = p1.applyKernelFactor(-0.25);

    assertEquals(-3, p1.getRValue());
    assertEquals(-1, p1.getGValue());
    assertEquals(-2, p1.getBValue());
  }

  /**
   * Tests applyKernelFactor for ability to effect with positive decimal factor.
   */

  @Test
  public void applyKernelFactorPositiveDecimal() {
    p1 = p1.applyKernelFactor(0.125);

    assertEquals(2, p1.getRValue());
    assertEquals(1, p1.getGValue());
    assertEquals(1, p1.getBValue());

  }

  /**
   * Tests applyKernelFactor for ability to effect with positive factor.
   */

  @Test
  public void applyKernelFactorPositive() {
    IPixel newPixel = new PixelImpl(12, 2, 1);
    newPixel = newPixel.applyKernelFactor(2.34);

    assertEquals((int) 28.58, newPixel.getRValue());
    assertEquals((int) 5.14, newPixel.getGValue());
    assertEquals((int) 2.84, newPixel.getBValue());
  }

  /**
   * Tests applyLinearTransformation for ability to effect with matrix of zero.
   */

  @Test
  public void applyLinearTransformationZeroes() {
    double[][] matrix = {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}};
    p1 = p1.applyLinearTransformation(matrix);

    assertEquals(0, p1.getRValue());
    assertEquals(0, p1.getGValue());
    assertEquals(0, p1.getBValue());
  }

  /**
   * Tests applyLinearTransformation for ability to effect with matrix of greyscale.
   */


  @Test
  public void applyLinearTransformationGreyscale() {
    double[][] grey = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};

    p1 = p1.applyLinearTransformation(grey);

    assertEquals(7, p1.getRValue());
    assertEquals(7, p1.getGValue());
    assertEquals(7, p1.getBValue());

    allZero = allZero.applyLinearTransformation(grey);

    assertEquals(0, allZero.getRValue());
    assertEquals(0, allZero.getGValue());
    assertEquals(0, allZero.getBValue());

    all255 = all255.applyLinearTransformation(grey);

    assertEquals(255, all255.getRValue());
    assertEquals(255, all255.getRValue());
    assertEquals(255, all255.getRValue());
  }

  /**
   * Tests applyLinearTransformation for ability to effect with matrix of sepia.
   */

  @Test
  public void applyLinearTransformationSepia() {
    double[][] sepia = {{0.3930, 0.7690, 0.1890},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    p1 = p1.applyLinearTransformation(sepia);

    assertEquals(10, p1.getRValue());
    assertEquals(9, p1.getGValue());
    assertEquals(7, p1.getBValue());

    all255 = all255.applyLinearTransformation(sepia);

    assertEquals(345, all255.getRValue());
    assertEquals(345, all255.getRValue());
    assertEquals(345, all255.getRValue());

    allZero = allZero.applyLinearTransformation(sepia);

    assertEquals(0, allZero.getRValue());
    assertEquals(0, allZero.getGValue());
    assertEquals(0, allZero.getBValue());
  }
}