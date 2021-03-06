package customerapi.sunbitassignment;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
@Entity
@Table
public class DateOfBirth
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @JsonProperty("year")
    private int year;
    @JsonProperty("month")
    private int month;
    @JsonProperty("day")
    private int day;
    @OneToOne(mappedBy = "dateOfBirth")
    private Customer customer;

    public DateOfBirth(int year,int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public DateOfBirth(){

    }

    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
