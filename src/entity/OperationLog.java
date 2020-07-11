package entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    private Time time;
    private String operation;
    private String authority;

    public OperationLog() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationLog that = (OperationLog) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(operation, that.operation)&&
                Objects.equals(authority, that.authority)&&
                Objects.equals(time,that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date,time, operation);
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                ", date=" + date +
                ", operation='" + operation + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
