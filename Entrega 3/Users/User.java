package Users;

import java.sql.Date;

public class User {

    protected int id;
    protected String name;
    protected int password;
    protected String address;
    protected String email;
    protected Date birthDate;
    protected int role;

    public User(int id, String name, int password, String address, String email, Date birthDate, int role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(int role) {
        this.role = role;
    }
}