package app;

import app.views.GlavniProzor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GlavniProzor glavniProzor = new GlavniProzor();
        Scene scene = new Scene(glavniProzor);

        stage.setScene(scene);
        stage.setTitle("Jun");
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.show();
    }
}
