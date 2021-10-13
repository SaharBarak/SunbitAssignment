package customerapi.sunbitassignment;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addressTbl")
public class Address
{
    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String city;
    private String street;
    private String houseNumber;
    private String zipCode;
    @OneToOne(mappedBy = "address")
    private Customer customer;

    //constructors
    public Address(String City, String Street, String HouseNumber, String ZipCode)
    {
        this.city = City;
        this.street = Street;
        this.houseNumber = HouseNumber;
        this.zipCode = ZipCode;
    }
    public Address() {}

    //getters
    public String getCity() {
        return city;
    }
    public String getHouseNumber() {
        return houseNumber;
    }
    public String getStreet() {
        return street;
    }
    public String getZipCode() {
        return zipCode;
    }

    //setters
    public void setCity(String city) {
        this.city = city;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id) && city.equals(address.city) && street.equals(address.street) && houseNumber.equals(address.houseNumber) && zipCode.equals(address.zipCode);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, houseNumber, zipCode);
    }
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
