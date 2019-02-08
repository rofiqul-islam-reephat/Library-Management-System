package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class  requestListController implements Initializable{

    ObservableList<RequestTableView> data = FXCollections.observableArrayList();


    @FXML
    private AnchorPane rootPane;


    @FXML
    private TableView<RequestTableView> bookdataTable;

    @FXML
    private TableColumn<RequestTableView, String> isbn;

    @FXML
    private TableColumn<RequestTableView, String> bookname;

    @FXML
    private TableColumn<RequestTableView, String> author;

    @FXML
    private TableColumn<RequestTableView, String> subject;

    @FXML
    private TableColumn<RequestTableView, String> language;

    @FXML
    private TableColumn<TableItemBookdata, String> publisher;

    @FXML
    private JFXButton goBackButton;


     @FXML
    void goBackButtonAction(ActionEvent event) throws IOException {

         AnchorPane pane = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));
         rootPane.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCol();
        try {
            getData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initCol(){

        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        bookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        language.setCellValueFactory(new PropertyValueFactory<>("language"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));

    }

    private void getData() throws SQLException {

        DBConnect db = new DBConnect ();

        String query  = "SELECT *FROM requests";

        ResultSet res = db.getResultSet(query);

        while(res.next()){

            String isbn = res.getString("isbn");
            String bookname  = res.getString("bookname");
            String author = res.getString("author");
            String subject = res.getString("subject");
            String language = res.getString("language");
            String publisher = res.getString("publisher");



            data.add(new RequestTableView(isbn, bookname, author,subject, language, publisher));

        }

        bookdataTable.getItems().setAll(data);



    }

}
