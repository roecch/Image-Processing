package cs3500.image.view;

import cs3500.image.controller.ImageController;
import cs3500.image.model.IManipulation.Manipulation;
import cs3500.image.model.MultiLayerImage;
import java.io.StringReader;

/**
 * Class for a mock controller.
 * Purpose for testing IViewListener class used for Controllers.
 */

public class MockController implements IViewListener {
  private final MultiLayerImage model;
  private final SwingGraphic view;

  /**
   * The constructor for MockController.
   */

  public MockController(MultiLayerImage model) {
    this.model = model;
    this.view = new SwingGraphic(this.model);
    this.view.registerViewEventListener(this);
  }

  @Override
  public void handleLoadImageEvent(String path) {
    try {
      model.loadLayer(path);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleSaveEvent() {
    try {
      this.model.exportFirstNonInvis();
      view.displayMessage("Successfully saved this image.");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleDuplicateEvent() {
    try {
      this.model.duplicateCurrent();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleInvisibleEvent() {
    try {
      this.model.makeCurrentInvis(this.model.getCurrent() + 1);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleVisibleEvent() {
    try {
      this.model.makeCurrentVis(this.model.getCurrent() + 1);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }

  }

  @Override
  public void handleCreateBlankEvent(int num) {
    try {
      this.model.createBlankLayer(num);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleExportAllEvent(String prefix) {
    try {
      this.model.export(prefix);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleChangeCurrentEvent(int newCurrent) {
    try {
      model.changeCurrent(newCurrent);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void handleDeleteEvent(int current) {
    try {
      model.remove(current);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
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
      throw new IllegalArgumentException("Invalid Effect Name");
    }
  }

  @Override
  public void handleScriptEvent(String path) {
    try {
      ImageController imgCont = new ImageController(this.model, new StringReader(""));
      imgCont.commandByClickedFile(path);
    } catch (Exception e) {
      throw new IllegalArgumentException("Script cannot be fully executed");
    }
  }
}
