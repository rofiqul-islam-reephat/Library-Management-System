/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class SearchbookController implements Initializable {
    
    @FXML
    AnchorPane rootPane;
    ObservableList<TableItemBookdata> data = FXCollections.observableArrayList();

    @FXML
    private JFXButton searchButton;
    @FXML
    private ChoiceBox<String> serachOption;
    @FXML
    private JFXButton iamFeelingLuckyButton;
    @FXML
    private TextField searchField;

    @FXML
    private TableView<TableItemBookdata> bookdataTable;
    @FXML
    private TableColumn<TableItemBookdata,String> isbn;
    @FXML
    private TableColumn<TableItemBookdata, String> bookname;
    @FXML
    private TableColumn<TableItemBookdata, String> author;
    @FXML
    private TableColumn<TableItemBookdata, String> subject;
    @FXML
    private TableColumn<TableItemBookdata, String> language;
    @FXML
    private TableColumn<TableItemBookdata,String> publisher;
    @FXML
    private TableColumn<TableItemBookdata, String> year;
    @FXML
    private TableColumn<TableItemBookdata, Integer> quantity;
    @FXML
    private JFXButton goBackButton;

    /**
     * Initializes the controller class.
     */

    @Override


    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        serachOption.getItems().addAll("isbn","bookname","author","subject","language","year","publisher");

        initCol();

    }


    public void initCol(){

        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        bookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        language.setCellValueFactory(new PropertyValueFactory<>("language"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }


    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException{
        
       AnchorPane pane = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));
       rootPane.getChildren().setAll(pane);
        
    }

    @FXML
    private  void iamFeelingLuckyAction(ActionEvent event){

    }

    @FXML
    public void searchButtonAction(ActionEvent event) throws SQLException {


            DBConnect db = new DBConnect();

            ResultSet res = db.getBookDatabse();

            if(serachOption.getSelectionModel().isEmpty()) {

                searchField.setText("");
                searchField.setPromptText("Select an option!");
            }

            if(searchField.getText().trim().isEmpty()){

                searchField.setText("");
                searchField.setPromptText("empty keyword!!");

            }




            if(!serachOption.getSelectionModel().isEmpty() && !searchField.getText().isEmpty()) {

                String option = serachOption.getValue();
                String keyword = searchField.getText().trim();

                String keywordException = keyword.substring(0,1).toUpperCase()+
                                           keyword.substring(1).toLowerCase();
                data.clear();


                while(res.next()){

                    String temp  = res.getString(option);

                    if(keyword.equals(temp) || temp.contains(keyword) || temp.contains(keywordException)){

                        String isbn = res.getString("isbn");
                        String bookname  = res.getString("bookname");
                        String author = res.getString("author");
                        String subject = res.getString("subject");
                        String language = res.getString("language");
                        String publisher = res.getString("publisher");
                        String year = res.getString("year");
                        Integer quantity = res.getInt("quantity");

                        data.add(new TableItemBookdata(isbn, bookname, author,subject, language, publisher,year, quantity));


                    }

                }



                bookdataTable.getItems().setAll(data);

            }
    }
    
    
}
