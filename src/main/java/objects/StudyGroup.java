package objects;

public class StudyGroup {
    private long group_id;
    private String group_name;
    private long curator_id;


    @Override
    public String toString() {
        return "StudyGroup{" +
                "group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
                ", curator_id='" + curator_id +
                '}';
    }

    public StudyGroup(long group_id, String group_name, long curator_id) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.curator_id = curator_id;

    }

    public long getGroup_id() {
        return group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public long getCurator_id() {
        return curator_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public void setCurator_id(long curator_id) {
        this.curator_id = curator_id;
    }
}
