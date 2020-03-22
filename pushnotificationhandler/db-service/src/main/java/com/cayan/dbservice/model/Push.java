package com.cayan.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "push_service")
public class Push {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String json;

    public Push() { }


    @Override
    public String toString() {
        return "UserConfig{" +
                "id=" + id +
                ", json='" + json + '\'' +
                '}';
    }

}
