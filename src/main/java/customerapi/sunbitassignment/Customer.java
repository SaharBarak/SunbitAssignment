package customerapi.sunbitassignment;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "customersTbl")
public class Customer
{
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="card_id", referencedColumnName = "id")
    private CreditCardDetails creditCardDetails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="birth_id", referencedColumnName = "id")
    private DateOfBirth dateOfBirth;
    private String licenseNumber;

    //constructors
    public Customer(String firstName, String lastName,
                    Address address, CreditCardDetails creditCardDetails,
                    int year,int month, int day, String licenseNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.creditCardDetails = creditCardDetails;
        this.dateOfBirth =  new DateOfBirth(year,month,day);
        this.licenseNumber = licenseNumber;
    }
    public Customer(String firstName, String lastName,
                    Address address, CreditCardDetails creditCardDetails,
                    DateOfBirth dateOfBirth, String licenseNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.creditCardDetails = creditCardDetails;
        this.dateOfBirth =  dateOfBirth;
        this.licenseNumber = licenseNumber;
    }
    public Customer() {

    }

    //getters
    public Address getAddress() {
        return address;
    }
    public CreditCardDetails getCreditCardDetails() {
        return creditCardDetails;
    }
    public DateOfBirth getDateOfBirth() {
        return this.dateOfBirth;
    }
    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }

    //setters
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setCreditCardDetails(CreditCardDetails creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }
    public void setDateOfBirth(int year, int month, int day) {
        this.dateOfBirth = new DateOfBirth(year,month,day);
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) && address.equals(customer.address) &&
                creditCardDetails.equals(customer.creditCardDetails) && dateOfBirth.equals(customer.dateOfBirth) &&
                licenseNumber.equals(customer.licenseNumber);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, creditCardDetails, dateOfBirth, licenseNumber);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", creditCardDetails=" + creditCardDetails +
                ", dateOfBirth=" + dateOfBirth +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}
