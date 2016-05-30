package pe.dwkim.hapduk_terminal.Model;

/**
 * Created by dwkim on 16. 5. 25..
 */
public class Destination {
    private int destinationId;
    private String destinationName;
    private int divisionId;
    private String divisionName;

    public Destination(int destinationId, String destinationName, int divisionId, String divisionName) {
        this.destinationId = destinationId;
        this.destinationName = destinationName;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destinationId=" + destinationId +
                ", destinationName='" + destinationName + '\'' +
                ", divisionId=" + divisionId +
                ", divisionName=" + divisionName +
                '}';
    }
}
