package org.tix.soa2_1.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coordinates {

    private Long id;

    private BigDecimal x;

    private Integer y;
}
