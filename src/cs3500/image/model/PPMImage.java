package cs3500.image.model;

import java.util.Objects;

/**
 * An image converted from a ppm file.
 */
public class PPMImage extends AImage {

  private final IPixel[][] pixels;

  /**
   * Constructor for PPMImage.
   *
   * @param fileName the path to the image
   */
  public PPMImage(String fileName) {
    this.pixels = ImageUtil.readPPM(Objects.requireNonNull(fileName));
  }

  /**
   * The copy constructor of PPMImage.
   * @param that PPMImage to make copy of
   */

  public PPMImage(PPMImage that) {
    this.pixels = that.clonePixels();
  }

  @Override
  public IPixel[][] getPixels() {
    return pixels;
  }

  @Override
  public IImage cloneImage() {
    return new PPMImage(this);
  }


  @Override
  public boolean equals(Object o) {
    boolean check = true;
    if (o instanceof PPMImage) {
      IPixel[][] otherLayers = ((PPMImage) o).getPixels();
      for (int i = 0; i < this.pixels.length; i++) {
        for (int j = 0; j < this.pixels[0].length; j++) {
          check &= this.pixels[i][j] == otherLayers[i][j];
        }
      }
      return check;
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.hashCode();
  }
}
