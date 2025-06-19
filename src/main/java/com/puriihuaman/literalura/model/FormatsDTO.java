package com.puriihuaman.literalura.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FormatsDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    @FormatsTypeUnique
    private String type;

    private String url;

    private UUID bookFormats;

}
