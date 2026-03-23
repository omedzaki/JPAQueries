import jakarta.persistence.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Uppgift 1

        Author a1 = new Author("George Orwell", "UK");
        Author a2 = new Author("Selma Lagerlof", "Sweden");
        Author a3 = new Author("Murakami", "Japan");

        Book b1 = new Book("1984", "Dystopi", 1949);
        Book b2 = new Book("Animal Farm", "Satir", 1945);
        Book b3 = new Book("Jerusalem", "Roman", 1901);
        Book b4 = new Book("Kafka", "Fantasy", 2002);
        Book b5 = new Book("Norwegian Wood", "Drama", 1987);

        a1.addBook(b1);
        a1.addBook(b2);
        a2.addBook(b3);
        a3.addBook(b4);
        a3.addBook(b5);

        Reader r1 = new Reader("Ali", "a@mail.com");
        Reader r2 = new Reader("Sara", "s@mail.com");
        Reader r3 = new Reader("John", "j@mail.com");

        r1.addBook(b1);
        r2.addBook(b2);
        r3.addBook(b1);

        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

        em.persist(r1);
        em.persist(r2);
        em.persist(r3);

        em.getTransaction().commit();

        // Uppgift 2

        TypedQuery<Author> q2 = em.createQuery(
            "SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.name = :name",
            Author.class
        );
        q2.setParameter("name", "George Orwell");

        Author author = q2.getSingleResult();
        for (Book b : author.getBooks()) {
            System.out.println(b.getTitle());
        }

        // Uppgift 3

        Book book = em.createQuery(
            "SELECT b FROM Book b WHERE b.title = :t",
            Book.class
        ).setParameter("t", "1984").getSingleResult();

        List<Reader> readers = em.createQuery(
            "SELECT r FROM Reader r WHERE :b MEMBER OF r.books",
            Reader.class
        ).setParameter("b", book).getResultList();

        for (Reader r : readers) {
            System.out.println(r);
        }

        // Uppgift 4

        List<Author> authors = em.createQuery(
            "SELECT DISTINCT a FROM Author a JOIN a.books b JOIN b.readers r",
            Author.class
        ).getResultList();

        for (Author a : authors) {
            System.out.println(a.getName());
        }

        // Uppgift 5

        List<Object[]> result = em.createQuery(
            "SELECT a.name, COUNT(b) FROM Author a JOIN a.books b GROUP BY a.name"
        ).getResultList();

        for (Object[] row : result) {
            System.out.println(row[0] + " " + row[1]);
        }

        // Uppgift 6

        List<Book> books = em.createNamedQuery("Book.findByGenre", Book.class)
                .setParameter("genre", "Fantasy")
                .getResultList();

        for (Book b : books) {
            System.out.println(b.getTitle());
        }

        em.close();
        emf.close();
    }
}