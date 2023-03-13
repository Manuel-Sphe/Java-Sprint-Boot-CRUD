package com.sphesp.contentcalendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;


import java.time.LocalDateTime;
// Need to do data validation
public record Content(


    Integer id,
    @NotEmpty
    @NotBlank
    String title,
    String desc,
    Status status, // Enum
    Type contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    @URL
    String url
    ){
    
}

