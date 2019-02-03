package com.rasl.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@ToString
@Getter @Setter
@Entity
public class UserAccount {
    @Id
    @Column
    private Integer UserAccountId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;
}
