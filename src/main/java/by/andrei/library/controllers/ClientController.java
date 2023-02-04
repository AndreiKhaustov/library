package by.andrei.library.controllers;

import by.andrei.library.dto.ClientDTO;
import by.andrei.library.models.Client;
import by.andrei.library.services.ClientService;
import by.andrei.library.util.ClientConverter;
import by.andrei.library.util.ClientValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
@Slf4j
public class ClientController {
    private ClientService clientService;
    private ClientValidator clientValidator;

    @Autowired
    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @GetMapping
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        log.info("load from DB all clients");
        return "clients/clients";
    }

    @GetMapping("/{id}")
    public String showClient(Model model, @PathVariable("id") int id) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        model.addAttribute("books", client.getBooks());
        return "clients/client";
    }

    @GetMapping("/add")
    public String getFields(@ModelAttribute("client") ClientDTO clientDTO) {
        return "clients/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client")@Valid Client client, BindingResult bindingResult) {
        clientValidator.validate(client, bindingResult);
        if(bindingResult.hasErrors()){
            return "clients/add";
        }
        clientService.addClient(client);
        return "redirect:/clients";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteClient(@PathVariable("id")int id){
        clientService.deleteClient(id);
        return "redirect:/clients";
    }

    @GetMapping("{id}/edit")
    public String editClient(Model model, @PathVariable("id")int id){
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "clients/edit";

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.PATCH)
    public String updateClient(@ModelAttribute("client")@Valid Client client, @PathVariable("id") int id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "clients/edit";
        }
        clientService.updateClient(client, id);
        return "redirect:/clients";
    }

}
