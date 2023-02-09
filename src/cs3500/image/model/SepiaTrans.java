package cs3500.image.model;

import cs3500.image.model.AColorTransform;
import cs3500.image.model.IImage;

/**
 * A linear color transformation class that is performs a matrix multiplication of the RGB values
 * of each pixel of the image to create a sepia effect.
 */

public class SepiaTrans extends AColorTransform {

  private static final double[][] TRANSFORMATION =
      {{0.3930, 0.7690, 0.1890},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}};


  @Override
  public void apply(IImage image) {
    super.applyTransformation(image, this.TRANSFORMATION);
    clamp(image);
  }
}
