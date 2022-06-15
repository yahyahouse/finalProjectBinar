package com.binar.dummyproject.model;

import com.binar.dummyproject.Enumeration.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Roles() {
        // This method is empty because it is needed when object is ready
    }
    public Roles(Integer roleId, ERole name){
        this.roleId = roleId;
        this.name = name;
    }

}
