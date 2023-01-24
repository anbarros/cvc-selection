package com.cvc.selection.entity;

import com.cvc.selection.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tranfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "transferValue")
    private BigDecimal transferValue;
    @Column(name = "fee")
    private BigDecimal fee;
    @Column(columnDefinition = "DATE", name = "transfer_date")
    @NotNull
    private LocalDate transferDate;
    @Column(columnDefinition = "DATE", name = "schedule_date")
    @NotNull
    private LocalDate scheduleDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fk_origin_account")
    private Account accountOrigin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fk_destination_account")
    private Account accountDestination;

    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionType;

}
