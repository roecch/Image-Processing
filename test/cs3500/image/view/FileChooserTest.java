package cs3500.image.view;

import static org.junit.Assert.assertEquals;

import cs3500.image.model.MultiLayerImage;
import cs3500.image.model.PixelImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for FileChooser: Ensures that the methods in the
 * FileChooser class are behaving correctly.
 */

public class FileChooserTest {
  MultiLayerImage image;
  SwingGraphic gui;
  ArrayList<IViewListener> listeners;
  FileChooser fChooser;

  @Before
  public void setUp() {
    image = new MultiLayerImage();
    image.createBlankLayer(1);
    image.createBlankLayer(2);
    image.changeCurrent(2);
    gui = new SwingGraphic(image);

    listeners = new ArrayList<>();
    listeners.add(new MockController(image));

    fChooser = new MockFileChooser(gui, listeners);
  }

  /**
   * Tests actionPerformed method for ability to affect image with valid command.
   */

  @Test
  public void actionPerformed() {
    fChooser.actionPerformed(new ActionEvent(new JLabel(), 0, "open"));
    assertEquals(new PixelImpl(15,91,201), image.getImageLayers().get(1).getPixels()[0][0]);
  }

  /**
   * Tests actionPerformed method with invalid command.
   */

  @Test (expected = UnsupportedOperationException.class)
  public void actionPerformedInvalid() {
    fChooser.actionPerformed(new ActionEvent(new JLabel(), 0, "bop"));
  }

  /**
   * Tests emitOpenToViewListener method.
   * Since method is only accessed when showOpenDialog is occurs, string is never invalid.
   */

  @Test
  public void emitScriptToViewListener() {
    image = new MultiLayerImage();
    gui = new SwingGraphic(image);
    listeners = new ArrayList<>();
    listeners.add(new MockController(image));
    fChooser = new MockFileChooser(gui, listeners);

    fChooser.emitScriptToViewListener("res\\ExampleScriptNoSave");
    assertEquals(new PixelImpl(94,99,93), image.getImageLayers().get(1).getPixels()[0][0]);
  }

  /**
   * Tests emitOpenToViewListener method.
   * Since method is only accessed when showOpenDialog is occurs, string is never invalid.
   */

  @Test
  public void emitOpenToViewListener() {
    fChooser.emitOpenToViewListener("src\\blue.jpg");
    assertEquals(new PixelImpl(15,91,201), image.getImageLayers().get(1).getPixels()[0][0]);
  }
}