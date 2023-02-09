package cs3500.image.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.nio.Buffer;

/**
 * An image with a means of storing individual pixels that can be accessed and altered with photo
 * manipulation.
 */
public interface IImage {

  /**
   * Retrieves this image's pixels.
   *
   * @return the pixels
   */
  IPixel[][] getPixels();

  /**
   * Exports image to filename.
   *
   * @param filename name to export as
   */

  void export(String filename);

  /**
   * Creates BufferedImage based on this image.
   *
   * @return BufferedImage of this image.
   */

  BufferedImage constructBuffered();

  /**
   * Creates deep copy of this image.
   * @return copy of this image
   */
  IImage cloneImage();
}
