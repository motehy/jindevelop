package me.hyukjin.developer.member.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "seq")
    private Long seq;

    @Column(name= "email", nullable = false)
    private String eamil;

    @Column(name= "name", nullable = false)
    private String name;

    private String pwd;

}
