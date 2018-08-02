package application.p.alex.julyworkout.model;

import java.util.Date;

public class Workout {
    private String title;
    private String description;
    private int imageResRef;
    private int imagePreview;
    private String difficult;
    private int repeatsCount;
    private int lastRecordRepeats;
    private Date lastRecordDate;
    private String executingTime;
    private boolean isFavorite;

    public Workout() {
    }

    public Workout(String title, String description, String difficult, int repeatsCount, String executingTime) {
        this.title = title;
        this.description = description;
        this.difficult = difficult;
        this.repeatsCount = repeatsCount;
        this.executingTime = executingTime;
    }

    public Workout(String title, String description, int imageResRef, int imagePreview, String difficult, String executingTime) {
        this.title = title;
        this.description = description;
        this.imageResRef = imageResRef;
        this.imagePreview = imagePreview;
        this.difficult = difficult;
        this.executingTime = executingTime;
        this.isFavorite = false;
    }

    public int getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(int imagePreview) {
        this.imagePreview = imagePreview;
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

    public String getExecutingTime() {
        return executingTime;
    }

    public void setExecutingTime(String executingTime) {
        this.executingTime = executingTime;
    }

    public int getImageResRef() {
        return imageResRef;
    }

    public void setImageResRef(int imageResRef) {
        this.imageResRef = imageResRef;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
