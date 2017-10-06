package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(420);
        primaryStage.setScene(new Scene(root, 322, 381));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
