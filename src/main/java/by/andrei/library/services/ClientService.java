package by.andrei.library.services;

import by.andrei.library.models.Client;
import by.andrei.library.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client, int id) {
        client.setId(id);
        clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        Client client = clientRepository.findById(id);
        client.getBooks().forEach(t -> System.out.println(t.getTitle()));
        return clientRepository.findById(id);
    }

    public Optional<Client> getClientByName(String name) {
        return clientRepository.findClientByName(name);
    }
}
