package entity;

import java.sql.Time;
import java.sql.Date;

public class OperationLog {
    private Time time;
    private Date date;
    private String operation;
    private String authority;

    public OperationLog(Time time, Date date, String operation, String authority) {
        this.time = time;
        this.date = date;
        this.operation = operation;
        this.authority = authority;

    }

    public OperationLog() {
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
