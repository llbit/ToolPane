package se.llbit.fx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class ToolTabSkin extends StackPane {

  private final Node content;
  private final ToggleButton header;
  private final ToolTab tab;

  public ToolTabSkin(ToolTab tab) {
    this.tab = tab;
    header = new ToggleButton(tab.getTitle());
    header.setTextAlignment(TextAlignment.LEFT);
    header.setAlignment(Pos.BASELINE_LEFT);
    header.setMaxWidth(Double.MAX_VALUE);
    header.setStyle("-fx-background-radius: 0");
    setPrefWidth(Double.MAX_VALUE);
    setMaxWidth(Double.MAX_VALUE);
    header.setSelected(tab.getSelected());
    header.selectedProperty().addListener(
        (observable, oldValue, selected) -> tab.setSelected(selected));
    content = tab.getContent();
    content.setVisible(tab.getSelected());
    getChildren().addAll(header, content);
    tab.selectedProperty().addListener((observable, prev, selected) -> {
      content.setVisible(selected);
      requestLayout();
    });
    setPrefWidth(-1);
  }

  @Override protected void layoutChildren() {
    double w = getWidth();
    header.resizeRelocate(0, 0, w, header.prefHeight(w));
    content.resizeRelocate(0, header.prefHeight(-1), w, content.prefHeight(w));
  }

  @Override protected double computePrefWidth(double height) {
    return snapSize(Math.max(header.prefWidth(-1), content.prefWidth(-1)));
  }

  @Override protected double computePrefHeight(double width) {
    double height = header.prefHeight(-1);
    if (tab.getSelected()) {
      height += content.prefHeight(-1);
    }
    return snapSize(height);
  }

  @Override protected double computeMinWidth(double height) {
    return computePrefWidth(height);
  }

  @Override protected double computeMinHeight(double width) {
    return computePrefHeight(width);
  }

  @Override protected double computeMaxWidth(double height) {
    return computePrefWidth(height);
  }

  @Override protected double computeMaxHeight(double width) {
    return computePrefHeight(width);
  }
}
