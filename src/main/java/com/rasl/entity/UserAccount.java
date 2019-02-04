package com.rasl.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter @Setter
@Entity
@AllArgsConstructor @NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode
public class UserAccount {

    @Id
    @Column
    @JsonProperty
    private Integer userAccountId;

    @JsonProperty
    @Column(nullable = false, unique = true)
    private String login;

    @JsonProperty
    @Column
    private String firstName;

    @JsonProperty
    @Column
    private String middleName;

    @JsonProperty
    @Column
    private String lastName;

}
