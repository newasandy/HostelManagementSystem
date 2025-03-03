package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 50)
    private String district;

    @Column(name = "rmc_mc", nullable = false, length = 100)
    private String rmcMc;

    @Column(name = "ward_no", nullable = false)
    private int wardNo;


    public Address() {
    }

    public Address(Users user, String country, String district, String rmcMc, int wardNo) {
        this.user = user;
        this.country = country;
        this.district = district;
        this.rmcMc = rmcMc;
        this.wardNo = wardNo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRmcMc() {
        return rmcMc;
    }

    public void setRmcMc(String rmcMc) {
        this.rmcMc = rmcMc;
    }

    public int getWardNo() {
        return wardNo;
    }

    public void setWardNo(int wardNo) {
        this.wardNo = wardNo;
    }
}
