package com.cvc.selection.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "idCount")
    private Long idCount;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "document")
    @NotBlank
    private String document;

}
