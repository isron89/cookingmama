package com.cookingmama.cookingmamaclient.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@Entity
@Table

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "recipeid")
    private String recipeid;

    @Column(name = "userid")
    private String userid;

    public Comment() {
    }

    public Comment(Long id, String text, String recipeid, String userid) {
        this.id=id;
        this.text = text;
        this.recipeid = recipeid;
        this.userid = userid;
    }
    public String getText(){
        return text;
    }
    public Long getId(){
        return id;
    }
    public String getRecipeid(){
        return recipeid;
    }

}
