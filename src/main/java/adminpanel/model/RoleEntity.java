package adminpanel.model;

import adminpanel.model.enums.Roles;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role", indexes = @Index(name = "person_id", columnList = "person_id", unique = true))
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private Roles role;
}
