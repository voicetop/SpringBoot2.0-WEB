package com.test.springtest.domain;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
@Entity
@Table(name = "tb_group")
public class Group implements Serializable, Persistable {
    @Id
    private String id;
    private String name;

    @Builder
    private Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    private void prePersist(){
        this.isNew = true;
    }

    @PostPersist
    private void postPersist(){
        this.isNew = false;
    }
}
