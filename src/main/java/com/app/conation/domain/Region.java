package com.app.conation.domain;

import com.app.conation.config.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "region")
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "regionName")
    private String regionName;

    @Column(name = "donationScore")
    private Long donationScore;

    @OneToMany(mappedBy = "regionId", cascade = CascadeType.ALL)
    private List<City> cities;

    public void addCity(City city) {
        this.cities.add(city);
        city.setRegionId(this);
    }

}
