package cs3500.image.controller;

import cs3500.image.model.IManipulation.Manipulation;
import cs3500.image.model.MultiLayerImage;
import cs3500.image.view.IViewListener;
import cs3500.image.view.SwingGraphic;
import java.io.StringReader;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class for the Graphic User Interface image controller.
 * Uses a single MultiLayerImage object for the model.
 */

public class GUIController implements IViewListener, IController {

  private final MultiLayerImage model;
  private final SwingGraphic view;

  /**
   * Constructor for GUIController.
   *
   * @param model the image model
   */

  public GUIController(MultiLayerImage model) {
    this.model = Objects.requireNonNull(model);
    this.view = new SwingGraphic(this.model);
    this.view.registerViewEventListener(this);
  }

  /**
   *
   */

  @Override
  public void run() {
    SwingGraphic.setDefaultLookAndFeelDecorated(false);

    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

  }


  @Override
  public void handleLoadImageEvent(String path) {
    try {
      model.loadLayer(path);
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleSaveEvent() {
    try {
      this.model.exportFirstNonInvis();
      view.displayMessage("Successfully saved this image.");
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleDuplicateEvent() {
    try {
      this.model.duplicateCurrent();
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleInvisibleEvent() {
    try {
      this.model.makeCurrentInvis(this.model.getCurrent() + 1);
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleVisibleEvent() {
    try {
      this.model.makeCurrentVis(this.model.getCurrent() + 1);
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }

  }

  @Override
  public void handleCreateBlankEvent(int num) {
    try {
      this.model.createBlankLayer(num);
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleExportAllEvent(String prefix) {
    try {
      this.model.export(prefix);
      view.displayMessage("Successfully exported all. See file res//" + prefix + ".txt for "
          + "locations of ");
    } catch (Exception e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleChangeCurrentEvent(int newCurrent) {
    try {
      model.changeCurrent(newCurrent);
    } catch (Exception e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleDeleteEvent(int current) {
    try {
      model.remove(current);
    } catch (Exception e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleEffectEvent(String effect) throws IllegalArgumentException {
    boolean contains = false;
    Manipulation m = null;

    for (int i = 0; i < Manipulation.values().length; i++) {
      contains = contains || effect.equals(Manipulation.values()[i].getString());

      if (contains) {
        m = Manipulation.values()[i];
        break;
      }
    }

    if (!contains || m == null) {
      throw new IllegalArgumentException("Not found.");
    }

    try {
      model.effectLayer(m.getManipulation());
    } catch (IllegalArgumentException e) {
      view.errorPopUp(e.getMessage());
    }
  }

  @Override
  public void handleScriptEvent(String path) {
    try {
      ImageController imgCont = new ImageController(this.model, new StringReader(""));
      imgCont.commandByClickedFile(path);
      view.displayMessage("Script fully executed.");
    } catch (Exception e) {
      view.errorPopUp(e.getMessage());
    }
  }
}
