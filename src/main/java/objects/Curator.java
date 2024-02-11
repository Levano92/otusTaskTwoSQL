package objects;

public class Curator {
    private long curator_id;
    private String curator_fio;

    @Override
    public String toString() {
        return "Student{" +
                "subscriber_id=" + curator_id +
                ", deviceName='" + curator_fio +
                '}';
    }

    public Curator(long curator_id, String curator_fio) {
        this.curator_id = curator_id;
        this.curator_fio = curator_fio;
     }

    public long getCurator_id() {
        return curator_id;
    }

    public String getCurator_fio() {
        return curator_fio;
    }


}
