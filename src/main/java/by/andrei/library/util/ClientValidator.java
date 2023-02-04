package by.andrei.library.util;

import by.andrei.library.models.Client;
import by.andrei.library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {
    private ClientService clientService;
@Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        if(clientService.getClientByName(client.getName()).isPresent()){
            errors.rejectValue("name", "", "Client with the same name has already exist");
        }
    }
}
