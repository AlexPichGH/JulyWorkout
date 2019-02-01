package application.p.alex.julyworkout.model;

public class Workout {
    private String workoutId;
    private String title;
    private String description;
    private String image;
    private String preview;
    private String difficult;
    private int repeatsCount;
    private int lastRecordRepeats;
    private String lastRecordDate;
    private String executingTime;
    private boolean isFavorite;

    public Workout() {
    }

    String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
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

    public String getLastRecordDate() {
        return lastRecordDate;
    }

    public void setLastRecordDate(String lastRecordDate) {
        this.lastRecordDate = lastRecordDate;
    }

    public String getExecutingTime() {
        return executingTime;
    }

    public void setExecutingTime(String executingTime) {
        this.executingTime = executingTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
