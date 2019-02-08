package sample;
import java.sql.*;

public class DBConnect {

        private Connection con;
        private Statement st;
        private ResultSet res;

        private String user = "root";
        private String pass = "";
        private String url  = "jdbc:mysql://localhost/library";
        

        public DBConnect(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url,user,pass);
                st = con.createStatement();
            }
            catch (ClassNotFoundException | SQLException e){
                System.out.println("Error: "+e);
            }
        }
        
        
        
        
        public boolean ExecuteAdminLoginQuery(String name , String pass) throws SQLException{
            
            String query = "SELECT * FROM admin_list";
            
            boolean login = false;
            
            ResultSet rs = st.executeQuery(query);
            
             while (rs.next()){
                 String admin_name = rs.getString("name");
                 String admin_password = rs.getString("password");
                 
                 if(admin_name.equals(name) && admin_password.equals(pass)){
                     
                     login = true ;
        
                 }
             }
      
            return login ;
        }
        
        
        public boolean ExecuteMemberLoginQuery(String name , String pass) throws SQLException{                  
            
            String query = "SELECT * FROM  member_list";
            
            boolean login = false;
            
            ResultSet rs = st.executeQuery(query);
            
             while (rs.next()){
                 
                 String member_name = rs.getString("name");
                 String member_password = rs.getString("password");
                 
                 if(member_name.equals(name) && member_password.equals(pass)){
                     
                     login = true ;
                                                  
                 }
             }
      
            return login ;
        }
        
        public void ExcuteQuery(String query) throws SQLException{
            
            st.execute(query);
            
            System.out.println("Successfully Executed query");
            
    
        }
        
        public void ExecuteUpdate(String query) throws SQLException{
            
            st.executeUpdate(query);
            
            System.out.println("Successfully updated table");
            
        }
        
        public void insertNewMemberInfo(String username , String name , String password , String id , String dep ,String sec,
                                      String year, String sem , String email ,String  mobile) throws SQLException{
            
          
            String member_infoQuery = "INSERT INTO member_info VALUES('"+username+"','"+name+"','"+id+"','"+
                            dep+"','"+sec+"','"+year+"','"+sem+"','"+email+"','"+mobile+"');";
            
                        
            st.execute(member_infoQuery);
            
            
            System.out.println("Successfully Inserted data");
            
        }
        
        public void insertMemberSecurityInfo(String username ,String password , String securQues ,String securAns) throws SQLException{
            
            res = getMemberSecurityDatabase();
            
             boolean table2Exists =false; //Security Database Table
             
             while(res.next()){
                 table2Exists = true ;break;
             }
            if(table2Exists)
               table2Exists = true;
                    
            if(!table2Exists){
           
                String query = "CREATE TABLE member_list(name VARCHAR(30) PRIMARY KEY, " +
                               "password VARCHAR(16),securityquestion TEXT , answer VARCHAR(30));";
                 st.execute(query);
            }
            
            if(table2Exists){
                
                String query = "INSERT INTO member_list VALUES('"+username+"','"+password+"','"+securQues+"','"+securAns+"');";
                
                st.execute(query);
                
                System.out.println("Successfully Added Security Information");        
            }
                       
        }
        
        public void insertBookInfo(String isbn , String bookname, String author, String subject ,String language,
                                   String publisher ,String year, int quantity) throws SQLException{
        
            
         String query = "INSERT INTO book_info VALUES('"+isbn+"','"+bookname+"','"+author+"','"+subject+"','"+
                          language+"','"+publisher+"','"+year+"',"+quantity+");";
           
            
          st.execute(query);
          
          System.out.println("Successfully added book data ----");
        
        }
        
        
        public ResultSet getMemberInfo(String inputName ) throws SQLException{
            
             String query = "SELECT * FROM  member_info WHERE username ='"+inputName+"'";
             
             System.out.println(inputName);
             
             ResultSet rs = st.executeQuery(query);
            
             return rs;
        }
        
        
        public ResultSet getMemberSecurityInfo(String input) throws SQLException{
            
              String query = "SELECT * FROM  member_list WHERE name = '"+input+"'";
             
             ResultSet rs = st.executeQuery(query);
            
             return rs;
            
        }
        
        public ResultSet getMemberDatabase() throws SQLException{
            
             String query = "SELECT * FROM  member_info";
             
             ResultSet rs = st.executeQuery(query);
            
             return rs;
            
        }
        public ResultSet getMemberSecurityDatabase() throws SQLException{
            
             String query = "SELECT * FROM  member_list";
             
             ResultSet rs = st.executeQuery(query);
            
             return rs;
            
        }
        
        public ResultSet getBookDatabse() throws SQLException{
            
            
            String query  = "SELECT * FROM book_info";
            
            ResultSet rs  =  st.executeQuery(query);
            
            return rs;
            
        }
        
        public ResultSet getResultSet(String query) throws SQLException{
            
            ResultSet rs = st.executeQuery(query);
            
            return rs;
            
        }
        
        
}
