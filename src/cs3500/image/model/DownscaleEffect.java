package cs3500.image.model;

public class DownscaleEffect extends AManipulation {
  public double factor;

  public DownscaleEffect() {
    this(0.5);
  }

  public DownscaleEffect(double factor) {
    this.factor = factor;
  }

  @Override
  public void apply(IImage image) {

  }
}
