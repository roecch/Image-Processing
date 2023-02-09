package cs3500.image.model;

/**
 * A manipulation to be applied to an image.
 */
public interface IManipulation {

  /**
   * All current types of image manipulation that can be done.
   */

  enum Manipulation {

    BLUR("blur", new BlurFilter()), SHARPEN("sharpen", new SharpenFilter()),
    GREYSCALE("greyscale", new GreyscaleTrans()), SEPIA("sepia", new SepiaTrans()),
    MOSAIC("mosaic", new MosaicEffect());

    private final IManipulation mani;
    private final String type;

    Manipulation(String type, IManipulation mani) {
      this.type = type;
      this.mani = mani;
    }

    /**
     * Gets the string of the manipulation.
     * @return the String associated with the manipulation
     */

    public String getString() {
      return this.type;
    }

    /**
     * Gets the manipulation class of the manipulation.
     * @return the manipulation class associated with the manipulation
     */

    public IManipulation getManipulation() {
      return this.mani;
    }
  }

  /**
   * Applies this manipulation to the given image.
   * @param image the image to apply this effect to
   */
  void apply(IImage image);

  /**
   * Clamps the individual pixels in this image to conform with the expected minimum and maximum
   * values of each channel.
   * @param image the image to clamp the pixel's values of
   */
  void clamp(IImage image);
}
