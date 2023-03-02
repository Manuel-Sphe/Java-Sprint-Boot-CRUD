package com.sphesp.contentcalendar.model;

import java.time.LocalDateTime;

public record Content(
    Integer id,
    String title,
    String desc,
    Status status, // Enum
    Type contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String url
    ){}

