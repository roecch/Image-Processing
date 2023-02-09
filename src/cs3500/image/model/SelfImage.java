package cs3500.image.model;

import java.awt.Color;
import java.util.Random;

/**
 * An image that resembles a checkerboard, with tiles being randomly colored based on a given array
 * of colors.
 */
public class SelfImage extends AImage {
  private final int size;
  private final IPixel[][] pixels;

  /**
   * Constructor for SelfImage.
   * @param numTile the number of tiles in the board
   * @param size the size in pixels of each tile
   * @param colors the colors within the board, to be randomized
   */
  public SelfImage(int numTile, int size, Color[] colors) {
    int numTitlesPerSideInt = (int) Math.sqrt(numTile);

    if (numTile > 0 && Math.sqrt(numTile) - Math.floor(Math.sqrt(numTile)) == 0 && size > 0) {
      this.size = size;
      pixels = new PixelImpl[numTitlesPerSideInt * size][numTitlesPerSideInt * size];
      for (int x = 0; x < numTitlesPerSideInt; x++) {
        for (int y = 0; y < numTitlesPerSideInt; y++) {
          Color col = colors[new Random().nextInt(colors.length)];
          this.changePixelColor(x, y, col);
        }
      }
    }
    else {
      throw new IllegalArgumentException("Invalid Number of Tiles or Size");
    }
  }

  /**
   * The copy constructor of SelfImage.
   * @param that SelfImage to make copy of
   */

  public SelfImage(SelfImage that) {
    this.pixels = that.clonePixels();
    this.size = that.size;
  }

  /**
   * Changes the IPIxel at the location of x,y to the given Color.
   *
   * @param x row number
   * @param y column number
   * @param color color to change to
   */

  private void changePixelColor(int x, int y, Color color) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        pixels[y * size + i][x * size + j]
            = new PixelImpl(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
  }

  @Override
  public IPixel[][] getPixels() {
    return pixels;
  }

  @Override
  public IImage cloneImage() {
    return new SelfImage(this);
  }
}
