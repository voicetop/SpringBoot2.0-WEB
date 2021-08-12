package com.test.springtest.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
@Entity
@Table(name = "tb_group")
public class Group {
    @Id
    private String id;
    private String name;

    @Builder
    private Group(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
