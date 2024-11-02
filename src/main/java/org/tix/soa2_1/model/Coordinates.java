package org.tix.soa2_1.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coordinates {

    private Long id;

    private BigDecimal x;

    private Integer y;
}
