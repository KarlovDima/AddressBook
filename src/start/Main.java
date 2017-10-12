package start;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("ru")));
        fxmlLoader.setLocation(getClass().getResource("../fxmls/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("address_book"));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(420);
        primaryStage.setScene(new Scene(fxmlMain, 322, 381));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
