package cs3500.image.view;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.image.model.IViewModel;
import cs3500.image.model.MultiLayerImage;
import cs3500.image.view.IImageView;
import cs3500.image.view.SwingGraphic;
import java.awt.Color;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

public class SwingGraphicTest {
  IViewModel model;
  SwingGraphic swing;
  @Before
  public void setup() {
    model = new MultiLayerImage();
    swing = new SwingGraphic(model);
  }

  @Test
  public void testErrorPopup() {
    swing.errorPopUp("test");
//    assertTrue(swing.);
  }

  @Test
  public void displayMessage() {

  }

  @Test
  public void testActionPerformedDuplicate() {

  }

  @Test
  public void testActionPerformedInvisible() {

  }

  @Test
  public void testActionPerformedVisible() {

  }

  @Test
  public void testActionPerformedApply() {


  }

  @Test
  public void testActionPerformedExportAll() {

  }

  @Test
  public void testActionPerformedSave() {

  }

  @Test
  public void testActionPerformedBlank() {

  }

  @Test
  public void testActionPerformedDelete() {

  }

  @Test(expected = UnsupportedOperationException.class)
  public void testActionPerformedError() {
//    ActionEvent event = new ActionEvent(sw);
//    this.swing.actionPerformed(new ActionEvent("fails"));
  }
}
