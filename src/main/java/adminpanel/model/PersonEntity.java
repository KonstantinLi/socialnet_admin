package adminpanel.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "persons", indexes = @Index(name = "full_name_index", columnList = "first_name, last_name", unique = true))
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String password;

    private String photo;

    private String about;

    private String city;

    private String country;

    @Column(name = "confirmation_code")
    private Integer confirmationCode;

    @Column(name = "is_approved", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isApproved;

    @Column(name = "last_online_time")
    private LocalDateTime lastOnlineTime;

    @Column(name = "is_blocked", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isBlocked;

    @Column (name = "change_password_token")
    private String changePasswordToken;

    @Column (name = "notifications_session_id")
    private String notificationSessionID;

    @Column (name = "online_status")
    private String onlineStatus;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted;

    @Column(name = "deleted_time")
    private LocalDateTime deletedTime;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "friendships", joinColumns = @JoinColumn(name = "src_person_id"), inverseJoinColumns = @JoinColumn(name = "dst_person_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<PersonEntity> srcFriendships = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "friendships", joinColumns = @JoinColumn(name = "dst_person_id"), inverseJoinColumns = @JoinColumn(name = "src_person_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<PersonEntity> dstFriendships = new ArrayList<>();
}
