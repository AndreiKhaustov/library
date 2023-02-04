package by.andrei.library.dto;

import by.andrei.library.models.Book;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class ClientDTO {

    private String name;
    private int age;


}
