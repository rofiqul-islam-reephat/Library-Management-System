package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeletebookController implements Initializable {


    boolean found = false;

    @FXML
    private JFXTextField searchISBNField;

    @FXML
    private JFXButton findButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton cancelButrton;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButrton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteButtonAction(ActionEvent event) throws SQLException {

        String isbn = searchISBNField.getText().trim();

        DBConnect db = new DBConnect();

        if(found==true){

            String query = "DELETE FROM book_info WHERE isbn='"+isbn+"';";

            db.ExcuteQuery(query);
            System.out.println("Successfully Deleted Book");

            Stage stage = (Stage) cancelButrton.getScene().getWindow();
            stage.close();
        }
        if(searchISBNField.getText().trim().isEmpty())
            searchISBNField.setPromptText("please enter an isbn!");



    }

    @FXML
    void findButtonAction(ActionEvent event) throws SQLException {

        DBConnect db = new DBConnect();

        String query = "SELECT * from book_info";

        ResultSet res = db.getBookDatabse();

        String temp = searchISBNField.getText().trim();

        String isbn = temp.replaceAll(" ","");


        while(res.next()){

            if(isbn.equals(res.getString("isbn"))){
                isbnLabel.setText(res.getString("isbn"));
                nameLabel.setText(res.getString("bookname"));
                authorLabel.setText(res.getString("author"));
                subjectLabel.setText(res.getString("subject"));
                languageLabel.setText(res.getString("language"));
                yearLabel.setText(res.getString("year"));
                publisherLabel.setText(res.getString("publisher"));
                found =true;
            }
        }


        if(found == false){
            searchISBNField.setText("");
            searchISBNField.setPromptText("Book not found!!");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }






}
