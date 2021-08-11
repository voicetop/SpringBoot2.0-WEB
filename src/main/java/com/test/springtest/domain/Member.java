package com.test.springtest.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString(exclude = {"password"}, callSuper = true)
@Entity
@Table(name = "member")
public class Member {
    @Id
    private String id;
    private String name;
    private String password;

    @Builder
    private Member(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
