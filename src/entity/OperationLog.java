package entity;

import service.OperationsType;

import javax.persistence.*;


import java.util.Date;
import java.util.Objects;

@Entity
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(value = TemporalType.TIME)
    private Date time;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    private OperationsType operation;
    private String authority;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;


    public OperationLog(Date time, Date date, OperationsType operation, String authority) {
        this.time = time;
        this.date = date;
        this.operation = operation;
        this.authority = authority;

    }

    public OperationLog() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OperationsType getOperation() {
        return operation;
    }

    public void setOperation(OperationsType operation) {
        this.operation = operation;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationLog that = (OperationLog) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(time, that.time) &&
                Objects.equals(date, that.date) &&
                Objects.equals(operation, that.operation) &&
                Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, operation, authority);
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "time=" + time +
                ", date=" + date +
                ", operation='" + operation + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
