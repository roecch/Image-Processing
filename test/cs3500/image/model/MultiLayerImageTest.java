package cs3500.image.model;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for MultiLayerImage.
 */
public class MultiLayerImageTest {

  MultiLayerImage multi1;
  String png;
  String jpeg;

  @Before
  public void setUp() {
    multi1 = new MultiLayerImage();
    png = "src\\blue.png";
    jpeg = "src\\blue.jpg";
  }

  /**
   * Tests if a model with no layers has an empty file names list.
   */

  @Test
  public void getImageFileNamesEmpty() {
    assertEquals(Arrays.asList(), multi1.getImageFilenames());
  }

  /**
   * Tests if image file names are stored properly in a single-layered model.
   */
  @Test
  public void getImageFileNamesNonEmpty() {
    multi1.createBlankLayer(1);
    multi1.loadLayer("src\\blue.png");
    assertEquals(Arrays.asList("src\\blue.png"), multi1.getImageFilenames());
  }

  /**
   * Tests if the model stores file names properly with multiple uploaded layers.
   */
  @Test
  public void getImageFileNamesNonEmptyMulti() {
    multi1.createBlankLayer(1);
    multi1.createBlankLayer(2);
    multi1.loadLayer("src\\blue.png");
    assertEquals(Arrays.asList("src\\blue.png", ""), multi1.getImageFilenames());
  }

  /**
   * Tests if a model with no layers has an empty list of layers.
   */
  @Test
  public void getLayersEmpty() {
    assertEquals(Arrays.asList(), multi1.getImageLayers());
  }

  /**
   * Tests if blank layers are stored as null in the list of layers in a model.
   */
  @Test
  public void getLayersNull() {
    multi1.createBlankLayer(1);
    List<IImage> test = new ArrayList<>();
    test.add(null);
    assertEquals(test, multi1.getImageLayers());
  }

  /**
   * Tests if the model properly stores layers for a single-layered model.
   */
  @Test
  public void getLayersNonNullImage() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(jpeg);

    List<IImage> test = new ArrayList<>();
    test.add(new JPGImage(jpeg));

