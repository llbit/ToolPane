package se.llbit.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class ToolPane extends Control {
  private ObservableList<ToolTab> tabs = FXCollections.observableArrayList();

  public ToolPane() {
    getStyleClass().setAll("tool-pane");
  }

  @Override protected Skin<?> createDefaultSkin() {
    return new ToolPaneSkin(this);
  }

  public ObservableList<ToolTab> getTabs() {
    return tabs;
  }
}
