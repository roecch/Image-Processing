package cs3500.image.view;

import org.junit.Test;

/**
 * Tests for IImageView: Ensures that the methods in the
 * IImageView class are behaving correctly.
 */

public class IImageViewTest {
  IImageView view = new MockView();

  /**
   * Tests for registerViewEventListener with null input.
   */

  @Test (expected = NullPointerException.class)
  public void testRegisterViewEventListenerNull() {
    view.registerViewEventListener(null);
  }

  /**
   * Tests for errorPopUp.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testErrorPopUp() {
    view.errorPopUp("Error");
  }

  /**
   * Tests for displayMessage.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testDisplayMessage() {
    view.errorPopUp("Success");
  }
}