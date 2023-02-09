package by.andrei.library.services;

import by.andrei.library.models.Client;
import by.andrei.library.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public boolean deleteClient(Integer id) {
        if(clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> getClientByName(String name) {
        return clientRepository.findClientByName(name);
    }
}
