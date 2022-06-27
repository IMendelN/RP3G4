package entities;

import java.sql.Date;

public class Client extends User {

    private int platform;
    private String genrePreference;

    public Client(int id, String name, int password, String address, String email, Date birthDate, int role,
            int platform, String genrePreference) {
        super(id, name, password, address, email, birthDate, role);
        this.platform = platform;
        this.genrePreference = genrePreference;
    }

    public int getPlatform() {
        return platform;
    }

    public String getGenrePreference() {
        return genrePreference;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public void setGenrePreference(String genrePreference) {
        this.genrePreference = genrePreference;
    }
}
