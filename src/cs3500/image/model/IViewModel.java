package cs3500.image.model;
import java.util.List;

public interface IViewModel {
  String[] getImageFilenames();
  List<IImage> getImageLayers();
  List<Integer> getInvisibleLayers();
  int getCurrent();
}
