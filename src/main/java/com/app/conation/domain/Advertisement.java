package com.app.conation.domain;

import com.app.conation.config.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "advertisement")
public class Advertisement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "advertisementName")
    private String advertisementName;

    @Column(name = "url")
    private String url;

    @Column(name = "length")
    private Long length;

    @Column(name = "price")
    private Long price;

    @Column(name = "advertisementOwnerId")
    private Long advertisementOwnerId;

    @Column(name = "advertisementCategory")
    @Enumerated(EnumType.STRING)
    private AdvertisementCategory advertisementCategory;

    @Column(name = "viewCount")
    private Long viewCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Getter
    private enum AdvertisementCategory{
        FOOD, PRODUCT, SERVICE, CULTURE
    }
}