package com.rasl.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@ToString
@Getter @Setter
@Entity
public class UserAccount {
    @Id
    @Column
    private Integer UserAccountId;

    @Column
    @NonNull
    @NotEmpty
    private String login;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;
}
