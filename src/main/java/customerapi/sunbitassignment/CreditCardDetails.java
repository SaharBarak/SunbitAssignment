package customerapi.sunbitassignment;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "creditCardDetailsTbl")
public class CreditCardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String cardNumber;
    private String cardExpirationDate;
    private String cvvCode;
    @OneToOne(mappedBy = "creditCardDetails")
    private Customer customer;

    //constructor
    public CreditCardDetails() {

    }
    public CreditCardDetails(String cardNumber, String cardExpirationDate, String cvvCode)
    {
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cvvCode = cvvCode;
    }
    //getters
    public Long getId() {
        return id;
    }
    public String getCardExpirationDate() {
        return cardExpirationDate;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getCvvCode() {
        return cvvCode;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }
    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDetails that = (CreditCardDetails) o;
        return cardNumber.equals(that.cardNumber) && cardExpirationDate.equals(that.cardExpirationDate) && cvvCode.equals(that.cvvCode);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardExpirationDate, cvvCode);
    }
    @Override
    public String toString() {
        return "CreditCardDetails{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardExpirationDate=" + cardExpirationDate +
                ", cvvCode='" + cvvCode + '\'' +
                '}';
    }
}
