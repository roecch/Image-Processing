package cs3500.image.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for IViewModel: Ensures that the methods in the
 * IViewModel class are behaving correctly.
 */

public class IViewModelTest {
  MultiLayerImage multiModel;
  IViewModel model;

  @Before
  public void setup() {
    multiModel = new MultiLayerImage();
    multiModel.createBlankLayer(1);
    multiModel.createBlankLayer(2);
    multiModel.createBlankLayer(3);
    multiModel.changeCurrent(1);
    multiModel.loadLayer("res//originalFlowers.jpg");
    multiModel.changeCurrent(2);
    multiModel.loadLayer("res//originalFlowers.jpg");
    multiModel.makeCurrentInvis(2);
    model = multiModel;
  }

  /**
   * Tests for getImageFilenames.
   */

  @Test
  public void getImageFilenames() {
    assertEquals("res//originalFlowers.jpg", model.getImageFilenames()[0]);
    assertEquals("res//originalFlowers.jpg", model.getImageFilenames()[1]);
    assertEquals("", model.getImageFilenames()[2]);
  }

  /**
   * Tests for getImageLayers.
   */

  @Test
  public void getImageLayers() {
    assertEquals(new JPGImage("res//originalFlowers.jpg").getPixels()[0][0],
        model.getImageLayers().get(0).getPixels()[0][0]);
    assertEquals(new JPGImage("res//originalFlowers.jpg").getPixels()[0][0],
        model.getImageLayers().get(1).getPixels()[0][0]);
    assertEquals(null, model.getImageLayers().get(2));
  }

  /**
   * Tests for getInvisibleLayers.
   */

  @Test
  public void getInvisibleLayers() {
    Integer[] test = new Integer[1];
    test[0] = 1;
    assertEquals(test[0], model.getInvisibleLayers().get(0));
  }

  /**
   * Tests for getCurrent.
   */

  @Test
  public void getCurrent() {
    assertEquals(1, model.getCurrent());
  }
}