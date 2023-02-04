package by.andrei.library.dto;

import by.andrei.library.models.Client;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookDTO {
    @NotEmpty(message = "this field should be filled")
    private String title;
    @Min(1800)
    @Max(2023)
    private int year;
//    @ManyToOne
//    @JoinColumn(name = "client_id", referencedColumnName = "id")
//    private Client mainClient;

}
