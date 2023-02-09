package cs3500.image.view;

import static org.junit.Assert.assertEquals;

import cs3500.image.model.MultiLayerImage;
import cs3500.image.model.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for IViewListener: Ensures that the methods in the
 * IViewListener class are behaving correctly.
 */

public class IViewListenerTest {
  MultiLayerImage img;
  IViewListener mockListener;

  @Before
  public void setUp() {
    img = new MultiLayerImage();
    img.createBlankLayer(1);
    img.loadLayer("res\\originalFlowers.jpg");
    img.createBlankLayer(2);
    mockListener = new MockController(img);
  }

  /**
   * Tests for handleEffectEvent with valid effect.
   */

  @Test
  public void testHandleEffectEvent(){
    mockListener.handleEffectEvent("sharpen");
    assertEquals(new PixelImpl(209, 219, 203),
        img.getImageLayers().get(0).getPixels()[0][0]);
  }

  /**
   * Tests for handleEffectEvent with invalid effect.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testHandleEffectEventInvalid(){
    mockListener.handleEffectEvent("blend");
  }

  /**
   * Tests for handleExportAllEvent with invalid string.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testHandleExportAllEventInvalid(){
    mockListener.handleExportAllEvent("pic");
  }

  /**
   * Tests for handleChangeCurrentEvent with valid integer.
   */

  @Test
  public void testHandleChangeCurrentEvent(){
    mockListener.handleChangeCurrentEvent(2);
    assertEquals(1, img.getCurrent());
  }

  /**
   * Tests for handleChangeCurrentEvent with invalid integer.
   */

  @Test (expected = Exception.class)
  public void testHandleChangeCurrentEventInvalid(){
    mockListener.handleChangeCurrentEvent(0);
  }

  /**
   * Tests for handleDeleteEvent with valid integer and multiple calls.
   */

  @Test
  public void testHandleDeleteEventMulti(){
    mockListener.handleDeleteEvent(1);
    mockListener.handleDeleteEvent(1);
    assertEquals(0, img.getImageFilenames().length);
  }

  /**
   * Tests for handleDeleteEvent with valid integer.
   */

  @Test
  public void testHandleDeleteEvent(){
    mockListener.handleDeleteEvent(2);
    assertEquals(1, img.getImageFilenames().length);
  }

  /**
   * Tests for handleDeleteEvent with invalid integer.
   */

  @Test (expected = Exception.class)
  public void testHandleDeleteEventInvalid(){
    mockListener.handleDeleteEvent(-1);
  }

  /**
   * Tests for handleScriptEvent with valid text file.
   */

  @Test
  public void testHandleScriptEvent(){
    img = new MultiLayerImage();
    mockListener = new MockController(img);
    mockListener.handleScriptEvent("res\\ExampleScriptNoSave");
    assertEquals(3, img.getImageFilenames().length);
  }

  /**
   * Tests for handleScriptEvent with invalid text file.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testHandleScriptEventInvalid(){
    img = new MultiLayerImage();
    mockListener = new MockController(img);
    mockListener.handleScriptEvent("res\\ExampleScriptNope");
  }
}