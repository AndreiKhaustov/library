package by.andrei.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @NotEmpty(message = "this field should be filled")
    private String title;
    @Column(name = "year")
    @Min(1800)
    @Max(2023)
    private int year;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client mainClient;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }
}
