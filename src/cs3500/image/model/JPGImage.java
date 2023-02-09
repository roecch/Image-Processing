package cs3500.image.model;

import java.util.Objects;

/**
 * A class that represents an image rendered from a given JPEG file path.
 */
public class JPGImage extends AImage {

  private final IPixel[][] pixels;

  /**
   * The JPEG constructor.
   * Reads the JPEG file of the given name.
   *
   * @param path name of file to read
   */

  public JPGImage(String path) {
    Objects.requireNonNull(path);

    try {
      this.pixels = ImageUtil.readJPGPNG(path);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * The copy constructor of JPGImage.
   * @param that JPGImage to make copy of
   */

  public JPGImage(JPGImage that) {
    this.pixels = that.clonePixels();
  }

  @Override
  public IPixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public IImage cloneImage() {
    return new JPGImage(this);
  }

  @Override
  public boolean equals(Object o) {
    boolean check = true;
    if (o instanceof JPGImage) {
      IPixel[][] otherLayers = ((JPGImage) o).getPixels();
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
