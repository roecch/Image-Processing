package cs3500.image.view;

public interface IImageView {
  void registerViewEventListener(IViewListener listener);
  void errorPopUp(String message);
  void displayMessage(String message);
}
