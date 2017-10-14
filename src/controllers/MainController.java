package controllers;

import interfaces.impls.CollectionAddressBook;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Person;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private CollectionAddressBook collectionAddressBook = new CollectionAddressBook();
    private Stage mainStage;

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

    private ObservableList<Person> backupList;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private ResourceBundle resourceBundle;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle=resources;

        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        initListeners();
        fillData();
        initLoader();
    }

    private void initListeners() {
        collectionAddressBook.getPersonObservableList().addListener((ListChangeListener<Person>) c -> updateCountLabel());

        tableAddressBook.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });
    }

    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxmls/edit.fxml"));
            fxmlLoader.setResources(resourceBundle);
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillData(){
        collectionAddressBook.fillTestData();
        backupList= FXCollections.observableArrayList();
        backupList.addAll(collectionAddressBook.getPersonObservableList());
        tableAddressBook.setItems(collectionAddressBook.getPersonObservableList());
    }

    private void updateCountLabel() {
        labelCount.setText(resourceBundle.getString("count")+":" + collectionAddressBook.getPersonObservableList().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button))
            return;

        Button button = (Button) source;

        switch (button.getId()) {
            case "btnAdd":
                editDialogController.setPerson(new Person());
                showDialog();
                collectionAddressBook.add(editDialogController.getPerson());
                break;
            case "btnEdit":
                if(isPersonSelected((Person) tableAddressBook.getSelectionModel().getSelectedItem())) {
                    editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                    showDialog();
                }
                break;
            case "btnDelete":
                Scene scene =mainStage.getScene();
                try {
                    scene.setRoot(FXMLLoader.load(getClass().getResource("../fxmls/main.fxml"), ResourceBundle.getBundle("bundles.Locale", Locale.ENGLISH)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                collectionAddressBook.delete((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                break;
        }

    }

    private void showDialog() {

        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        editDialogStage.showAndWait();
    }

    private boolean isPersonSelected(Person person){
        if (person == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("");
            alert.setContentText("Выберите запись");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void buttonSearchPressed(ActionEvent actionEvent){
        collectionAddressBook.getPersonObservableList().clear();

        for (Person person:backupList) {
            if(person.getFio().toLowerCase().contains(txtSearch.getText().toLowerCase()) || person.getPhone().toLowerCase().contains(txtSearch.getText().toLowerCase()) )
                collectionAddressBook.getPersonObservableList().add(person);
        }
    }
}
