package model;


import lombok.Data;

import java.io.Serializable;


@Data
public class Coordinates implements Serializable {

    private Long id;

    private double x;

    private Integer y;
}
