/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.print.attribute.standard.RequestingUserName;

/**
 * FXML Controller class
 *
 * @author reephat
 */
public class AdminpanelController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private JFXTextField issueIsbnField;
    @FXML
    private JFXTextField issueMemberIdField;
    @FXML
    private JFXDatePicker issueDateField;
    @FXML
    private JFXTextField reIssuIsbnField;
    @FXML
    private JFXTextField reIssueMemberField;
    @FXML
    private JFXDatePicker reIssueDateField;

    @FXML
    private JFXButton issueConfirm;
    @FXML
    private JFXButton reIssueConfirm;
    @FXML
    private JFXButton addMemberButton;
    @FXML
    private JFXButton addBookButton;
    @FXML
    private JFXButton searachMemberButton;
    @FXML
    private JFXButton deleteMemberButton;
    @FXML
    private JFXButton memberDatabase;
    @FXML
    private JFXButton seachBookButton;
    @FXML
    private JFXButton delelteBookButton;
    @FXML
    private JFXButton bookDatabseButton;
    @FXML
    private JFXButton issueListButton;
    @FXML
    private JFXButton bookReqButton;
    @FXML
    private JFXButton reIssueLustButton;
    @FXML
    private JFXButton newNoticeButton;
    @FXML
    private JFXButton logoutButton;
    
  

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
    private void logoutButtonAction(ActionEvent event) throws IOException{
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
        rootPane.getChildren().setAll(pane);
                
    }



    @FXML
    void bookReqActionButton(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("requestlist.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    
    
    
 
    @FXML
    private void addMemberButtonAction(ActionEvent event) throws IOException{
        
      try{
          
          FXMLLoader fxloader = new FXMLLoader(getClass().getResource("addmember.fxml"));
          Parent root1 = (Parent) fxloader.load();
          Stage stage = new Stage();
          
          stage.setTitle("ADD MEMBER");
          stage.setScene(new Scene(root1));  
          stage.show();
          
      }
      catch(IOException e){
          
          System.out.println("e");
      }
   
    }
    
    @FXML
    private void addBookButtonAction(ActionEvent event) throws IOException{
        
      try{
          
          FXMLLoader fxloader = new FXMLLoader(getClass().getResource("addbook.fxml"));
          Parent root1 = (Parent) fxloader.load();
          Stage stage = new Stage();
          
          stage.setTitle("ADD NEW BOOK ENTRY");
          stage.setScene(new Scene(root1));  
          stage.show();
          
      }
      catch(IOException e){
          
          System.out.println("e");
      }
   
    }
    
    @FXML
    public void issueConfirmButton(ActionEvent event) throws SQLException{
        
        DBConnect db  = new DBConnect();
        
        ResultSet rs = db.getBookDatabse();
        
        String temp = issueMemberIdField.getText();
        String temp2 = issueIsbnField.getText();
        
        String memberID = temp.replaceAll(" ","");
        String bookisbn = temp2.replaceAll(" ","");

        //Checks if book exists in the database and available

        boolean bookexists = false;
        boolean memberexists = false ;

        int quantity= -1;

        while(rs.next()){
            
             if(bookisbn.equals(rs.getString("isbn"))) {
                 bookexists = true;
                 quantity = rs.getInt("quantity");
             }
        }

        //Checks if the member is valid

        DBConnect db2 = new DBConnect();

        ResultSet rs2  =db2.getMemberDatabase();

        while(rs2.next()){

            if(memberID.equals(rs2.getString("id")))
                memberexists=true;
        }

        //Checks if the field left empty

        if(issueMemberIdField.getText().trim().isEmpty())
            issueMemberIdField.setPromptText("must fill this field");
        if(issueIsbnField.getText().trim().isEmpty()){
            issueIsbnField.setPromptText("must fill this field");
        }


        if(!bookexists && !issueIsbnField.getText().trim().isEmpty()) {

            issueIsbnField.setText("");
            issueIsbnField.setPromptText("book not found!");

        }

        System.out.println(memberexists);
        if(!memberexists && !issueMemberIdField.getText().trim().isEmpty()) {
            issueMemberIdField.setText("");
            issueMemberIdField.setPromptText("member not found!");
        }

        if(quantity == 0) {
            issueMemberIdField.setText("");
            issueIsbnField.setText("");
            issueIsbnField.setPromptText("book is not available now!");
            issueMemberIdField.setPromptText("please come back later :(");

        }


        boolean alreadyIssued  = false;

        DBConnect db4 = new DBConnect();

        String query4 = "SELECT * from issue_list";

        ResultSet issue = db4.getResultSet(query4);

        while(issue.next()){

            if(issue.getString("memberid") == memberID && issue.getString("bookisbn") == bookisbn)
                alreadyIssued = true;
        }

        if(alreadyIssued==true){

            issueIsbnField.setText("");
            issueIsbnField.setPromptText("book already issued");
            issueMemberIdField.setText("");
            issueMemberIdField.setPromptText("to this user");
        }

        if(!alreadyIssued && memberexists && bookexists && quantity>0){

            quantity--;

            String query = "INSERT INTO issue_list VALUES('"+issueMemberIdField.getText().trim()
                            +"','"+issueIsbnField.getText().trim()+"','"+
                    issueDateField.getValue()+"','"+(issueDateField.getValue().plusDays(14))+"');";

            String query2 = "UPDATE book_info SET quantity="+quantity+" WHERE isbn ="+bookisbn+";";

            db2.ExecuteUpdate(query);

            db2.ExcuteQuery(query);

            issueMemberIdField.setText("");
            issueMemberIdField.setPromptText("Success");
            issueIsbnField.setText("");
            issueIsbnField.setPromptText("Success");

            System.out.println("Successfully updated book issue");
        }
        
    }


    @FXML
    public void reIssueConfirmButton(ActionEvent event) throws SQLException {

        DBConnect db  = new DBConnect() ;

        ResultSet rs = db.getResultSet("SELECT * FROM issue_list");

        String isbn = reIssuIsbnField.getText().trim();
        String member = reIssueMemberField.getText().trim();

        boolean isIssued = false;


        while(rs.next()){

                if(member.equals(rs.getString("memberid")) && isbn.equals(rs.getString("bookisbn"))){

                    isIssued = true;}
        }

        if(reIssuIsbnField.getText().trim().isEmpty())
            reIssuIsbnField.setPromptText("you must fill this!!");

        if(reIssueMemberField.getText().trim().isEmpty())
            reIssueMemberField.setPromptText("can't leave this field empty");


        if(isIssued == true){

            String query = "UPDATE issue_list SET reutrn_date = reutrn_date+7 WHERE memberid ="+member+";";

            db.ExecuteUpdate(query);

            String query2 = "UPDATE issue_list SET issue_date = "+reIssueDateField.getValue()+ "WHERE memberid = "+member+";";

            reIssueMemberField.setText("");
            reIssueMemberField.setPromptText("Success");
            reIssueMemberField.setText("");
            reIssueMemberField.setPromptText("Success");
        }

        if(isIssued==false){

            reIssueMemberField.setText("");
            reIssueMemberField.setPromptText("haven't issued before!");
        }
        
    }
    
    
    @FXML 
    public void bookDatabaseButtonAction(ActionEvent event) throws IOException{
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("bookdatabase.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void searchBookButtonAction(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("searchbook.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
   @FXML
    public void searchMemberButtonAction(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("searchstudent.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML 
    public void memberDatabaseButtonAction(ActionEvent event) throws IOException{
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("memberdatabase.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    @FXML
    void deleteBookButtonAction(ActionEvent event) throws IOException {


        FXMLLoader fxloader = new FXMLLoader(getClass().getResource("deletebook.fxml"));
        Parent root1 = (Parent) fxloader.load();
        Stage stage = new Stage();

        stage.setTitle("Delete Book Entry");
        stage.setScene(new Scene(root1));
        stage.show();

    }
    
   
    
}
