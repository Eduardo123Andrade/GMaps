package br.com.novaroma.gmaps.entity;

/**
 * Created by cafe petinho on 28/03/2018.
 */

public class Address {

    private String streat;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;

    public Address() {}

    public Address(String streat, String neighborhood, String city, String state, String postalCode) {
        this.streat = streat;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreat() {
        return streat;
    }

    public void setStreat(String streat) {
        this.streat = streat;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String toString2() {
        return "Address{" +
                "streat=" + streat + "\n" +
                ", neighborhood=" + neighborhood + "\n" +
                ", city=" + city + "\n" +
                ", state=" + state + "\n" +
                ", postalCode=" + postalCode +'}';
    }

    @Override
    public String toString() {
        return  streat + ", " +
                neighborhood + ", " +
                city + ", " +
                state + ", "  +
                postalCode;
    }
}
