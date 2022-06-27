package Sales;

public class WeekSalesReports {
    private int genreQuantities;
    private int platformQuantities;
    private int developerQuantities;

    public WeekSalesReports(int genreQuantities, int platformQuantities, int developerQuantities) {
        this.genreQuantities = genreQuantities;
        this.platformQuantities = platformQuantities;
        this.developerQuantities = developerQuantities;
    }

    public int getGenreQuantities() {
        return genreQuantities;
    }

    public void setGenreQuantities(int genreQuantities) {
        this.genreQuantities = genreQuantities;
    }

    public int getPlatformQuantities() {
        return platformQuantities;
    }

    public void setPlatformQuantities(int platformQuantities) {
        this.platformQuantities = platformQuantities;
    }

    public int getDeveloperQuantities() {
        return developerQuantities;
    }

    public void setDeveloperQuantities(int developerQuantities) {
        this.developerQuantities = developerQuantities;
    }

}
