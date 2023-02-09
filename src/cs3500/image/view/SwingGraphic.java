package cs3500.image.view;

import cs3500.image.model.IImage;
import cs3500.image.model.IViewModel;
import cs3500.image.model.IManipulation.Manipulation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * This class opens the main window, that has different elements illustrated in it. It also doubles
 * up as all the listeners for simplicity. Such a design is not recommended in general.
 */

public class SwingGraphic extends JFrame implements IImageView, ActionListener, ItemListener {

  private final JPanel mainPanel;
  private JButton deleteCurrentButton;
  private JPanel imagePanel;
  private final JScrollPane mainScrollPane;
  private JButton duplicateButton;
  private JButton invisibleButton;
  private JButton visibleButton;
  private JButton exportAllButton;
  private JButton createBlankButton;
  private final List<IViewListener> viewListeners;
  private final IViewModel model;
  private JLabel[] imageLabel;
  private final GridBagConstraints gbc = new GridBagConstraints();
  private final GridBagLayout gridBag = new GridBagLayout();
  private JLabel comboboxDisplay;
  private final Color DARK_GRAY = new Color(60, 60, 60);
  private final int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment()
      .getMaximumWindowBounds().width;
  private final int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment()
      .getMaximumWindowBounds().height;
  private JComboBox<String> combobox;
  private List<IImage> images;

  /**
   * The constructor of SwingGraphic.
   * @param model the image model
   */

  public SwingGraphic(IViewModel model) {
    super();
    this.model = Objects.requireNonNull(model);
    viewListeners = new ArrayList<>();

    setTitle("Image Processing");
    setSize(WIDTH, HEIGHT);

    mainPanel = new JPanel();
    mainPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setBackground(DARK_GRAY);
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    this.insertOpenSaveButtons();
    this.drawImageLayerPanels();
    this.insertEffectOptions();
  }

  /**
   * Creates the combobox and buttons of the available effects for the model
   * and adds it to the main window.
   */

  private void insertEffectOptions() {
    // combobox effects
    JPanel comboboxWAllEffects = new JPanel();
    comboboxWAllEffects.setLayout(new BoxLayout(comboboxWAllEffects, BoxLayout.PAGE_AXIS));
    comboboxWAllEffects.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    JPanel comboboxWApplyButton = new JPanel();
    comboboxWApplyButton.setLayout(new FlowLayout(FlowLayout.LEFT));
    comboboxWAllEffects.setBorder(BorderFactory.createTitledBorder(null,
        "Images Effects", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
        null, Color.WHITE));
    comboboxWApplyButton.setBackground(DARK_GRAY);
    comboboxWAllEffects.setBackground(DARK_GRAY);
    mainPanel.add(comboboxWAllEffects);

    comboboxDisplay = new JLabel("Which effect do you want to apply?");
    comboboxDisplay.setForeground(Color.white);
    comboboxWApplyButton.add(comboboxDisplay);
    String[] options = new String[Manipulation.values().length];

    for (int i = 0; i < options.length; i++) {
      options[i] = Manipulation.values()[i].getString();
    }

    combobox = new JComboBox<String>();
    //the event listener when an option is selected
    combobox.addItemListener(this);
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }

    comboboxWApplyButton.add(combobox);
    JButton applyButton = new JButton("Apply");
    applyButton.setActionCommand("apply");
    applyButton.addActionListener(this);
    comboboxWApplyButton.add(applyButton);

    comboboxWAllEffects.add(comboboxWApplyButton);

    JPanel otherEffects = new JPanel();
    otherEffects.setLayout(new FlowLayout(FlowLayout.LEFT));
    otherEffects.setBackground(DARK_GRAY);

    // new buttons
    duplicateButton = new JButton("Duplicate");
    duplicateButton.addActionListener(this);
    duplicateButton.setActionCommand("duplicate");
    otherEffects.add(duplicateButton);

    visibleButton = new JButton("Make Current Visible");
    visibleButton.addActionListener(this);
    visibleButton.setActionCommand("visible");
    otherEffects.add(visibleButton);

    invisibleButton = new JButton("Make Current Invisible");
    invisibleButton.addActionListener(this);
    invisibleButton.setActionCommand("invisible");
    otherEffects.add(invisibleButton);

    createBlankButton = new JButton("Add blank layer");
    createBlankButton.addActionListener(this);
    createBlankButton.setActionCommand("blank");
    otherEffects.add(createBlankButton);

    deleteCurrentButton = new JButton("Delete Current");
    deleteCurrentButton.addActionListener(this);
    deleteCurrentButton.setActionCommand("delete");
    otherEffects.add(deleteCurrentButton);

