package com.mahdi.website.model;

import lombok.Data;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@Document(collection  = "logs")
public class Logg {

    @Id
    private String id;
    private Date date;
    private String level;
    private String message;

}

