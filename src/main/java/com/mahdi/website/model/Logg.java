package com.mahdi.website.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collation = "logs")
@Getter
@Setter
@RequiredArgsConstructor
public class Logg {

    @Id
    private String id;
    private Date date;
    private String level;
    private String message;

}
