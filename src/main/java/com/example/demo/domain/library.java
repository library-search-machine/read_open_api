package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class library {
    @Id
    @SequenceGenerator(
            name = "BOARD_SEQ_GENERATOR",
            sequenceName = "BOARD_SEQ",
            initialValue = 1, allocationSize = 50
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR")
    private Long id;

    @Column(length = 500)
    private String bookName;

    @Column(length = 1000)
    private String authors;
    @Column
    private String publisher;
    @Column
    private String publicationYear;
    @Column
    private String isbn13;
    @Column
    private String vol;

    @Column
    private String classNum;
    @Column(nullable = false)
    private String libraryName;

    @Column(nullable = false,length = 2000)
    private String fullText;
    public library(String bookName,String libraryName,String publisher,String publicationYear,String authors,String classNum,String isbn13,String vol) {
        this.bookName =bookName;
        this.libraryName = libraryName;
        this.publisher = publisher;
        this.publicationYear =publicationYear;
        this.authors = authors;
        this.classNum = classNum;
        this.isbn13 = isbn13;
        this.vol = vol;
        StringBuffer sb = new StringBuffer();
        sb.append(bookName);
        sb.append(vol);
        sb.append(" ");
        sb.append(libraryName);
        sb.append(" ");
        sb.append(publisher);
        sb.append(" ");
        sb.append(authors);
        this.fullText =sb.toString();
    }
}
