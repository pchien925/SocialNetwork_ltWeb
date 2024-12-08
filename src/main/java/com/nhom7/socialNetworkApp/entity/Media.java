package com.nhom7.socialNetworkApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medias")
public class Media extends AbstractEntity<Long>{
    @Column(name = "media_key")
    private String mediaKey;

    @Column(name = "caption")
    private String caption;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;
}
