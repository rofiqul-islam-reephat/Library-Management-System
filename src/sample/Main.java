package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @FXML
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Smart Library Manager");
        primaryStage.setScene(new Scene(root, 1024, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
