package Inventory;

import java.sql.Date;

public class Platform extends Game {

    public Platform(int id, String title, String description, String developer, String publisher, Date releaseDate,
            double price, int userRating, int role) {
        super(id, title, description, developer, publisher, releaseDate, price, userRating, role);

    }

}
