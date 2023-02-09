package cs3500.image.model;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * An abstract class representation of an image that stores information on its individual pixels.
 */
public abstract class AImage implements IImage {

  @Override
  public abstract IPixel[][] getPixels();

  /**
   * Renders the given 2DArray of pixels as a string.
   *
   * @param pixels the pixels to be rendered
   * @return a string of pixels, newlines separating each row
   */

  protected String stringPixels(IPixel[][] pixels) {
    String ppmString = "";
    for (IPixel[] row : pixels) {
      ppmString
          += "\n" + Arrays.toString(row).replace(",",
          " ").replace("[", "").replace("]", "");
    }
    return ppmString;
  }

  /**
   * Exports the image as the format from the given string.
   *
   * @param destination String of the filename and type
   */

  @Override
  public void export(String destination) {
    if (destination.endsWith(".ppm")) {
      this.exportPPM(destination);
    } else if (destination.endsWith(".jpg")) {
      ImageUtil.exportAsJPGPNG(constructBuffered(), "jpg", destination);
    } else if (destination.endsWith(".png")) {
      ImageUtil.exportAsJPGPNG(constructBuffered(), "png", destination);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * Imports the image with the format corresponding to the type given in the string.
   *
   * @param filepath String of the filename and type
   */

  public static IImage importImage(String filepath) {
    String fileType = filepath.substring(filepath.lastIndexOf(".") + 1);
    switch (fileType.toLowerCase()) {
      case "ppm":
        return new PPMImage(filepath);
      case "jpg":
        return new JPGImage(filepath);
      case "png":
        return new PNGImage(filepath);
      default:
        throw new IllegalArgumentException("Invalid file import type.");
    }
  }


  /**
   * Exports the image as a PPM.
   *
   * @param filename name of file to export image as
   */

  private void exportPPM(String filename) {
    new ImageUtil().exportPPMUtil(filename, this.stringPixels(this.getPixels()));
  }

  /**
   * Renders this image as a BufferedImage in order for the image to be properly written to the
   * JPG/PNG file.
   *
   * @return the pixels of this image in BufferedImage-form
   */


  @Override
  public BufferedImage constructBuffered() {
    IPixel[][] pixels = this.getPixels();
    BufferedImage img = new BufferedImage(pixels[0].length, pixels.length,
        BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < pixels.length; y++) {
      for (int x = 0; x < pixels[y].length; x++) {
        img.setRGB(x, y, pixels[y][x].getRGB());
      }
    }

    return img;
  }

  /**
   * Writes the image as a string.
   *
   * @return image's pixels written as a string
   */

  @Override
  public String toString() {
    IPixel[][] pixels = this.getPixels();
    String result = "";

    try {
      for (IPixel[] row : pixels) {
        for (IPixel p : row) {
          result += p.toString() + "    ";
        }
        result += "\n";
      }

      return result;
    } catch (NullPointerException e) {
      return "";
    }
  }

  /**
   * Creates deep copy of this images' pixels
   * @return Copy of images' pixels
   */

  protected IPixel[][] clonePixels() {
    IPixel[][] returnPixels = new IPixel[this.getPixels().length][this.getPixels()[0].length];
    for (int i = 0; i < this.getPixels().length; i++) {
      for (int j = 0; j < this.getPixels()[0].length; j++) {
        returnPixels[i][j] = new PixelImpl(this.getPixels()[i][j].getRValue(),
            this.getPixels()[i][j].getGValue(), this.getPixels()[i][j].getBValue());
      }
    }
    return returnPixels;
  }
}
