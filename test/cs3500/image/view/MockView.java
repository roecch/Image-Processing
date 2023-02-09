package cs3500.image.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for a mock view.
 * Purpose for testing IImageView class used by views.
 */

public class MockView implements IImageView {
  private final List<IViewListener> viewListeners;

  /**
   * The constructor for MockView.
   */

  MockView() {
    viewListeners = new ArrayList<>();
  }

  @Override
  public void registerViewEventListener(IViewListener listener) {
    this.viewListeners.add(Objects.requireNonNull(listener));
  }

  @Override
  public void errorPopUp(String message) {
    throw new IllegalArgumentException(message);
  }

  @Override
  public void displayMessage(String message) {
    throw new IllegalArgumentException(message);
  }
}
