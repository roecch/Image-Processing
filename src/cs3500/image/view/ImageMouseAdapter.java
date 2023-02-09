package cs3500.image.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Class for the Image Mouse Adapter.
 * Purpose of highlighting the chosen image in the panel.
 */

public class ImageMouseAdapter extends MouseAdapter {

  private SwingGraphic view;
  private JLabel[] imageLabel;

  /**
   * The constructor of ImageMouseAdapter.
   */

  public ImageMouseAdapter(SwingGraphic view, JLabel[] imageLabel) {
    this.imageLabel = Objects.requireNonNull(imageLabel);
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    for (int x = 0; x < imageLabel.length; x++) {
      imageLabel[x].setBorder(BorderFactory.createEmptyBorder());
      if (e.getSource() == imageLabel[x]) {
        imageLabel[x].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        view.emitChangeCurrent(x + 1);
      }
    }
  }
}