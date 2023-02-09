package cs3500.image.model;

import java.util.ArrayList;

/**
 * A filter class to manipulate through a kernel factor to blur image.
 */

public class BlurFilter extends AFilter {

  private static final double[][] kernel =
      {{0.0625, 0.125, 0.0625},
          {0.125, 0.25, 0.125},
          {0.0625, 0.125, 0.0625}};

  /**
   * Cycles through each pixel of the image and uses the surrounding pixels to calculate a new
   * PixelImpl and blur the image.
   *
   * @param image Image that is to be manipulated by kernel
   */
  @Override
  public void apply(IImage image) {
    IPixel[][] imagePixels = image.getPixels().clone();
    IPixel[][] postApplyPixels = new IPixel[imagePixels.length][imagePixels[0].length];
    ArrayList<IPixel> affectedPixels = new ArrayList<>();

    for (int x = 0; x < imagePixels.length; x++) {
      for (int y = 0; y < imagePixels[x].length; y++) {
        affectedPixels.clear();

        affectedPixels.addAll(this.applyHelper(imagePixels[x], this.kernel[1], y));

        if (x + 1 <= imagePixels.length - 1) {
          affectedPixels.addAll(this.applyHelper(imagePixels[x + 1], this.kernel[2], y));
        }

        if (x - 1 >= 0) {
          affectedPixels.addAll(this.applyHelper(imagePixels[x - 1], this.kernel[0], y));
        }

        postApplyPixels[x][y] = super.updatePixel(affectedPixels);
      }
    }

    super.changePixelsToApplied(image, postApplyPixels);
    super.clamp(image);
  }

  /**
   * Cycles through each pixel of the image and uses the surrounding pixels to calculate a new
   * PixelImpl and blur the image.
   *
   * @param pixels    the current row of the image's pixels being analyzed
   * @param kernelRow the row of the kernel corresponding to the current row
   * @param y         the current column of the current row being analyzed
   */

  private ArrayList<IPixel> applyHelper(IPixel[] pixels, double[] kernelRow, int y) {
    ArrayList<IPixel> affectedPixels = new ArrayList<>();

    affectedPixels.add(pixels[y].applyKernelFactor(kernelRow[1]));

    if (y + 1 <= pixels.length - 1) {
      affectedPixels.add(pixels[y + 1].applyKernelFactor(kernelRow[2]));
    }
    if (y - 1 >= 0) {
      affectedPixels.add(pixels[y - 1].applyKernelFactor(kernelRow[0]));
    }

    return affectedPixels;
  }
}
