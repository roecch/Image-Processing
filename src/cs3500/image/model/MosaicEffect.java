package cs3500.image.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * An image manipulation object that alters an image to have a stained-glass effect.
 */
public class MosaicEffect extends AManipulation {

  private final int seedCount;

  public MosaicEffect() {
    this(1000);
  }

  public MosaicEffect(int seedCount) {
    if (seedCount < 0) {
      throw new IllegalArgumentException("Invalid seed count.");
    }

    this.seedCount = seedCount;
  }

  /**
   * Applies mosaic effect to this image with a default of 10,000 seeds.
   *
   * @param image the image to apply this effect to
   */
  @Override
  public void apply(IImage image) {
    IPixel[][] pixels = image.getPixels();

    Map<Point, List<Point>> seeds = generateSeeds(pixels.length, pixels[0].length);

    double min = Math.sqrt(Math.pow(pixels[0].length, 2.0) + Math.pow(pixels.length, 2.0));
    Point closest = null;

    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[0].length; y++) {
        for (Map.Entry<Point, List<Point>> item : seeds.entrySet()) {
          if (distance(item.getKey(), x, y) < min - 0.001) {
            min = distance(item.getKey(), x, y);
            closest = item.getKey();
          }
        }

        seeds.get(closest).add(new Point(x, y));

        closest = null;
        min = Math.sqrt(Math.pow(pixels.length, 2.0) + Math.pow(pixels[0].length, 2.0));
      }
    }

    for (Map.Entry<Point, List<Point>> item : seeds.entrySet()) {

      int totalR = 0;
      int totalG = 0;
      int totalB = 0;

      for (Point p : item.getValue()) {
        totalR += pixels[(int) p.getX()][(int) p.getY()].getRValue();
        totalG += pixels[(int) p.getX()][(int) p.getY()].getGValue();
        totalB += pixels[(int) p.getX()][(int) p.getY()].getBValue();

        if (item.getValue().size() > 0) {
          totalR /= item.getValue().size();
          totalG /= item.getValue().size();
          totalB /= item.getValue().size();
        }

        pixels[(int) p.getX()][(int) p.getY()] = new PixelImpl(totalR, totalG, totalB);
      }
    }

    super.clamp(image);
  }

  /**
   * Applies the distance formula between two points.
   *
   * @param seed the coordinates of a seed
   * @param x    the x-coordinate of the second point
   * @param y    the y-coordinate of the second point
   * @return the distance between the two points
   */
  private double distance(Point seed, int x, int y) {
    return Math.sqrt(Math.pow(Math.abs(seed.x - x), 2)
        + Math.pow(Math.abs(seed.y - y), 2));
  }

  /**
   * Generates 10,000 seeds based on the width an height provided.
   *
   * @param actualWidth  the width of the image
   * @param actualHeight the height of the image
   * @return a map of the point of each seed to it's closest points throughout the image (the lists
   * are empty for now)
   */
  private Map<Point, List<Point>> generateSeeds(int actualWidth, int actualHeight) {
    Map<Point, List<Point>> seedClusters = new HashMap<>();
    while (seedClusters.size() < this.seedCount) {
      Point newPoint = new Point(new Random().nextInt(actualWidth),
          new Random().nextInt(actualHeight));
      seedClusters.putIfAbsent(newPoint, new ArrayList<>());
    }
    return seedClusters;
  }
}
