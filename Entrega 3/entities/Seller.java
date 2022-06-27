package entities;

import java.sql.Date;

public class Seller extends User {

    public Seller(int id, String name, int password, String address, String email, Date birthDate, int role) {
        super(id, name, password, address, email, birthDate, role);

    }

}
