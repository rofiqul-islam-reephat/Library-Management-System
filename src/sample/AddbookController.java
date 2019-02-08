
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
import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class AddbookController implements Initializable {
    
    

    
    @FXML
    private JFXButton confirmButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXTextField isbnField;
    @FXML
    private JFXTextField booknameField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField subjectField;
    @FXML
    private JFXTextField languageField;
    @FXML
    private JFXTextField publisherField;
    @FXML
    private JFXTextField yearField;
    @FXML
    private JFXTextField quantity;

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
    private void CofirmButtonAction(ActionEvent event) throws SQLException {
        
        String warning = "You must fill this field ! ";
        
        DBConnect db = new DBConnect();
        
        ResultSet res = db.getBookDatabse();
                         
        String temp2 =isbnField.getText();
                    
        String enteredBookISBN = temp2.replace(" ","");
        
        boolean isbnexists = false;
      
        
        while(res.next()){
            
           if(enteredBookISBN.equals(res.getString("isbn"))){
                
                  isbnField.setText("");
                 isbnField.setPromptText("Book already resgistered");
                isbnexists = true ; 
             } 
                 
              
        }
        
        if(isbnField.getText().trim().isEmpty() && !isbnexists){
            isbnField.setPromptText(warning);
            
        }
        
        if(booknameField.getText().trim().isEmpty()){
            
            booknameField.setPromptText(warning);
        }
        
        if(authorField.getText().trim().isEmpty()){
            
            authorField.setPromptText(warning);
        }
        
        if(subjectField.getText().trim().isEmpty()){
            
            subjectField.setPromptText(warning);
        }
        
        if(languageField.getText().trim().isEmpty()){
            
            languageField.setPromptText(warning);
        }
        
        
        if(publisherField.getText().trim().isEmpty()){
            
            publisherField.setPromptText(warning);
            
        }
        
        if(yearField.getText().trim().isEmpty()){
            
            yearField.setPromptText(warning);
        }
        
        if(quantity.getText().trim().isEmpty()){
            
            quantity.setPromptText(warning);
            
        }
        
        boolean emptyField = isbnField.getText().trim().isEmpty()     ||  booknameField.getText().trim().isEmpty()  ||
                             authorField.getText().trim().isEmpty()   ||  subjectField.getText().trim().isEmpty()   ||
                             languageField.getText().trim().isEmpty() ||  publisherField.getText().trim().isEmpty() ||
                             yearField.getText().trim().isEmpty()     ||  quantity.getText().trim().isEmpty() ;  
        
       
        if(!isbnexists && !emptyField){
            
            String temp= quantity.getText();
            String tempquan = temp.replace(" ", "");
            
            int quan = Integer.parseInt(tempquan);
            
            
            db.insertBookInfo(isbnField.getText(),booknameField.getText(), authorField.getText(), 
                               subjectField.getText(),languageField.getText(),publisherField.getText(),
                                yearField.getText(), quan);
            
           Stage stage = (Stage) cancelButton.getScene().getWindow();
           stage.close();
                                
        }  
        
    }

    @FXML
    private void CloseButtonAction(ActionEvent event) {
        
          Stage stage = (Stage) cancelButton.getScene().getWindow();
          stage.close();
    }
    
    
   
    
}
