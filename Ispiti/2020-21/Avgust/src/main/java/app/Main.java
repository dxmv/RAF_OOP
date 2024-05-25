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
        Scene scene = new Scene(new GlavniProzor());

        stage.setScene(scene);
        stage.setTitle("Avgust");
        stage.show();
    }
}
