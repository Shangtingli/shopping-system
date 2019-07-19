package com.springboot.project.onlineShop.model;

import java.io.Serializable;
import javax.persistence.*;

//Important: Now the column name of repository follow a specific format:
// emailId => email_id, shippingAddressId=> shipping_address_id

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {


    public Authorities(){

    }
    public Authorities(Long id, String emailId, String authorities){
        this.id = id;
        this.email_id = emailId;
        this.authorities = authorities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email_id")
    private String email_id;

    @Column(name="authorities")
    private String authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return email_id;
    }

    public void setEmailId(String emailId) {
        this.email_id = emailId;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}