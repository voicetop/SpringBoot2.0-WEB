package com.test.springtest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString(exclude = {"password"}, callSuper = true)
@Entity
@Table(name = "tb_member")
public class Member implements Serializable, Persistable {

    @Id
    private String id;
    private String name;
    @JsonIgnore
    private String password;

    @Builder
    private Member(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
