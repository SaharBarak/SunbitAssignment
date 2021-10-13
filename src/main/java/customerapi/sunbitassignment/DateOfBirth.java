package customerapi.sunbitassignment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dateOfBirthTbl")
public class DateOfBirth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private int year;
    private int month;
    private int day;
    private LocalDate date;
    @OneToOne(mappedBy = "dateOfBirth")
    private Customer customer;

    public DateOfBirth(int year,int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = LocalDate.of(year, month, day);
    }
    public DateOfBirth(LocalDate date)
    {
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.date = date;
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
    public LocalDate getDate() {
        return date;
    }

    public void setDay(int day) {
        this.day = day;
        this.date = LocalDate.of(this.year, this.month, day);
    }

    public void setMonth(int month) {
        this.month = month;
        this.date = LocalDate.of(this.year, month, this.day);
    }

    public void setYear(int year) {
        this.year = year;
        this.date = LocalDate.of(year, this.month, this.day);
    }

}
