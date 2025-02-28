package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class AddressModel extends BaseEntity{

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 50)
    private String district;

    @Column(nullable = false, length = 100)
    private String rmc_mc;

    @Column(nullable = false)
    private int ward_no;


    public AddressModel() {
    }

    public AddressModel(Long user_id, String country, String district, String rmc_mc, int ward_no) {
        this.user_id = user_id;
        this.country = country;
        this.district = district;
        this.rmc_mc = rmc_mc;
        this.ward_no = ward_no;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public String getRmc_mc() {
        return rmc_mc;
    }

    public void setRmc_mc(String rmc_mc) {
        this.rmc_mc = rmc_mc;
    }

    public int getWard_no() {
        return ward_no;
    }

    public void setWard_no(int ward_no) {
        this.ward_no = ward_no;
    }
}
