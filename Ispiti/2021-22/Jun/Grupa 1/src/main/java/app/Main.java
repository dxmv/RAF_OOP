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
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }
}
