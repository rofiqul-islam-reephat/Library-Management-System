
package sample;

public class RequestTableView{

    private  String isbn ;
    private String bookname;
    private String author;
    private String subject ;
    private String language;
    private String publisher;


    public RequestTableView(String isbn , String bookname , String author, String subject ,
                             String language ,
                             String publisher ){


        this.isbn = isbn ; this.bookname = bookname ; this.author = author ; this.subject = subject ;
        this.language = language ;  this.publisher = publisher ;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }





}
