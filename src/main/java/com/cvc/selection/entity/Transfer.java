package com.cvc.selection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tranfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "transferValue")
    private BigDecimal transferValue;
    @Column(name = "tax")
    private BigDecimal tax;
    @Column(columnDefinition = "DATE", name = "transfer_date")
    @NotNull
    private LocalDate transferDate;
    @Column(columnDefinition = "DATE", name = "schedule_date")
    @NotNull
    private LocalDate scheduleDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_origin_account")
    private Account origin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_destination_account")
    private Account destination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_type_transaction")
    private TypeTransaction typeTransaction;

}
