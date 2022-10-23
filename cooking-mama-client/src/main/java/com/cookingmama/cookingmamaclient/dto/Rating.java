package com.cookingmama.cookingmamaclient.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rate")
    private int rate;

    @Column(name = "recipeid")
    private String recipeid;

    @Column(name = "userid")
    private String userid;

    public Rating() {
    }

    public Rating(Long id, int rate, String recipeid, String userid) {
        this.id=id;
        this.rate = rate;
        this.recipeid = recipeid;
        this.userid = userid;
    }
    public int getRate(){
        return rate;
    }
    public Long getId(){
        return id;
    }
    public String getRecipeid(){
        return recipeid;
    }

}
