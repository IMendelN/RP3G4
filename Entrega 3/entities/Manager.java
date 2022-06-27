package entities;

import java.sql.Date;

public class Manager extends User {

    public Manager(int id, String name, int password, String address, String email, Date birthDate, int role) {
        super(id, name, password, address, email, birthDate, role);

    }

}
