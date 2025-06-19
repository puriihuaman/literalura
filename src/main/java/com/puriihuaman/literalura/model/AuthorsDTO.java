package com.puriihuaman.literalura.model;

import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthorsDTO {

    private UUID id;

    private Integer birthYear;

    private Integer deathYear;

    @Size(max = 255)
    private String name;

}
