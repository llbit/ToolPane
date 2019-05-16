package se.llbit.fx;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

class ToolPaneSkin extends SkinBase<ToolPane> {
  private final ScrollPane scrollPane;
  private VBox content = new VBox();
  private Map<ToolTab, ToolTabSkin> tabs = new IdentityHashMap<>();

  ToolPaneSkin(ToolPane control) {
    super(control);
    scrollPane = new ScrollPane(content);
    content.setFillWidth(true);
    content.setPrefWidth(Control.USE_COMPUTED_SIZE);
    content.setMaxWidth(Double.MAX_VALUE);
    scrollPane.setFitToWidth(true);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    getChildren().add(scrollPane);
    for (ToolTab tab : control.getTabs()) {
      addTab(tab, -1);
    }
    control.getTabs().addListener((ListChangeListener<ToolTab>) change -> {
      ObservableList<ToolTab> tabs = control.getTabs();
      while (change.next()) {
        if (change.wasPermutated()) {
          List<ToolTab> permuted = new ArrayList<>(change.getTo() - change.getFrom() + 1);
          for (int i = change.getFrom(); i <= change.getTo(); ++i) {
            permuted.add(tabs.get(i));
          }
          for (ToolTab tab : permuted) {
            removeTab(tab);
          }
          for (int i = 0; i < permuted.size(); ++i) {
            addTab(permuted.get(i), change.getFrom() + i);
          }
        }
        if (change.wasAdded()) {
          for (ToolTab tab : change.getAddedSubList()) {
            addTab(tab, -1);
          }
        }
        if (change.wasRemoved()) {
          for (ToolTab tab : change.getRemoved()) {
            removeTab(tab);
          }
        }
      }
    });
  }

  private void removeTab(ToolTab tab) {
    content.getChildren().remove(tabs.get(tab));
    tabs.remove(tab);
  }

  private void addTab(ToolTab tab, int offset) {
    ToolTabSkin skin = new ToolTabSkin(tab);
    tabs.put(tab, skin);
    if (offset >= 0) {
      // Insert at offset.
      content.getChildren().add(offset, skin);
    } else {
      // Append.
      content.getChildren().add(skin);
    }
  }

  @Override protected void layoutChildren(double contentX, double contentY, double contentWidth,
      double contentHeight) {
    scrollPane.resizeRelocate(contentX, contentY, contentWidth, contentHeight);
  }

  @Override protected double computeMinWidth(double height, double topInset, double rightInset,
      double bottomInset, double leftInset) {
    return scrollPane.minWidth(height) + leftInset + rightInset;
  }

  @Override protected double computeMinHeight(double width, double topInset, double rightInset,
      double bottomInset, double leftInset) {
    return scrollPane.minHeight(width) + topInset + bottomInset;
  }

  @Override protected double computePrefWidth(double height, double topInset, double rightInset,
      double bottomInset, double leftInset) {
    return scrollPane.prefWidth(height) + leftInset + rightInset + 30; // Extra space for scrollbar.
  }

  @Override protected double computePrefHeight(double width, double topInset, double rightInset,
      double bottomInset, double leftInset) {
    return scrollPane.prefHeight(width) + topInset + bottomInset;
  }

  @Override protected double computeMaxWidth(double height, double topInset, double rightInset,
      double bottomInset, double leftInset) {
    return scrollPane.maxWidth(height) + leftInset + rightInset;
  }

  @Override protected double computeMaxHeight(double width, double topInset, double rightInset,
      double bottomInset, double leftInset) {
    return scrollPane.maxHeight(width) + topInset + bottomInset;
  }
}
