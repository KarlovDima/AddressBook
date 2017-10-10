package controllers;

import interfaces.impls.CollectionAddressBook;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Person;

import java.io.IOException;

public class MainController {

    private CollectionAddressBook collectionAddressBook=new CollectionAddressBook();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView tableAddressBook;

    @FXML
    private TableColumn<Person, String> columnFIO;

    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private Label labelCount;

    @FXML
    private void initialize(){
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person,String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        collectionAddressBook.fillTestData();

        tableAddressBook.setItems(collectionAddressBook.getPersonObservableList());

        collectionAddressBook.getPersonObservableList().addListener((ListChangeListener<Person>) c -> updateCountLabel());



        updateCountLabel();
    }

    private void updateCountLabel(){
        labelCount.setText("Количество записей: "+collectionAddressBook.getPersonObservableList().size());
    }

    public void showDialog(ActionEvent actionEvent) {
         Object source = actionEvent.getSource();

         if(!(source instanceof Button))
             return;

         Button button=(Button) source;
         Person person=(Person) tableAddressBook.getSelectionModel().getSelectedItem();

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxmls/edit.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
