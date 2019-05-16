package se.llbit.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class ToolPaneDemo extends Application {
  private ToolPane tabs;
  private Random rand = new Random(1);

  public static void main(String[] args) {
    launch(args);
  }

  @Override public void start(Stage stage) {
    stage.setTitle("Tool Tabs");
    VBox content = new VBox();
    content.setSpacing(10);
    content.setPadding(new Insets(10));
    tabs = new ToolPane();
    Button addTab = new Button("Add tab");
    addTab.setOnAction(event -> addTab());
    content.setPrefWidth(Control.USE_COMPUTED_SIZE);
    content.getChildren().addAll(addTab, tabs);
    for (int i = 0; i < 4; ++i) addTab();
    Scene scene = new Scene(content);
    stage.setScene(scene);
    stage.show();
  }

  private void addTab() {
    String name = "Tab " + rand.nextInt(10);
    ToolTab tab = new ToolTab(name, new Label("there is a long line of text here just to check GUI layout " + name));
    tabs.getTabs().add(tab);
  }
}
