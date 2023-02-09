package cs3500.image.model;

import cs3500.image.view.IViewListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * An image that consists of layers of images.
 */
public class MultiLayerImage extends AImage implements IViewModel {

  private final List<String> layerNames;
  private final List<IImage> layers;
  private final ArrayList<Integer> invisLayers;
  private int current;

  /**
   * The constructor for MultiLayerImage.
   */
  public MultiLayerImage() {
    layerNames = new ArrayList<>();
    layers = new ArrayList<>();
    this.invisLayers = new ArrayList<>();
    this.current = 0;
  }

  /**
   * Gets the list of file names.
   *
   * @return list of file names in this image
   */
  @Override
  public String[] getImageFilenames() {
    return layerNames.toArray(new String[layerNames.size()]);
  }

  /**
   * Gets the layers of this image.
   *
   * @return the layers of this image
   */
  @Override
  public List<IImage> getImageLayers() {
    return layers;
  }

  @Override
  public List<Integer> getInvisibleLayers() {
    return new ArrayList<>(this.invisLayers);
  }

  @Override
  public int getCurrent() {
    return this.current;
  }

  /**
   * Applies the image effect to the current layer.
   *
   * @param manipulation the manipulation to be applied
   */
  public void effectLayer(IManipulation manipulation) {
    manipulation.apply(this.layers.get(this.current));
  }

  /**
   * Makes the layer invisible.
   *
   * @param num the nth layer
   */
  public void makeCurrentInvis(int num) {
    invisLayers.add(num - 1);
  }

  /**
   * Makes the layer visible.
   *
   * @param num the nth layer
   */
  public void makeCurrentVis(int num) {
    List<Integer> listOfInvisLayers = (List<Integer>) invisLayers.clone();
    invisLayers.clear();
    for (Integer i : listOfInvisLayers) {
      if (i != num - 1) {
        invisLayers.add(i);
      }
    }
  }

  /**
   * Loads the image from the given file path into the layer.
   *
   * @param newLay the file path of the image
   */
  public void loadLayer(String newLay) {
    IImage replacedImage = AImage.importImage(newLay);

    if (layers.size() > 0) {
      for (int i = 0; i < layers.size(); i++) {
        if (layers.get(i) != null
            && i != this.current
            && (layers.get(i).getPixels().length != replacedImage.getPixels().length
            || layers.get(i).getPixels()[0].length != replacedImage.getPixels()[0].length)) {
          throw new IllegalArgumentException("Layers must be same dimensions as previous ones");
        }
      }
    }

    layerNames.set(current, newLay);
    layers.set(current, replacedImage);
  }

  /**
   * Creates a blank layer at the end of image's layer list.
   *
   * @param num the nth layer of the image
   */
  public void createBlankLayer(int num) {
    if (this.layers.size() + 1 == num) {
      layerNames.add("");
      layers.add(null);
    } else {
      throw new IllegalArgumentException("Invalid : "
          + "Layer Number must be one more than current size");
    }
  }

  /**
   * Changes the current.
   *
   * @param newCurr the value to change current to
   * @throws IndexOutOfBoundsException when the index is invalid
   */
  public void changeCurrent(int newCurr) {
    if (newCurr <= this.layers.size() && newCurr > 0) {
      this.current = newCurr - 1;
    } else {
      throw new IndexOutOfBoundsException("Invalid index for current.");
    }
  }

  /**
   * Removes this layer from the image.
   *
   * @param layer the position of the layer to be removed
   */
  public void remove(int layer) {
    this.layerNames.remove(layer - 1);
    this.layers.remove(layer - 1);
  }

  @Override
  public IPixel[][] getPixels() {
    try {
      return this.layers.get(current).getPixels();
    } catch (IndexOutOfBoundsException | NullPointerException e) {
      return new IPixel[1][1];
    }
  }

  /**
   * Exports the first visible layer of this multi-layer image.
   *
   * @throws IllegalArgumentException when there are no visible layers to export
   */
  public void exportFirstNonInvis() {
    int firstVis = -1;
    for (int i = this.layers.size() - 1; i >= 0; i--) {
      if (!this.invisLayers.contains(i) && this.layers.get(i) != null) {
        firstVis = i;
      }
    }

    try {
      this.layers.get(firstVis).export(this.layerNames.get(firstVis));
    }
    catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("No visible layers in this image.");
    }
  }

  /**
   * Duplicates the current layer, adds to the end of the list of layers.
   */
  public void duplicateCurrent() {
    this.layerNames.add(this.layerNames.get(current));
    this.layers.add(this.cloneImage());
  }

  /**
   * Exports every visible layer in this image.
   *
   * @param suffix the prefix of each file in this image
   */

  @Override
  public void export(String suffix) {
    if (!suffix.contains(".")) {
      throw new IllegalArgumentException("Invalid file type.");
    }
    String type = suffix.substring(suffix.indexOf(".") + 1);

    String newSuffix = suffix.substring(0, suffix.indexOf("."));
    String txt = "";

    List<IImage> images = getImageLayers();
    String[] imgNames = getImageFilenames();

    for (int i = 0; i < this.layers.size(); i++) {
      if (!(images.get(i) == null)) {
        String fileName = imgNames[i].substring(0, imgNames[i].indexOf("."))
            + "_" + newSuffix;
        String finalName = fileName + "." + type;

        boolean unique = false;
        int n = 1;
        while (!unique) {
          try {
            if (!(new File(finalName).createNewFile())) {
              finalName = fileName + "-" + n + "." + type;
            } else {
              unique = true;
            }
          }
          catch (IOException e) {
            throw new IllegalArgumentException("Unable to create file at this time.");
          }
          n++;
        }

        images.get(i).export(finalName);
        txt += "Layer " + imgNames[i] + " to " + finalName + "\n";
      }
    }

    ImageUtil.exportTxtFile(newSuffix, txt);
  }

  @Override
  public IImage cloneImage() {
    if (this.layers.get(current) == null) {
      return null;
    }
    return this.layers.get(current).cloneImage();
  }


  @Override
  public boolean equals(Object o) {
    if (o instanceof MultiLayerImage) {
      return this.layers.equals(((MultiLayerImage) o).layers)
          && this.layerNames.equals(((MultiLayerImage) o).layerNames);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.hashCode();
  }
}
