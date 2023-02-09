package cs3500.image.view;

import static org.junit.Assert.assertEquals;

import cs3500.image.model.IImage;
import cs3500.image.model.MultiLayerImage;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ImageMouseAdapter: Ensures that the methods in the
 * ImageMouseAdapter class are behaving correctly.
 */

public class ImageMouseAdapterTest {
  MultiLayerImage image;
  SwingGraphic gui;
  JLabel[] labels;
  ImageMouseAdapter mouseAdapt;

  @Before
  public void setUp() {
    image = new MultiLayerImage();
    image.createBlankLayer(1);
    image.loadLayer("res\\originalFlowers.jpg");
    image.createBlankLayer(2);
    image.changeCurrent(2);
    image.loadLayer("res\\originalFlowers.jpg");
    gui = new SwingGraphic(image);

    List<IImage> images = this.image.getImageLayers();
    labels = new JLabel[image.getImageFilenames().length];
    for (int i = 0; i < images.size(); i++) {
      labels[i] = new JLabel();

      if (images.get(i) != null) {
        ImageIcon icon = new ImageIcon(images.get(i).constructBuffered());
        labels[i].setIcon(icon);
        labels[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
      }
    }
    mouseAdapt = new ImageMouseAdapter(gui, labels);
  }

  /**
   * Tests for valid constructor inputs.
   */

  @Test
  public void constructorValid() {
    new ImageMouseAdapter(gui, labels);
  }

  /**
   * Tests for invalid view for constructor.
   */

  @Test (expected = NullPointerException.class)
  public void constructorInvalidView() {
    new ImageMouseAdapter(null, labels);
  }

  /**
   * Tests for invalid JLabel array for constructor.
   */

  @Test (expected = NullPointerException.class)
  public void constructorInvalidLabels() {
    new ImageMouseAdapter(gui, null);
  }

  /**
   * Tests for mouseClicked with MouseEvent of command source of first in JLabel array.
   */

  @Test
  public void testMouseClicked() {
    mouseAdapt.mouseClicked(new MouseEvent(labels[0], 0, 19203L,
        2, 543,432, 0, true, 1));

    assertEquals(Color.RED, ((LineBorder)labels[0].getBorder()).getLineColor());
  }

  /**
   * Tests for mouseClicked with MouseEvent of command source of non-first in JLabel array.
   */

  @Test
  public void testMouseNotFirstClicked() {
    mouseAdapt.mouseClicked(new MouseEvent(labels[1], 0, 19203L,
        2, 543,432, 0, true, 1));

    assertEquals(Color.RED, ((LineBorder)labels[1].getBorder()).getLineColor());
  }
}