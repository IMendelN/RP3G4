package Users;

import java.sql.Date;

public class Admin extends User {

    public Admin(int id, String name, int password, String address, String email, Date birthDate, int role) {
        super(id, name, password, address, email, birthDate, role);

    }

}
