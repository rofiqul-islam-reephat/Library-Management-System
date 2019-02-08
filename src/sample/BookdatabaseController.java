
package sample;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class BookdatabaseController implements Initializable {
    
    AnchorPane rootaPane ;
    
    ObservableList<TableItemBookdata> data = FXCollections.observableArrayList();

    @FXML
    private TableView<TableItemBookdata> bookdataTable;
    @FXML
    private TableColumn<TableItemBookdata, String> isbn;
    @FXML
    private TableColumn<TableItemBookdata, String> bookname;
    @FXML
    private TableColumn<TableItemBookdata, String> author;
    @FXML
    private TableColumn<TableItemBookdata, String> subject;
    @FXML
    private TableColumn<TableItemBookdata, String> language;
    @FXML
    private TableColumn<TableItemBookdata, String> publisher;
    @FXML
    private TableColumn<TableItemBookdata, String> year;
    @FXML
    private TableColumn<TableItemBookdata, Integer> quantity;
    @FXML
    private JFXButton goBackButton;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        
        DBConnect db = new DBConnect();
        
        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(BookdatabaseController.class.getName()).log(Level.SEVERE,null, ex);
        }
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
    private void goBackAction(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));
        rootPane.getChildren().setAll(pane);   
    }

    private void getData() throws SQLException {
        
        DBConnect db = new DBConnect ();
        
        String query  = "SELECT *FROM book_info";
        
        ResultSet res = db.getResultSet(query);
        
        while(res.next()){
            
            String isbn = res.getString("isbn");
            String bookname  = res.getString("bookname");
            String author = res.getString("author");
            String subject = res.getString("subject");
            String language = res.getString("language");
            String publisher = res.getString("publisher");
            String year = res.getString("year");
            Integer quantity = res.getInt("quantity");

            int quan = quantity;

            System.out.println(quan);

            data.add(new TableItemBookdata(isbn, bookname, author,subject, language, publisher,year, quan));
            
        }
        
        bookdataTable.getItems().setAll(data);
        
        
        
    }

    
    
}
