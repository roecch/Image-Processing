package cs3500.image.model;

/**
 * A pixel of a color that can be represented with RGB channel-values.
 */
public interface IPixel {

  /**
   * Retrieves this pixel's R-channel value.
   * @return the red channel value
   */
  int getRValue();

  /**
   * Retrieves this pixel's G-channel value.
   * @return the green channel value
   */
  int getGValue();

  /**
   * Retrieves this pixel's B-channel value.
   * @return the blue channel value
   */
  int getBValue();

  /**
   * Applies the given kernel factor to this pixel's channels.
   * @param factor the factor to be applied
   * @return a new pixel of modified values
   */
  IPixel applyKernelFactor(double factor);

  /**
   * Applies the given linear transformation matrix to this pixel's channels.
   * @param matrix the linear transformation matrix to be applied
   * @return a new pixel of the modified values
   */
  IPixel applyLinearTransformation(double[][] matrix);

  /**
   * Returns the SRGB value of this pixel's color.
   * @return SRGB value
   */
  int getRGB();
}
