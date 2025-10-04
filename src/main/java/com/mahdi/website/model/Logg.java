package com.mahdi.website.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document(collection = "logs")
public class Logg {

    @Id
    private String id;
    private Date date;
    private String level;
    private String message;

}

