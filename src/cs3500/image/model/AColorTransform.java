package cs3500.image.model;

/**
 * A linear color transformation that is performed through matrix multiplication of RGB values.
 */
public abstract class AColorTransform extends AManipulation {

  /**
   * Applies the given transformation matrix to each pixel within the given image.
   * @param image the image to apply the transformation to
   * @param transform the transformation matrix
   */
  protected void applyTransformation(IImage image, double[][] transform) {
    IPixel[][] pixels = image.getPixels();
    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[r].length; c++) {
        pixels[r][c] = pixels[r][c].applyLinearTransformation(transform);
      }
    }
  }

  @Override
  public abstract void apply(IImage image);
}
