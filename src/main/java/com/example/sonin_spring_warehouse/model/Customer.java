package com.example.sonin_spring_warehouse.model;

import javax.persistence.*;

@Table(name = "customers")
@Entity
public class Customer {

    @Id
    @Column(name = "customer_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Part getRole() {
        return part;
    }

    public void setRole(Part part) {
        this.part = part;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
