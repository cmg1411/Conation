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
    private List<User> users;

    @OneToOne(mappedBy = "regionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DonationStatus donationStatusId;

    public void addUser(User user) {
        users.add(user);
        user.setRegionId(this);
    }

}
