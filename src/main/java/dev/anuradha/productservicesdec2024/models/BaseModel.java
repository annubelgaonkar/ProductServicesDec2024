package dev.anuradha.productservicesdec2024.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date created_at;
    private Date lastModifiedAt;
    private boolean isDeleted;

}
