package com.neocortex.recognitioncortex.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicationDto {

    private Long id;
    private String content;
    private String title;
    private String username;
    private Instant createdOn;

}
