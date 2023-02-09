package cs3500.image.view;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

/**
 * Class for a mock file chooser.
 * Purpose for testing FileChooser class.
 */

public class MockFileChooser extends FileChooser{

  private final JLabel label;
  private final List<IViewListener> viewListeners;

  /**
   * The constructor for MockFileChooser.
   */

  MockFileChooser(SwingGraphic ui, List<IViewListener> listeners) {
    super(ui, listeners);
    this.label = new JLabel("no file selected");
    this.viewListeners = Objects.requireNonNull(listeners);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // if the user presses the save button show the save dialog
    String com = e.getActionCommand().toLowerCase();

    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    switch (e.getActionCommand()) {
      case "open":
        label.setText("src\\blue.jpg");
        emitOpenToViewListener(label.getText());
        break;
      case "script":
        label.setText("res\\ExampleScript1");
        emitScriptToViewListener(label.getText());
        break;
      default:
        throw new UnsupportedOperationException();
    }
  }

  @Override
  protected void emitScriptToViewListener(String path) {
    for (IViewListener listener : this.viewListeners) {
      try {
        listener.handleScriptEvent(path);
      } catch (Exception e) {
        e.getStackTrace();
      }
    }
  }

  @Override
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
