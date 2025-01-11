package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person implements Serializable {

    private Long id;
    private LocalDate birthday;

    @JsonProperty("Color_E")
    private Color_E colorE;

    @JsonProperty("Color_H")
    private Color_H colorH;
}
