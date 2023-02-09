package cs3500.image;

import cs3500.image.controller.GUIController;
import cs3500.image.model.MultiLayerImage;
import cs3500.image.model.SepiaTrans;
import cs3500.image.view.SwingGraphic;

/**
 * A main class for running the image manipulation program.
 */
public class Main {

  /**
   * Runs the image manipulation program.
   *
   * @param args console input
   */
  public static void main(String[] args) {

    MultiLayerImage m = new MultiLayerImage();
    m.createBlankLayer(1);
    m.loadLayer("res\\originalFlowers.jpg");
    m.effectLayer(new SepiaTrans());
    m.createBlankLayer(2);
    m.changeCurrent(2);
    m.loadLayer("res//originalFlowers.jpg");
    m.getImageFilenames();
    GUIController c = new GUIController(m);

    c.run();
  }
}
