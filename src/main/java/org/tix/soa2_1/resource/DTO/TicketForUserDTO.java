package org.tix.soa2_1.resource.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.tix.soa2_1.model.Coordinates;
import org.tix.soa2_1.model.TicketType;

import java.time.ZonedDateTime;

@Data
@Getter
@Setter
public class TicketForUserDTO {

    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Double price;
    private String comment;
    private TicketType ticketType;
    private Long personId;



}
