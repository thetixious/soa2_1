package org.tix.soa2_1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class Person {

    private Long id;
    private LocalDate birthday;

    private ColorEEnum colorE;

    private ColorHEnum colorH;
}
