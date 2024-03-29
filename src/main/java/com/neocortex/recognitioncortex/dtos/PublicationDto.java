package com.neocortex.recognitioncortex.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicationDto {

    private Long id;
    private String content;
    private String title;
    private String username;
    private Date createdOn;
    private Date updatedOn;
    private int nb_like;
    private int nb_commenter;
    private int nb_partager;

}
