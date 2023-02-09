package cs3500.image.model;

import java.awt.Color;

/**
 * A class representing a pixel, with its color representing with standard RGB values.
 */
public class PixelImpl implements IPixel {
  private final int rValue;
  private final int gValue;
  private final int bValue;

  /**
   * The constructor of PixelImpl.
   */
  public PixelImpl(int rValue, int gValue, int bValue) {
    this.rValue = rValue;
    this.gValue = gValue;
    this.bValue = bValue;
  }

  /**
   * The constructor of PixelImpl for Color parameter.
   */
  public PixelImpl(Color color) {
    this.rValue = color.getRed();
    this.gValue = color.getGreen();
    this.bValue = color.getBlue();
  }

  /**
   * Returns the Red value of the PixelImpl.
   *
   * @return red value of pixel
   */
  @Override
  public int getRValue() {
    return rValue;
  }

  /**
   * Returns the Green value of the PixelImpl.
   *
   * @return green value of pixel
   */
  @Override
  public int getGValue() {
    return gValue;
  }

  /**
   * Returns the Blue value of the PixelImpl.
   *
   * @return blue value of pixel
   */
  @Override
  public int getBValue() {
    return bValue;
  }

  /**
   * Multiplies the RGB values of the PixelImpl by the factor and rounds.
   * Making a new PixelImpl out of the products.
   *
   * @param factor a double that is the number to by multiplied with RGB values
   * @return PixelImpl with all RGB values multiplied by factor
   */
  @Override
  public IPixel applyKernelFactor(double factor) {
    double rounding;
    if (factor < 0) {
      rounding = -0.5;
    }
    else {
      rounding = 0.5;
    }

    return new PixelImpl((int) ((rValue + 0.0) * factor + rounding),
        (int) ((gValue + 0.0) * factor + rounding), (int) ((bValue + 0.0) * factor + rounding));
  }

  @Override
  public int getRGB() {
    return new Color(rValue, gValue, bValue).getRGB();
  }

  /**
   * Multiplies and adds the RGB values of the PixelImpl through the matrix to
   * a PixelImpl with new RBG values.
   *
   * @param matrix a 2D double array acting as a matrix to be applied to the RGB values
   * @return PixelImpl with all RGB values multiplied by factor
   */
  @Override
  public IPixel applyLinearTransformation(double[][] matrix) {
    int newR = 0;
    int newG = 0;
    int newB = 0;

    newR = (int) (matrix[0][0] * this.getRValue()
        + matrix[0][1] * this.getGValue()
        + matrix[0][2] * this.getBValue() + 0.5);

    newG = (int) (matrix[1][0] * this.getRValue()
        + matrix[1][1] * this.getGValue()
        + matrix[1][2] * this.getBValue() + 0.5);

    newB = (int) (matrix[2][0] * this.getRValue()
        + matrix[2][1] * this.getGValue()
        + matrix[2][2] * this.getBValue() + 0.5);

    return new PixelImpl(newR, newG, newB);
  }

  /**
   * Returns a string of the red value, green value, then blue value with a space in between each.
   *
   * @return String of the RBG values
   */

  @Override
  public String toString() {
    return this.rValue + " " + this.gValue + " " + this.bValue;
  }

  /**
   * Checks if the given objects RGB values matches this ones.
   *
   * @return whether the two objects are equal
   */

  @Override
  public boolean equals(Object p) {
    if (!(p instanceof PixelImpl)) {
      return false;
    }
    else {
      return this.getRValue() == ((PixelImpl) p).getRValue()
          && this.getGValue() == ((PixelImpl) p).getGValue()
          && this.getBValue() == ((PixelImpl) p).getBValue();
    }
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return int of the hashcode
   */

  @Override
  public int hashCode() {
    return this.hashCode();
  }
}