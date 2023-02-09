package cs3500.image.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

/**
 * Class for the file chooser.
 * Used to allow user to pick file out of computer files.
 */

public class FileChooser extends JFrame implements ActionListener {

  private final JLabel label;
  private final SwingGraphic ui;
  private final List<IViewListener> viewListeners;

  /**
   * Constructor for ImageController (text-based).
   *
   * @param ui the SwingGraphic view
   * @param listeners the Controllers to effect that are effected by UI
   */

  FileChooser(SwingGraphic ui, List<IViewListener> listeners) {
    this.label = new JLabel("no file selected");
    this.viewListeners = Objects.requireNonNull(listeners);
    this.ui = Objects.requireNonNull(ui);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int r = j.showOpenDialog(null);

    if (r == JFileChooser.APPROVE_OPTION) {
      // set the label to the path of the selected file
      label.setText(j.getSelectedFile().getAbsolutePath());
      switch (e.getActionCommand()) {
        case "open":
          emitOpenToViewListener(label.getText());
          break;
        case "script":
          emitScriptToViewListener(label.getText());
          break;
        default:
          throw new UnsupportedOperationException();
      }
      this.ui.drawImageLayers();
      this.ui.revalidate();
      this.ui.repaint();
    }
    else {
      label.setText("the user cancelled the operation");
    }
  }

  /**
   * Allows controllers to read script to effect state of UI
   *
   * @param path the string of the path of the file
   */

  protected void emitScriptToViewListener(String path) {
    for (IViewListener listener : this.viewListeners) {
      try {
        listener.handleScriptEvent(path);
      } catch (Exception e) {
        e.getStackTrace();
      }
    }
  }

  /**
   * Allows controllers to open file to effect state of UI
   *
   * @param path the string of the path of the file
   */

  protected void emitOpenToViewListener(String path) {
    for (IViewListener listener : this.viewListeners) {
      try {
        listener.handleLoadImageEvent(path);
      } catch (IllegalArgumentException e) {
        e.getStackTrace();
      }
    }
  }
}


