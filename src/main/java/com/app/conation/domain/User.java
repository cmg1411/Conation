package com.app.conation.domain;

import com.app.conation.config.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @JoinColumn(name = "cityId")
    @ManyToOne(fetch = FetchType.LAZY)
    private City cityId;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "experiencePoint")
    private Long experiencePoint;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

}