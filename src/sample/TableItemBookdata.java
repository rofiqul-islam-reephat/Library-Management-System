
package sample;

public class TableItemBookdata {
    
    private  String isbn ;
    private String bookname;
    private String author;
    private String subject ;
    private String language;
    private String publisher;
    private String year;
    private int quantitiy;
    
    
    public TableItemBookdata(String isbn , String bookname , String author, String subject ,
                              String language ,
                              String publisher ,String year ,int quantity){
        
        
        this.isbn = isbn ; this.bookname = bookname ; this.author = author ; this.subject = subject ;
        this.language = language ; this.year = year ; this.publisher = publisher ; this.quantitiy = quantity ;
        
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuantitiy() {
        return quantitiy;
    }

    public void setQuantitiy(int quantitiy) {
        this.quantitiy = quantitiy;
    }
    
    
    
    
}
