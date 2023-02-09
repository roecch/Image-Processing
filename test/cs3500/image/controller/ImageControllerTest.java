package cs3500.image.controller;

import static org.junit.Assert.assertEquals;

import cs3500.image.model.MultiLayerImage;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;

/**
 * Tests for ImageController: Ensures that the methods in the
 * ImageController class are behaving correctly.
 */

public class ImageControllerTest {

  Color[] colors;

  @Before
  public void setup() {
    colors = new Color[1];
    colors[0] = Color.GREEN;
  }

  @Test
  public void testMoveCurrent() {
    Readable rd = new StringReader("create layer one\n create layer    two"
        + "\n current one\nload src\\blue.jpg\nsepia\ncurrent one\nload src\\blue.jpg");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();

    assertEquals("15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n"
        + "15 91 201    15 91 201    15 91 201    15 91 201    15 91 201    \n" , image.toString());
  }

  @Test
  public void testApplySepia() {
    Readable rd = new StringReader("create layer one\nload src\\blue.png\nsepia");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();

    assertEquals(
        "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
        + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
        + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
        + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n"
        + "114 101 79    114 101 79    114 101 79    114 101 79    114 101 79    \n",
        image.toString());
  }

  @Test
  public void testLoadLayer() {
    Readable rd = new StringReader("create layer one\n load src\\blue.png");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();

    MultiLayerImage compareImg = new MultiLayerImage();
    compareImg.createBlankLayer(1);
    compareImg.loadLayer("src\\blue.png");

    assertEquals(compareImg.toString(), image.toString());
    assertEquals(5, image.getPixels().length);
    assertEquals(5, image.getPixels()[0].length);
  }

  @Test
  public void testSaveLayer() {
    Readable rd = new StringReader("create layer one\n load src\\testing.ppm  \n "
        + "save  res\\originalFlowers.ppm");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();

    MultiLayerImage compareImg = new MultiLayerImage();
    compareImg.createBlankLayer(1);
    compareImg.loadLayer("src\\testing.ppm");

    boolean check = true;
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < 5; y++) {
        if (image.getImageLayers().get(0) != null && compareImg.getImageLayers().get(0) != null) {
          check &= image.getImageLayers().get(0).getPixels()[x][y]
              .equals(compareImg.getImageLayers().get(0).getPixels()[x][y]);
        }
      }
    }
    assertEquals(true, check);
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testSetToInvalidCurrent() {
    Readable rd = new StringReader("create layer one\nload src\\blue.jpg\ncurrent four");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testLoadInvalidFile() {
    Readable rd = new StringReader("create layer one\n load src\\Koala.ppm");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testInvalidCommand() {
    Readable rd = new StringReader("create aasdf one\n load src\\Koala.ppm");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();
  }

  @Test
  public void testDuplicateLayer() {
    Readable rd = new StringReader("create layer one\n load src\\blue.png \nduplicate");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    MultiLayerImage compareImg = new MultiLayerImage();
    compareImg.createBlankLayer(1);
    compareImg.loadLayer("src\\blue.png");
    compareImg.duplicateCurrent();
    c.commandByUser();

    assertEquals(compareImg.toString(), image.toString());
    assertEquals(5, image.getPixels().length);
    assertEquals(5, image.getPixels()[0].length);
  }

  @Test
  public void testIgnoreComment() {
    Readable rd = new StringReader("create layer one#ueiwuioqwueowqeio \n load src\\blue.png");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    MultiLayerImage compareImg = new MultiLayerImage();
    compareImg.createBlankLayer(1);
    compareImg.loadLayer("src\\blue.png");
    c.commandByUser();

    assertEquals(compareImg.toString(), image.toString());
    assertEquals(5, image.getPixels().length);
    assertEquals(5, image.getPixels()[0].length);
  }

  @Test
  public void testReadFile() {
    Readable rd = new StringReader("create layer one\n load src\\blue.png");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    MultiLayerImage compareImg = new MultiLayerImage();
    compareImg.createBlankLayer(1);
    compareImg.loadLayer("src\\blue.png");
    c.commandByUser();

    assertEquals(compareImg.toString(), image.toString());
    assertEquals(5, image.getPixels().length);
    assertEquals(5, image.getPixels()[0].length);
  }

  @Test
  public void testMakeInvisible() {
    Readable rd = new StringReader("create layer one\n load src\\blue.png\n"
        + "invisible one #make first layer (topmost) invisible\n");
    MultiLayerImage image = new MultiLayerImage();
    ImageController c = new ImageController(image, rd);
    c.commandByUser();

    MultiLayerImage model = new MultiLayerImage();
    model.createBlankLayer(1);
    model.loadLayer("src\\blue.png");
    model.makeCurrentInvis(1);

    boolean check = true;
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < 5; y++) {
        if (image.getImageLayers().get(0) != null && model.getImageLayers().get(0) != null) {
          check &= image.getImageLayers().get(0).getPixels()[x][y]
              .equals(model.getImageLayers().get(0).getPixels()[x][y]);
        }
      }
    }
    assertEquals(true, check);
  }


  @Test
  public void testExportAll() {
    ImageController c = new ImageController(new MultiLayerImage(),
        new StringReader("2\ncreate layer one\nload layer res\\originalFLowers.jpg\nexport all res\\try.jpg"));
    c.run();
  }
}
