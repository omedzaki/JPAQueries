import jakarta.persistence.*;
import java.util.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String genre;
    private int publicationYear;

    @ManyToMany(mappedBy = "books")
    private List<Reader> readers = new ArrayList<>();

    public Book() {}

    public Book(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = year;
    }

    public String getTitle() {
        return title;
    }
}