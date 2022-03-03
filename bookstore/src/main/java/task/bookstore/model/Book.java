package task.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private int price;
    @Column
    private String authorName;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
