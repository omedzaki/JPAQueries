import jakarta.persistence.*;
import java.util.*;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String nationality;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }
}