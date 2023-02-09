package by.andrei.library.models;

import by.andrei.library.database.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.FetchProfile;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "Client.books",
        attributeNodes = {
        @NamedAttributeNode("books")
        })
public class Client implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "this field should be filled")
    @Size(min = 2, max = 100, message = "This field should be between 2 and 100 letters")
    private String name;

    @Column(name = "age")
    @Min(3)
    @Max(99)
    private int age;
    @OneToMany(mappedBy = "mainClient")
    private List<Book> books = new ArrayList<>();

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Client(String name) {
        this.name = name;
    }

}
