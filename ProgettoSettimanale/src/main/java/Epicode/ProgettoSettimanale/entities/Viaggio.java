package Epicode.ProgettoSettimanale.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "destinazione")
    private String destinazione;
    @Column(name = "data_viaggio")
    private LocalDate dataViaggio;
    @Column(name = "stato_viaggio")
    private String stato;

}
