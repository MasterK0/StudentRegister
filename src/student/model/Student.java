package student.model;

import java.awt.*;
import java.sql.Timestamp;

public class Student {

    private Integer id;
    private String fullName;
    private String address;

    public Student(Integer id, String fullName, String address) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
    }

    public Student(String fullName, String address) {
        this.fullName = fullName;
        this.address = address;
    }

    public Student() {

    }


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