    boolean check = true;
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < 5; y++) {
        check &= multi1.getImageLayers().get(0).getPixels()[x][y]
            .equals(test.get(0).getPixels()[x][y]);
      }
    }
    assertEquals(true, check);
  }


  /**
   * Tests if the model properly stores multiple uploaded images as layers.
   */
  @Test
  public void getLayersMultiImages() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(jpeg);

    multi1.createBlankLayer(2);
    multi1.changeCurrent(2);
    multi1.loadLayer(png);

    List<IImage> test = new ArrayList<>();
    test.add(new JPGImage(jpeg));
    test.add(new PNGImage(png));

    boolean check = true;
    for (int i = 0; i < 2; i++) {
      for (int x = 0; x < 5; x++) {
        for (int y = 0; y < 5; y++) {
          check &= multi1.getImageLayers().get(i).getPixels()[x][y]
              .equals(test.get(i).getPixels()[x][y]);
        }
      }
    }

    assertEquals(true, check);
  }

  /**
   * Tests if the model properly accounts for multiple blank layers.
   */
  @Test
  public void getLayersMultiBlankLayers() {
    multi1.createBlankLayer(1);
    multi1.createBlankLayer(2);
    multi1.createBlankLayer(3);

    assertEquals(Arrays.asList(null, null, null), multi1.getImageLayers());
  }

  /**
   * Tests if the model properly stores information on blank and non-blank layers, in proper order.
   */
  @Test
  public void getLayersMixedBlankAndFull() {
    multi1.createBlankLayer(1);
    multi1.createBlankLayer(2);
    multi1.createBlankLayer(3);
    multi1.changeCurrent(2);
    multi1.loadLayer(jpeg);

    multi1.changeCurrent(3);
    multi1.loadLayer(png);

    List<IImage> test = new ArrayList<>();
    test.add(null);
    test.add(new JPGImage(jpeg));
    test.add(new PNGImage(png));

    boolean check = true;
    for (int i = 0; i < 2; i++) {
      for (int x = 0; x < 5; x++) {
        for (int y = 0; y < 5; y++) {
          if (multi1.getImageLayers().get(i) != null && test.get(i) != null) {
            check &= multi1.getImageLayers().get(i).getPixels()[x][y]
                .equals(test.get(i).getPixels()[x][y]);
          }
        }
      }
    }
    assertEquals(true, check);
  }


  /**
   * Tests if the model properly applies a blur effect to a layer.
   */
  @Test
  public void effectLayerBlur() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.effectLayer(new BlurFilter());
    assertEquals("9 51 113    12 68 151    12 68 151    12 68 151    9 51 113    \n"
            + "12 68 151    16 91 202    16 91 202    16 91 202    12 68 151    \n"
            + "12 68 151    16 91 202    16 91 202    16 91 202    12 68 151    \n"
            + "12 68 151    16 91 202    16 91 202    16 91 202    12 68 151    \n"
            + "9 51 113    12 68 151    12 68 151    12 68 151    9 51 113    \n",
        multi1.getImageLayers().get(0).toString());
  }


  /**
   * Tests if the model properly applies a sharpen effect to a layer.
   */
  @Test
  public void effectLayerSharpen() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.effectLayer(new SharpenFilter());
    assertEquals("19 116 251    25 151 255    19 118 251    23 140 255    17 105 226    \n"
            + "25 151 255    35 209 255    27 165 255    33 198 255    23 140 255    \n"
            + "21 129 255    29 176 255    19 121 251    25 154 255    17 107 226    \n"
            + "25 151 255    35 209 255    27 165 255    33 198 255    23 140 255    \n"
            + "19 116 251    25 151 255    19 118 251    23 140 255    17 105 226    \n",
        multi1.getImageLayers().get(0).toString());
  }

  /**
   * Tests if the model properly applies a sepia effect to a layer.
   */
  @Test
  public void effectLayerSepia() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.effectLayer(new SepiaTrans());
    assertEquals("114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
            + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
            + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
            + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
            + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n",
        multi1.getImageLayers().get(0).toString());
  }

  /**
   * Tests if the model properly applies a greyscale effect to a layer.
   */
  @Test
  public void effectLayerGrey() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.effectLayer(new GreyscaleTrans());
    assertEquals("83 83 83    83 83 83    83 83 83    83 83 83    83 83 83    \n"
            + "83 83 83    83 83 83    83 83 83    83 83 83    83 83 83    \n"
            + "83 83 83    83 83 83    83 83 83    83 83 83    83 83 83    \n"
            + "83 83 83    83 83 83    83 83 83    83 83 83    83 83 83    \n"
            + "83 83 83    83 83 83    83 83 83    83 83 83    83 83 83    \n",
        multi1.getImageLayers().get(0).toString());
  }

  /**
   * Tests changing a single layer to be invisible. Tests by attempting to export, which should
   * result in an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void makeCurrentInvisSingleLayer() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.makeCurrentInvis(1);
    multi1.exportFirstNonInvis();
  }

  /**
   * Tests that a single layer can be changed to be visible. Tests via attempting to export.
   */
  @Test
  public void makeCurrentVisSingleLayer() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.makeCurrentInvis(1);
    multi1.makeCurrentVis(1);
    multi1.export("src\\blue.png");

    PNGImage newBlue = new PNGImage("src\\blue.png");
    assertEquals("15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n",
        newBlue.toString());
  }

  /**
   * Tests that a png image is properly uploaded into the model.
   */
  @Test
  public void loadLayerPNG() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    assertEquals("15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n",
        multi1.getImageLayers().get(0).toString());
  }


  /**
   * Tests that a jpg image is properly uploaded into the model.
   */
  @Test
  public void loadLayerJPG() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(jpeg);
    assertEquals("15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
            + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n",
        multi1.getImageLayers().get(0).toString());
  }

  /**
   * Tests that a ppm image is properly uploaded into the model.
   */
  @Test
  public void loadLayerPPM() {
    multi1.createBlankLayer(1);
    multi1.loadLayer("src\\testing.ppm");
    assertEquals(
        "174 158 169    156 149 139    158 154 142    172 165 159    186 181 178    "
            + "185 179 181    \n"
            + "154 143 139    149 143 129    174 168 156    189 184 180    186 182 183    "
            + "181 170 176    \n"
            + "140 133 107    154 150 141    187 182 178    198 191 185    177 169 166    "
            + "163 155 152    \n"
            + "146 144 121    175 169 169    192 187 184    188 179 172    163 157 143    "
            + "153 150 135    \n"
            + "166 160 146    193 184 187    193 184 185    170 161 154    151 146 127    "
            + "152 151 131    \n"
            + "187 179 177    191 183 180    177 169 167    159 150 153    164 154 162    "
            + "172 161 165    \n",
        multi1.getImageLayers().get(0).toString());
  }

  /**
   * Tests that an exception is thrown when an invalid image is uploaded into the model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void loadLayerFaultyImage() {
    multi1.createBlankLayer(1);
    multi1.loadLayer("src\\bad.ppm");
  }


  /**
   * Tests if two different images are loaded into the same layer, that only the last uploaded image
   * is stored.
   */
  @Test
  public void overwriteImageLoad() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(jpeg);
    multi1.loadLayer(png);
    assertEquals(Arrays.asList(png), multi1.getImageFilenames());
  }

  /**
   * Tests if an empty string is added to the file names list when a blank layer is created.
   */
  @Test
  public void createBlankLayerOne() {
    multi1.createBlankLayer(1);
    assertEquals(Arrays.asList(""), multi1.getImageFilenames());
  }

  /**
   * Tests if changeCurrent() works by loading an image into the second layer of a model.
   */
  @Test
  public void changeCurrent() {
    multi1.createBlankLayer(1);
    multi1.createBlankLayer(2);
    multi1.changeCurrent(2);
    multi1.loadLayer(jpeg);

    assertEquals(Arrays.asList("", jpeg), multi1.getImageFilenames());
  }

  /**
   * Tests if an exception is thrown when attempting to change current to an out of bounds index.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void changeCurrentOutOfBounds() {
    multi1.createBlankLayer(1);
    multi1.createBlankLayer(2);
    multi1.changeCurrent(3);
  }

  /**
   * Tests removing the given image.
   */
  @Test
  public void remove() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(jpeg);
    multi1.remove(1);

    assertEquals(Arrays.asList(), multi1.getImageFilenames());
  }

  /**
   * Tests getting pixels from a blank layer.
   */
  @Test
  public void getPixelsBlank() {
    multi1.createBlankLayer(1);
    IPixel[][] pixels = new IPixel[1][1];
    assertEquals(pixels, multi1.getPixels());
  }

  /**
   * Tests getting pixels from a model with no layers.
   */
  @Test
  public void getPixelsEmpty() {
    IPixel[][] pixels = new IPixel[1][1];
    assertEquals(pixels, multi1.getPixels());
  }

  /**
   * Tests getting pixels from a non-empty layer.
   */
  @Test
  public void getPixelsNonEmpty() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);

    IPixel[][] pixels = new IPixel[5][5];
    for (int i = 0; i < 5; i++) {
      for (int r = 0; r < 5; r++) {
        pixels[i][r] = new PixelImpl(15, 91, 201);
      }
    }

    assertEquals(pixels, multi1.getPixels());
  }

  /**
   * Tests for an exception thrown when attempting to export a blank layer.
   */
  @Test(expected = NullPointerException.class)
  public void exportBlank() {
    multi1.createBlankLayer(1);
    multi1.export("src\\bad.ppm");
  }

  /**
   * Tests duplicating a single image in a model.
   */
  @Test
  public void duplicateCurrent() {
    multi1.createBlankLayer(1);
    multi1.loadLayer(png);
    multi1.duplicateCurrent();
    assertEquals(Arrays.asList(png, png), multi1.getImageFilenames());
  }

  /**
   * Tests duplicating multiple images within a single model.
   */
  @Test
  public void duplicateCurrentMulti() {
    multi1.createBlankLayer(1);
    multi1.createBlankLayer(2);
    multi1.loadLayer(png);
    multi1.changeCurrent(2);
    multi1.loadLayer(jpeg);
    multi1.changeCurrent(1);
    multi1.duplicateCurrent();

    assertEquals(Arrays.asList(png, jpeg, png), multi1.getImageFilenames());
  }
}