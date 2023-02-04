package by.andrei.library.repositories;

import by.andrei.library.models.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

//    @EntityGraph(attributePaths = "mainClient")
//    Book findById(int id);
}
