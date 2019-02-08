package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentRequestController implements Initializable {

    @FXML
    private JFXTextField isbnTextField;

    @FXML
    private JFXTextField bookNameTextField;

    @FXML
    private JFXTextField authorNameTextField;

    @FXML
    private JFXTextField subjectTextField;

    @FXML
    private JFXTextField languageTextField;

    @FXML
    private JFXTextField publisherTextfFiled;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXButton cancelButton;



    public void CancelButtonAction(ActionEvent event) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void confimButtonAction(ActionEvent event) throws SQLException, IOException {

        String warning  = "You need to fill this field";

        if(bookNameTextField.getText().trim().isEmpty()){

            bookNameTextField.setPromptText(warning);
        }

        if(isbnTextField.getText().trim().isEmpty()){

            isbnTextField.setPromptText(warning);
        }

        boolean empty = bookNameTextField.getText().trim().isEmpty()
                        || isbnTextField.getText().trim().isEmpty();

        if(!empty) {

            DBConnect db = new DBConnect();

            String query = "INSERT INTO requests VALUES ('"+isbnTextField.getText().trim()+"','"+
                            bookNameTextField.getText().trim()+"','"+authorNameTextField.getText().trim()+"','"+
                            subjectTextField.getText().trim()+"','"+languageTextField.getText().trim()+"','"+
                            publisherTextfFiled.getText().trim()+"');";

            db.ExcuteQuery(query);

            System.out.println("Successfully requested");

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
