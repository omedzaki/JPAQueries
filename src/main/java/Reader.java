import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Reader {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Reader() {}

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return name + " - " + email;
    }
}