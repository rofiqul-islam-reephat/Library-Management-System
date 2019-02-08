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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class StudentpanelController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;

    String username ;
     
    @FXML
    private JFXButton findBookButton;
    @FXML
    private JFXButton reqBookButton;
    @FXML
    private JFXButton currentlyReadingButton;
    @FXML
    private JFXButton readListButton;
    @FXML
    private JFXButton noticeBoardButton;
    @FXML
    private JFXButton bookDatabaseButton;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private Label welocmeUserLabel;
    @FXML
    private Label memberNameField;
    @FXML
    private Label IdField;
    @FXML
    private Label departmentField;
    @FXML
    private Label sectionField;
    @FXML
    private Label yearField;
    @FXML
    private Label semesterField;
    @FXML
    private Label emailField;
    @FXML
    private Label mobileField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    
    
    @FXML
    public void logoutButtonAction(ActionEvent event) throws IOException{
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }

    @FXML
    void bookListButtonAction(ActionEvent event) {

    }




    @FXML
    void currentlyReadingButtonAction(ActionEvent event) {

    }

    @FXML
    void findBookbuttonAction(ActionEvent event) {

    }


    @FXML
    void noticeBoardButtonAction(ActionEvent event) {

    }

    @FXML
    void readListButtonAction(ActionEvent event) {

    }

    @FXML
    void requestBookButtonAction(ActionEvent event) throws IOException {

        FXMLLoader fxloader = new FXMLLoader(getClass().getResource("studentRequest.fxml"));
        Parent root1 = (Parent) fxloader.load();
        Stage stage = new Stage();

        stage.setTitle("New Request");
        stage.setScene(new Scene(root1));
        stage.show();


    }


    public String getIdFieldText() {
        return semesterField.getText().trim();
    }


    
    
    public void setUserNameLabel(String name){
        
        welocmeUserLabel.setText(name);
    }
    
    
    
    public void setUserNameInfor(String name){
        
       memberNameField.setText(name);
    }
    
    public void setUserIdLabel(int id){
        
       IdField.setText(Integer.toString(id));
        
    }
    
    public void setUserDepLabel(String dep){
        
        departmentField.setText(dep);
    }
    
    public void setUserSectLabel(String sec){
        
        sectionField.setText(sec);
    }
    
    public void setUserYearLabel(String year){
     
        yearField.setText(year);
        
    }
    
     public void setUserSemLabel(String sem){
         
     
        semesterField.setText(sem);
        
    }
    
    public void setUserEmailLabel(String email){
        
        emailField.setText(email);
    }
    
    public void setUserMobileLabel(String label){
        
        mobileField.setText(label);
    }
    
    
    
}
 