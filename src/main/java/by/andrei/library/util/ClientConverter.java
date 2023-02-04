package by.andrei.library.util;

import by.andrei.library.dto.ClientDTO;
import by.andrei.library.models.Client;

public class ClientConverter {

    public static Client convertToClient(ClientDTO clientDTO){
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setAge(clientDTO.getAge());
        return client;
    }
}
