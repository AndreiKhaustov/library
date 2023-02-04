package by.andrei.library.repositories;

import by.andrei.library.models.Book;
import by.andrei.library.models.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @EntityGraph(value = "Client.books")
    Client findById(int id);

    Optional<Client> findClientByName(String name);
}
