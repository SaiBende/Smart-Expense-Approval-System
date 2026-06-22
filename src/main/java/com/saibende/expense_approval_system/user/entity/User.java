package com.saibende.expense_approval_system.user.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.saibende.expense_approval_system.expense.entity.Expense;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false)
    private String fullname;

    @Column(nullable= false, unique = true,length = 50)
    private String username;

    @Column(nullable= false)
    private String email;

    @Column(nullable= false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable= false)
    private Role role;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Expense> expenses;

}
