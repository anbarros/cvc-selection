package com.cvc.selection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "idAccount")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCount;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "numberAccount")
    @NotBlank
    private String numberAccount;
    @Column(name = "document")
    @NotBlank
    private String document;

}
