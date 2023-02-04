package by.andrei.library.services;

import by.andrei.library.models.Client;
import by.andrei.library.repositories.ClientRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    private static final String NAME = "Andrei";
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    @Test
    void getClientByName() {
        Client client = new Client();
        client.setName(NAME);
        doReturn(Optional.of(client)).when(clientRepository).findClientByName(NAME);
        Optional<Client> actualResult = clientService.getClientByName(NAME);
        assertTrue(actualResult.isPresent());
        Client expectedResult = new Client(NAME);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}