    comboboxWAllEffects.add(otherEffects);
  }

  /**
   * Creates the image panel that has all the images of the model.
   */

  private void drawImageLayerPanels() {
    imagePanel = new JPanel();
    imagePanel.setBackground(DARK_GRAY);
    imagePanel.setBorder(BorderFactory.createTitledBorder(null,
        "Showing Layers", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
        null, Color.WHITE));
    gbc.insets = new Insets(20, 20, 20,20);
    imagePanel.setLayout(gridBag);
    mainPanel.add(imagePanel);
    drawImageLayers();
    }

  /**
   * Creates the images of the model.
   */

    void drawImageLayers() {
    imagePanel.removeAll();
    images = this.model.getImageLayers();
    imageLabel = new JLabel[this.model.getImageFilenames().length];
    JScrollPane[] imageScrollPane = new JScrollPane[imageLabel.length];

    for (int i = 0; i < images.size(); i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);

      if (images.get(i) != null) {
        ImageIcon icon = new ImageIcon(images.get(i).constructBuffered());
        imageLabel[i].setIcon(icon);
        imageScrollPane[i]
            .setPreferredSize(new Dimension(icon.getIconWidth() + 10, icon.getIconHeight() + 10));
        imageScrollPane[i]
            .setMaximumSize(new Dimension(WIDTH / 3, HEIGHT / 3));
        if (this.model.getInvisibleLayers().contains(i)) {
          imageLabel[i].setIcon(new ImageIcon());
        }

      }
      else {
        imageScrollPane[i].setPreferredSize(new Dimension(WIDTH / 4, HEIGHT / 4));
      }
      imageLabel[i].addMouseListener(new ImageMouseAdapter(this, imageLabel));
      imageLabel[i].setVerticalAlignment(JLabel.CENTER);
      imageLabel[i].setHorizontalAlignment(JLabel.CENTER);
      imagePanel.add(imageScrollPane[i]);
      gbc.gridy = i / 3;
      gridBag.setConstraints(imageScrollPane[i], gbc);
    }
  }

  /**
   * Creates the buttons that allow user to open and save files.
   */

  private void insertOpenSaveButtons() {
    JPanel fileChooser = new JPanel();
    fileChooser.setBackground(new Color(60, 60, 60));
    fileChooser.setMaximumSize(new Dimension(WIDTH, 40));
    fileChooser.setLayout(new FlowLayout(FlowLayout.LEFT));
    JButton buttonOpen = new JButton("Open");
    JButton buttonSave = new JButton("Save");
    JButton buttonScript = new JButton("Feed Script");


    buttonOpen.addActionListener(new FileChooser(this, this.viewListeners));
    buttonSave.addActionListener(this);
    buttonScript.addActionListener(new FileChooser(this, this.viewListeners));

    buttonOpen.setActionCommand("open");
    buttonSave.setActionCommand("save");
    buttonScript.setActionCommand("script");

    exportAllButton = new JButton("Export All");
    exportAllButton.addActionListener(this);
    exportAllButton.setActionCommand("exportAll");

    fileChooser.add(buttonOpen);
    fileChooser.add(buttonSave);
    fileChooser.add(exportAllButton);
    fileChooser.add(buttonScript);
    mainPanel.add(fileChooser);
  }

  @Override
  public void registerViewEventListener(IViewListener listener) {
    this.viewListeners.add(Objects.requireNonNull(listener));
  }

  /**
   * Duplicates the current layer for all the controllers
   * that are connected to this SwingGraphic
   */

  protected void emitDuplicateEvent() {
    for (IViewListener l : this.viewListeners) {
      l.handleDuplicateEvent();
    }
  }

  protected void emitChangeCurrent(int i) {
    for (IViewListener l : this.viewListeners) {
      l.handleChangeCurrentEvent(i);
    }
  }

  protected void emitInvisibleEvent() {
    for (IViewListener l : this.viewListeners) {
      l.handleInvisibleEvent();
    }
  }

  protected void emitVisibleEvent() {
    for (IViewListener l : this.viewListeners) {
      l.handleVisibleEvent();
    }
  }

  protected void emitEffectEvent(String effect) {
    for (IViewListener listener : this.viewListeners) {
      listener.handleEffectEvent(effect);
    }
  }

  protected void emitBlankEvent(int i) {
    for (IViewListener listener : this.viewListeners) {
      listener.handleCreateBlankEvent(i);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "duplicate":
          emitDuplicateEvent();
          break;
        case "invisible":
          emitInvisibleEvent();
          break;
        case "visible":
          emitVisibleEvent();
          break;
        case "apply":
          emitEffectEvent((String) this.combobox.getSelectedItem());
          break;
        case "exportAll":
          String prefix = JOptionPane.showInputDialog(this,
              "Enter valid suffix for file names: ", null);
          if (prefix != null) {
            emitExportAllEvent(prefix);
          } else {
            errorPopUp("Cannot have empty file suffix.");
          }
          break;
        case "save":
          emitSaveEvent();
          break;
        case "blank":
          emitBlankEvent(this.model.getImageFilenames().length + 1);
          break;
        case "delete":
          emitDeleteEvent(this.model.getCurrent());
          break;
        default:
          throw new UnsupportedOperationException();
      }
    }
    catch (UnsupportedOperationException exception) {
      throw new UnsupportedOperationException(exception.getMessage());
    }
    drawImageLayers();
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  private void emitSaveEvent() {
    for (IViewListener l : this.viewListeners) {
      l.handleSaveEvent();
    }
  }

  private void emitDeleteEvent(int current) {
    for (IViewListener l : this.viewListeners) {
      l.handleDeleteEvent(current + 1);
    }
  }

  private void emitExportAllEvent(String suffix) {
    for (IViewListener l : this.viewListeners) {
      l.handleExportAllEvent(suffix);
    }
  }

  @Override
  public void errorPopUp(String message) {
    JOptionPane.showMessageDialog(this,
        "Invalid input: " + message, "Error",  JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this,
        message, "Operation successful",  JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
  }
}
