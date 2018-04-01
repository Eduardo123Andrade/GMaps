package br.com.novaroma.gmaps.entity;

/**
 * Created by cafe petinho on 28/03/2018.
 */

public class Client {

    private String name;
    private String socialReason;
    private Address address;

    public Client() { }

    public Client(String name, String socialReason, Address address) {
        this.name = name;
        this.socialReason = socialReason;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", socialReason='" + socialReason + '\'' +
                ", address=" + address +
                '}';
    }
}
