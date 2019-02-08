/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class AddmemberController implements Initializable {
    
  
    
    
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXTextField fullNameField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField depField;
    @FXML
    private JFXTextField secField;
    @FXML
    private JFXTextField yearField;
    @FXML
    private JFXTextField semField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField seqQuesField;
    @FXML
    private JFXTextField answerField;
    @FXML
    private JFXButton confirmButton;
    @FXML
    private JFXButton cancelButton;

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
    public void CloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
      }
    
    
    
    @FXML
    public void CofirmButtonAction(ActionEvent event) throws SQLException{
        
        String warning = "This field is required!! ";
        
        DBConnect db = new DBConnect();
        
        ResultSet res = db.getMemberDatabase();
                         
        String temp1 = userNameField.getText();
        String temp2  = idField.getText();
        
        String enteredUserName = temp1.replace(" ","");
        String enteredId = temp2.replace(" ","");
        
       
        
        boolean userexists = false;
        boolean idexists = false;
        boolean tableExists = false; // User information Table
                      
        
        while(res.next()){
                    
            if(enteredUserName.equals(res.getString("username"))){  
                userNameField.setText("");
                userNameField.setPromptText("username Exists!!");
                userexists = true;
                
            }
            
            if(enteredId.equals(res.getString("id"))){
                idField.setText("");
                idField.setPromptText("      Id Exists!!");
                idexists = true;
            }
           
            tableExists = true;
        }
        
        
        if(userNameField.getText().trim().isEmpty() && !userexists){
            userNameField.setPromptText(warning);
        }
        if(fullNameField.getText().trim().isEmpty()){
            fullNameField.setPromptText(warning);
        }
        if(passwordField.getText().trim().isEmpty()){
            passwordField.setPromptText(warning);
        }
        if(idField.getText().trim().isEmpty() &&!idexists){
            
            idField.setPromptText(warning);
        }
        
        if(depField.getText().trim().isEmpty()){
            depField.setPromptText(warning);
        }
        
        if(secField.getText().trim().isEmpty()){
            secField.setPromptText(warning);
        }
        
        if(yearField.getText().trim().isEmpty()){
            
            yearField.setPromptText(warning);
        }
        if(semField.getText().trim().isEmpty()){
            
            semField.setPromptText(warning);
        }
        
        if(emailField.getText().trim().isEmpty()){
            emailField.setPromptText(warning);
        }
        
        if(mobileField.getText().trim().isEmpty()){
            mobileField.setPromptText(warning);
        }
        
        if(seqQuesField.getText().trim().isEmpty()){
            seqQuesField.setPromptText(warning);
        }
        
        if(answerField.getText().trim().isEmpty()){
            
            answerField.setPromptText(warning);
            
        }
        
       boolean emptyField =  userNameField.getText().trim().isEmpty() || fullNameField.getText().trim().isEmpty() ||
                             idField.getText().trim().isEmpty()       || depField.getText().trim().isEmpty()      ||
                             secField.getText().trim().isEmpty()      || yearField.getText().trim().isEmpty()     ||
                             semField.getText().trim().isEmpty()      || emailField.getText().trim().isEmpty()    ||
                             mobileField.getText().trim().isEmpty()   || passwordField.getText().trim().isEmpty() ||
                             seqQuesField.getText().trim().isEmpty()  || answerField.getText().trim().isEmpty() ;          
       
       if(!tableExists){
           
           String query = "CREATE TABLE member_info(username VARCHAR(30) NOT NULL PRIMARY KEY,name VARCHAR(30),"+
                          "id VARCHAR(20)  NOT NULL UNIQUE, dep VARCHAR(5) , sec VARCHAR(3) , year VARCHAR(5),"+
                          "sem VARCHAR(5), email VARCHAR(30), mobile VARCHAR(20) );";
           
           db.ExecuteUpdate(query);
       }

       
       if(!userexists && !idexists && !emptyField ){
           
           db.insertNewMemberInfo(enteredUserName,fullNameField.getText(),idField.getText(),passwordField.getText(),
                   depField.getText(),secField.getText(),yearField.getText(),semField.getText(),
                   emailField.getText(),mobileField.getText());
          db.insertMemberSecurityInfo(enteredUserName,passwordField.getText(),seqQuesField.getText(),answerField.getText());
           
           Stage stage = (Stage) confirmButton.getScene().getWindow();
           stage.close();
           
       }
  
    }
    
    
}
