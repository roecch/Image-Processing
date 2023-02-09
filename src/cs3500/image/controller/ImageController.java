package cs3500.image.controller;

import cs3500.image.model.IManipulation;
import cs3500.image.model.IManipulation.Manipulation;
import cs3500.image.model.MultiLayerImage;
import cs3500.image.view.IViewListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for the simple image controller. Uses a single MultiLayerImage object for the model.
 */

public class ImageController implements IController {

  private final MultiLayerImage model;
  private Scanner scanner;

  /**
   * Constructor for ImageController (text-based).
   *
   * @param model the image model
   * @param rd    the Readable object
   */

  public ImageController(MultiLayerImage model, Readable rd) {
    if (rd == null || model == null) {
      throw new IllegalArgumentException("Invalid parameter for Image Controller Constructor");
    }

    this.model = model;
    this.scanner = new Scanner(rd);
  }

  /**
   * Prompts the user if they want to run a script or manually put in commands.
   */

  public void run() {
    System.out.println("Press 1 to run a script. Press 2 to run program via manual input.");
    while (scanner.hasNext()) {
      try {
        int command = scanner.nextInt();
        if (command == 1) {
          System.out.println("Type file name. ");
          commandByFile();
          return;
        } else if (command == 2) {
          commandByUser();
          return;
        } else {
          System.out.println("Try again. Invalid input. ");
        }
      } catch (InputMismatchException e) {
        System.out.println("Try again. Invalid input. ");
      }
    }
  }

  /**
   * Reads and attempts to execute commands given in file form by the user.
   */

  public void commandByFile() {
    while (scanner.hasNext()) {
      String name = scanner.next();
      try {
        scanner = new Scanner(new FileInputStream(name));
      } catch (FileNotFoundException e) {
        System.out.println("File " + name + " not found! Try again. ");
      }
      break;
    }
    this.commandReader();
  }

  public void commandByClickedFile(String path) {
    try {
      scanner = new Scanner(new FileInputStream(path));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + path + " not found! Try again. ");
    }
    this.commandReader();
  }

  /**
   * Reads and executes commands given by the user through the console.
   */
  public void commandByUser() {
    System.out.println("Manual input: ");
    try {
      this.commandReader();
    } catch (IllegalArgumentException e) {
      this.commandReader();
    }

  }

  /**
   * Reads user commands.
   */
  private void commandReader() {
    int lineCount = 0;
    while (scanner.hasNext()) {
      String str = scanner.nextLine();

      if (str.contains("#")) {
        str = str.substring(0, str.indexOf("#"));
      }

      String effect;
      String effectedItem = null;

      if (getCommandEffect(str) != null) {
        try {
          this.model.effectLayer(getCommandEffect(str));
        } catch (NullPointerException n) {
          System.out.println("Current Layer is null: Please load image on this layer.");
        }
      } else {
        try {
          str = str.trim();

          int index = str.lastIndexOf(" ");

          if (index > -1) {
            effect = str.substring(0, index).replace(" ", "");
            effectedItem = str.substring(index + 1).replace(" ", "");
          } else {
            effect = str.replace(" ", "");
          }

          switch (effect.toLowerCase()) {
            case "createlayer": {
              this.model.createBlankLayer(this.getCommandNum(effectedItem.toLowerCase()));
              break;
            }
            case "current": {
              this.model.changeCurrent(this.getCommandNum(effectedItem.toLowerCase()));
              break;
            }
            case "load": {
              this.model.loadLayer(effectedItem);
              break;
            }
            case "save": {
              this.model.exportFirstNonInvis();
              break;
            }
            case "delete": {
              this.model.remove(this.getCommandNum(effectedItem.toLowerCase()));
              break;
            }
            case "invisible": {
              this.model.makeCurrentInvis(this.getCommandNum(effectedItem.toLowerCase()));
              break;
            }
            case "visible": {
              this.model.makeCurrentVis(this.getCommandNum(effectedItem.toLowerCase()));
              break;
            }
            case "duplicate": {
              this.model.duplicateCurrent();
              break;
            }
            case "exportall": {
              this.model.export(effectedItem);
              break;
            }
            default: {
              throw new IllegalArgumentException("Invalid image command at line "
                  + lineCount + ". Please retry.");
            }
          }
        } catch (NullPointerException | IndexOutOfBoundsException n) {
          throw new IllegalArgumentException("Invalid image command at line "
              + lineCount + ": " + n.getMessage() + ". Please retry.");
        }
      }
    }
    lineCount++;
  }

  /**
   * Attempts to match the user command with a valid type of image manipulation, returns null
   * otherwise.
   */
  private IManipulation getCommandEffect(String commandEffect) {
    for (Manipulation m : Manipulation.values()) {
      if (m.getString().equals(commandEffect)) {
        return m.getManipulation();
      }
    }
    return null;
  }

  /**
   * Converts numbers in word form to their values as ints.
   *
   * @param layerNum the layer number in string form
   * @return the int form of layerNum
   */
  private int getCommandNum(String layerNum) {
    return Numbers.getWordToNum(layerNum);
  }
}