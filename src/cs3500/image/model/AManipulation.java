package cs3500.image.model;

/**
 * An abstract class representing a manipulation or effect on an image.
 */
public abstract class AManipulation implements IManipulation {

  /**
   * Clamps the RGB values of each pixel in an image.
   * @param image the image
   */
  public void clamp(IImage image) {
    IPixel[][] pixels = image.getPixels();
    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[x].length; y++) {
        IPixel p = pixels[x][y];
        pixels[x][y] = new PixelImpl(clampHelper(p.getRValue()),
            clampHelper(p.getGValue()), clampHelper(p.getBValue()));
      }
    }
  }

  /**
   * Clamps the given value between 0 and 255.
   * @param value the value to be clamped
   * @return the clamped value
   */
  private int clampHelper(int value) {
    if (value > 255) {
      return 255;
    }

    if (value < 0) {
      return 0;
    }

    return value;
  }

}
