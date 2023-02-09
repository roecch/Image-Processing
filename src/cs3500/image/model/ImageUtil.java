package cs3500.image.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return a scanner containing the ppm file content
   */

  static Scanner scanPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() != 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    return new Scanner(builder.toString());
  }

  /**
   * Reads the file of the given name as a JPEG or PNG as it RGB values of the image.
   *
   * @param path name of file to read
   * @return 2D array of IPixels of the image
   * @throws IllegalArgumentException Throws exception when no file of given name can be found
   */

  public static IPixel[][] readJPGPNG(String path) throws IllegalArgumentException {
    BufferedImage image = null;
    File file = null;

    try {
      file = new File(path);
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to reach file from given path.");
    }

    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read file.");
    }

    IPixel[][] copy = new IPixel[image.getHeight()][image.getWidth()];

    for (int x = 0; x < copy.length; x++) {
      for (int y = 0; y < copy[x].length; y++) {
        copy[x][y] = new PixelImpl(new Color(image.getRGB(y, x)));
      }
    }
    return copy;
  }

  /**
   * Reads a PPM file and returns a corresponding 2DArray of pixels.
   *
   * @param filename the path of the file
   * @return a 2D-Array of the image's pixels
   */
  static IPixel[][] readPPM(String filename) {

    Scanner sc = scanPPM(filename);

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();

    IPixel[][] pixels = new PixelImpl[height][width];

    int maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new PixelImpl(r, g, b);
      }
    }

    return pixels;
  }

  /**
   * Gets the maximum value found within the image specified in the given path.
   *
   * @param filename the path to the image to be read
   * @return the maximum RGB-value found within the image
   */

  public static int getMaxValue(String filename) {
    Scanner sc = scanPPM(filename);

    String token = sc.next();
    int width = sc.nextInt();
    int height = sc.nextInt();

    return sc.nextInt();
  }

  /**
   * Exports the image as a PPM.
   *
   * @param filename name of file to export image as
   */

  public static void exportPPMUtil(String filename, String ppmString) {
    try {
      BufferedWriter writer = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream(filename)));
      writer.write(ppmString);
      writer.flush();
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Exports the txt file containing the given text to the given path.
   * @param filename the name of the destination file path
   * @param txt the text to be written to the txt file
   */
  public static void exportTxtFile(String filename, String txt) {
    try {
      BufferedWriter writer = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream("res\\" + filename + ".txt")));
      writer.write(txt);
      writer.flush();
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Exports the image as either a JPEG or PNG.
   *
   * @param type     type of file to export image as
   * @param filename name of file to export image as
   * @throws IllegalArgumentException when buffered image is null or unable to write to
   */

  public static void exportAsJPGPNG(BufferedImage bufferedImage, String type, String filename)
      throws IllegalArgumentException {
    try {
      RenderedImage image = Objects.requireNonNull(bufferedImage);

      ImageIO.write(image, type, new File(filename));

    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Discerns if the given file path name already exists.
   * @param name the file path
   * @return whether or not file path already exists.
   */
  public static boolean attemptNewFileMulti(String name) {
    try {
      File f = new File(name);
      return f.createNewFile();
    }
    catch (IOException e) {
      return false;
    }
  }


}