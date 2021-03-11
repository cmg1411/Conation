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
@Table(name = "city")
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cityName")
    private String cityName;

    @JoinColumn(name = "regionId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region regionId;

    @Column(name = "donationScore")
    private Long donationScore;

    @OneToMany(mappedBy = "cityId", cascade = CascadeType.ALL)
    private List<User> users;

    public void addUsers(User user) {
        users.add(user);
        user.setCityId(this);
    }
}