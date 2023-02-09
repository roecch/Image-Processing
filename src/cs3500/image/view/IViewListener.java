package cs3500.image.view;

public interface IViewListener {
  void handleLoadImageEvent(String path);

  void handleSaveEvent();

  void handleDuplicateEvent();

  void handleInvisibleEvent();

  void handleVisibleEvent();

  void handleCreateBlankEvent(int num);

  void handleEffectEvent(String effect) throws IllegalArgumentException;

  void handleExportAllEvent(String prefix);

  void handleChangeCurrentEvent(int newCurrent);

  void handleDeleteEvent(int current);

  void handleScriptEvent(String path);

}
