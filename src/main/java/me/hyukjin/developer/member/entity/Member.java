package me.hyukjin.developer.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "seq")
    private Long seq;

    @Column(name= "id", nullable = false)
    private String id;

    @Column(name= "name", nullable = false)
    private String name;

    private String pwd;

}
