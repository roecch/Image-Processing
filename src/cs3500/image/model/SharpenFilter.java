package cs3500.image.model;

import cs3500.image.model.AFilter;
import cs3500.image.model.IImage;
import cs3500.image.model.IPixel;
import java.util.ArrayList;

/**
 * A filter class to manipulate through a kernel factor to sharpen image.
 */

public class SharpenFilter extends AFilter {

  private static final double[][] kernel =
      {{-0.125, -0.125, -0.125, -0.125, -0, 125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, 0.25, 1.0, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, -0.125, -0.125, -0.125, -0, 125}};

  /**
   * Cycles through each pixel of the image and uses the surrounding pixels to calculate a new
   * PixelImpl and sharpen the image.
   *
   * @param image Image that is to be manipulated by kernel
   */

  public void apply(IImage image) {
    IPixel[][] pixels = image.getPixels().clone();
    IPixel[][] postApplyPixels = new IPixel[pixels.length][pixels[0].length];
    ArrayList<IPixel> affectedPixels = new ArrayList<>();

    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[0].length; y++) {
        affectedPixels.clear();

        affectedPixels.addAll(this.applyHelper(pixels[x], this.kernel[2], y));

        if (x + 2 <= pixels.length - 1) {
          affectedPixels.addAll(this.applyHelper(pixels[x + 2], this.kernel[4], y));
        }
        if (x + 1 <= pixels.length - 1) {
          affectedPixels.addAll(this.applyHelper(pixels[x + 1], this.kernel[3], y));
        }
        if (x - 2 >= 0) {
          affectedPixels.addAll(this.applyHelper(pixels[x - 2], this.kernel[0], y));
        }
        if (x - 1 >= 0) {
          affectedPixels.addAll(this.applyHelper(pixels[x - 1], this.kernel[1], y));
        }
        postApplyPixels[x][y] = super.updatePixel(affectedPixels);
      }
    }
    super.changePixelsToApplied(image, postApplyPixels);
    super.clamp(image);
  }

  /**
   * Cycles through each pixel of the image and uses the surrounding pixels to calculate a new
   * PixelImpl and sharpen the image.
   *
   * @param pixels    the current row of the image's pixels being analyzed
   * @param kernelRow the row of the kernel corresponding to the current row
   * @param y         the current column of the current row being analyzed
   */

  private ArrayList<IPixel> applyHelper(IPixel[] pixels, double[] kernelRow, int y) {
    ArrayList<IPixel> affectedPixels = new ArrayList<IPixel>();

    affectedPixels.add(pixels[y].applyKernelFactor(kernelRow[2]));

    if (y + 1 <= pixels.length - 1) {
      affectedPixels.add(pixels[y + 1].applyKernelFactor(kernelRow[3]));
    }
    if (y + 2 <= pixels.length - 1) {
      affectedPixels.add(pixels[y + 2].applyKernelFactor(kernelRow[4]));
    }
    if (y - 2 >= 0) {
      affectedPixels.add(pixels[y - 2].applyKernelFactor(kernelRow[0]));
    }
    if (y - 1 >= 0) {
      affectedPixels.add(pixels[y - 1].applyKernelFactor(kernelRow[1]));
    }

    return affectedPixels;
  }
}
