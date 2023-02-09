package cs3500.image.model;

import java.util.ArrayList;

/**
 * A filter manipulation that is performed through a kernel factor.
 */

abstract class AFilter extends AManipulation {

  @Override
  public abstract void apply(IImage image);

  /**
   * Adds up all the values of RGB in each pixel in the ArrayList to create a new Pixel
   * made up of the sums.
   *
   * @param pixels ArrayList of IPixels
   * @return A PixelImpl created by the sums of the RGB values
   */

  protected PixelImpl updatePixel(ArrayList<IPixel> pixels) {
    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;

    for (IPixel p: pixels) {
      newRed += p.getRValue();
      newGreen += p.getGValue();
      newBlue += p.getBValue();
    }

    return new PixelImpl(newRed, newGreen, newBlue);
  }

  /**
   * Updates all the pixels in the image to the corresponding IPixel from the given 2DArray.
   * @param image Image that is to be manipulated by kernel
   * @param postBlurPixels 2D array of IPixels to update the image
   */

  protected void changePixelsToApplied(IImage image, IPixel[][] postBlurPixels) {
    for (int i = 0; i < image.getPixels().length; i++) {
      for (int j = 0; j < image.getPixels()[0].length; j++) {
        image.getPixels()[i][j] = postBlurPixels[i][j];
      }
    }
  }
}
