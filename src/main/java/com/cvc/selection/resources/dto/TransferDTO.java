package com.cvc.selection.resources.dto;

import jakarta.validation.constraints.NotBlank;
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
public class TransferDTO {
        private Long id;
        @NotNull
        private BigDecimal transferValue;
        private BigDecimal fee;
        private LocalDate transferDate;
        private LocalDate scheduleDate;
        @NotBlank
        private Long numberAccountOrigin;
        @NotBlank
        private Long numberAccountDestination;

}
