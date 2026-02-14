package org.example.OneToOne.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq")
    @SequenceGenerator(name="passport_seq", sequenceName = "passport-sequence", initialValue = 100, allocationSize = 1)
    private int passportId;

    private String passportNumber;
    private String country;

    public Passport() {}

    public Passport(String passportNumber, String country) {
        this.passportNumber = passportNumber;
        this.country = country;
    }

    public int getPassportId() {
        return passportId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
