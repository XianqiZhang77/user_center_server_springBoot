package com.concordia.rpcDomain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserProfileRequest implements Serializable {

    private static final long serialVersionUID = -6982581963113782356L;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("person_profile")
    private String personalProfile;

    private String country;

    private String province;

    private String city;

    @JsonProperty("street_address")
    private String streetAddress;

    @JsonProperty("area_number")
    private String areaNumber;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
