package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class MainController  implements Initializable  {
    
    @FXML 
    private AnchorPane rootPane;

    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton forgotPassword;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    


    @FXML
    private void loginButton(ActionEvent event) throws SQLException, IOException {
        
        DBConnect db = new DBConnect();
        
        String inputUserName =userName.getText();


        boolean adminlogin = db.ExecuteAdminLoginQuery(inputUserName.trim(),password.getText());  
        
        if(adminlogin==true){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));
            rootPane.getChildren().setAll(pane); 
          }
        
        else{
            
           boolean studentlogin =db.ExecuteMemberLoginQuery(inputUserName.trim(),password.getText());
           
           if(studentlogin==true){
           
             FXMLLoader loader = new FXMLLoader(getClass().getResource("studentpanel.fxml"));
             Parent root = loader.load();
             StudentpanelController stuCntrl  = loader.getController();
             
             String userNameLabel = inputUserName.trim();
             
             stuCntrl.setUserNameLabel(userNameLabel.toUpperCase());
             
             ResultSet  res = db.getMemberInfo(inputUserName.trim());
             
             
             while(res.next()){
             stuCntrl.setUserNameInfor(res.getString("name"));
             stuCntrl.setUserIdLabel(res.getInt("id"));
             stuCntrl.setUserDepLabel(res.getString("dep"));
             stuCntrl.setUserSectLabel(res.getString("sec"));
             stuCntrl.setUserYearLabel(res.getString("year"));
             stuCntrl.setUserSemLabel(res.getString("sem"));
             stuCntrl.setUserEmailLabel(res.getString("email"));
             stuCntrl.setUserMobileLabel(res.getString("mobile"));
             }
             loginButton.getScene().setRoot(root);
           }
           else{
               
               userName.setPromptText("Invaild User or Password!");
               password.setText("");
           }
            
        }
        
    }

    @FXML
    private void forgotAction(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }
    
}
