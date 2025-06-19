package com.puriihuaman.literalura.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BooksDTO {

    private UUID id;

    @Size(max = 255)
    @BooksTitleUnique
    private String title;

    private String summaries;

    private List<@Size(max = 255) String> bookshelves;

    private List<@Size(max = 255) String> languages;

    private Boolean copyright;

    @Size(max = 255)
    private String mediaType;

    @NotNull
    private UUID formats;

    private Integer downloadCount;

    private List<@Size(max = 255) String> subjects;

    private List<@Size(max = 255) String> authors;

    private List<@Size(max = 255) String> translators;

    private UUID authorsBooks;

}
