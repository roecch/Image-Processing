package cs3500.image.model;

/**
 * A linear color transformation class that is performs a matrix multiplication of the RGB values
 * of each pixel of the image to create a greyscale effect.
 */

public class GreyscaleTrans extends AColorTransform {

  private static final double[][] TRANSFORMATION =
      {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};

  /**
   * Applies the transformation matrix of the GreyscaleTrans class to the given image.
   * Also insures that all RBG values are between 0 and 255 inclusive
   *
   * @param image Image that is to be manipulated by kernel
   */

  @Override
  public void apply(IImage image) {
    super.applyTransformation(image, this.TRANSFORMATION);
    clamp(image);
  }
}
