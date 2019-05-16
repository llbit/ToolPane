package se.llbit.fx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

public class ToolTab {

  private final String title;
  private final Node content;
  private ToolPane toolPane = null;
  private BooleanProperty selected = new SimpleBooleanProperty(false);

  public ToolTab(String title, Node content) {
    this.title = title;
    this.content = content;
  }

  void setToolPane(ToolPane toolPane) {
    this.toolPane = toolPane;
  }

  public ToolTab(String title) {
    this(title, null);
  }

  public String getTitle() {
    return title;
  }

  public Node getContent() {
    return content;
  }

  public ToolPane getToolPane() {
    return toolPane;
  }

  public void setSelected(boolean selected) {
    this.selected.setValue(selected);
  }

  public BooleanProperty selectedProperty() {
    return selected;
  }

  public boolean getSelected() {
    return selected.getValue();
  }
}
