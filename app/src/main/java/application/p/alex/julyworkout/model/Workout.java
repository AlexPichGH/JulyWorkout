package application.p.alex.julyworkout.model;

import java.util.Date;

public class Workout {
    private String title;
    private String description;
    private int imageResRef;
    private String difficult;
    private int repeatsCount;
    private int lastRecordRepeats;
    private Date lastRecordDate;
    private long executingTime;

    public Workout() {
    }

    public Workout(String title, String description, String difficult, int repeatsCount, long executingTime) {
        this.title = title;
        this.description = description;
        this.difficult = difficult;
        this.repeatsCount = repeatsCount;
        this.executingTime = executingTime;
    }

    public Workout(String title, String description, int imageResRef, String difficult, int repeatsCount, long executingTime) {
        this.title = title;
        this.description = description;
        this.imageResRef = imageResRef;
        this.difficult = difficult;
        this.repeatsCount = repeatsCount;
        this.executingTime = executingTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public int getRepeatsCount() {
        return repeatsCount;
    }

    public void setRepeatsCount(int repeatsCount) {
        this.repeatsCount = repeatsCount;
    }

    public int getLastRecordRepeats() {
        return lastRecordRepeats;
    }

    public void setLastRecordRepeats(int lastRecordRepeats) {
        this.lastRecordRepeats = lastRecordRepeats;
    }

    public Date getLastRecordDate() {
        return lastRecordDate;
    }

    public void setLastRecordDate(Date lastRecordDate) {
        this.lastRecordDate = lastRecordDate;
    }

    public long getExecutingTime() {
        return executingTime;
    }

    public void setExecutingTime(long executingTime) {
        this.executingTime = executingTime;
    }

    public int getImageResRef() {
        return imageResRef;
    }

    public void setImageResRef(int imageResRef) {
        this.imageResRef = imageResRef;
    }
}
