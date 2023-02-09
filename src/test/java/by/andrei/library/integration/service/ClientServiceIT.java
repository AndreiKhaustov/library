package by.andrei.library.integration.service;

import by.andrei.library.integration.util.DBContainer;
import by.andrei.library.integration.util.IT;
import by.andrei.library.models.Client;
import by.andrei.library.repositories.ClientRepository;
import by.andrei.library.services.ClientService;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@IT
@AllArgsConstructor
public class ClientServiceIT extends DBContainer {
    private static final String NAME = "Andrei";
    private static final Integer CLIENT_ID = 1;
    private final ClientService clientService;

    @Test
    void checkClientService(){
        List<Client> clients = clientService.getClients();
        clients.forEach(t -> t.getName());
        assertFalse(clients.isEmpty());
    }
    @Test
    void findById(){
        Optional<Client> maybeClient = clientService.findById(CLIENT_ID);
        assertTrue(maybeClient.isPresent());
        maybeClient.ifPresent(user->assertEquals("Andrei", user.getName()));
    }

    @Test
    void delete(){
        assertFalse(clientService.deleteClient(-12));
        assertTrue(clientService.deleteClient(1));
    }


//    @Test
//    void getClientByName() {
//        Optional<Client> actualResult = clientService.getClientByName(NAME);
//        System.out.println(actualResult.get().getId() + " " + actualResult.get().getName());
//        assertTrue(actualResult.isPresent());
//        Client expectedResult = new Client(NAME);
//        expectedResult.setId(9);
//        expectedResult.setAge(28);
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
//    }


//    @Test
//    void addClient() {
//        Client client = new Client();
//        client.setAge(33);
//        client.setName("Dima");
//        clientService.addClient(client);
//    }
}
