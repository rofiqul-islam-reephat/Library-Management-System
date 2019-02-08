/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class SearchstudentController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton searchButton;
    @FXML
    private ChoiceBox<?> serachOption;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> dep;
    @FXML
    private TableColumn<?, ?> year;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> mobile;
    @FXML
    private JFXButton goBackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchButtonAction(ActionEvent event) {
    }

   @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException{
        
       AnchorPane pane = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));
       rootPane.getChildren().setAll(pane);
        
    }
}
