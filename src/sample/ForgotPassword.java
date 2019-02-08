/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class ForgotPassword implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXButton searchUserBiutton;
    @FXML
    private JFXTextArea secquesField;
    @FXML
    private JFXTextField secquesAns;
    @FXML
    private JFXTextArea retrivePassField;
    @FXML
    private JFXButton confrimButton;
    @FXML
    private JFXButton backToLoginButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backToLogin(ActionEvent event) throws IOException {
        
       AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        rootPane.getChildren().setAll(pane);
        
    }
    
    @FXML
    private void searchUserButtonAction(ActionEvent event) throws SQLException{
        
        DBConnect db = new DBConnect();
        
        String inputSearch = userName.getText();
        ResultSet res = db.getMemberSecurityInfo(inputSearch);
        
        boolean found = false;
        
        while(res.next()){
            
            if(inputSearch.equals(res.getString("name"))){
                
               secquesField.setText(res.getString("securityquestion"));
               found = true;
               break;
            }
        }
        
        if(found ==false){
            userName.setText("Not Found!!");
        }
        
    }
    
    @FXML
    
    private void ConfirmButton(ActionEvent event) throws SQLException{
        
        DBConnect db = new DBConnect();
        
        String inputSearch = userName.getText();
        ResultSet res = db.getMemberSecurityInfo(inputSearch);
        String ans = secquesAns.getText();
        
        boolean correctAns = false;
        
        while(res.next()){
            
            if(ans.equals(res.getString("answer"))){
                 
               retrivePassField.setText(res.getString("password"));
               correctAns = true;
            }
        }
        if(correctAns==false)
            retrivePassField.setText("Wrong Answer!!");
        
    }
    
}
    
