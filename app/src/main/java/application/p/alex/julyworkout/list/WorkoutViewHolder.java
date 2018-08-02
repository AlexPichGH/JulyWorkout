package application.p.alex.julyworkout.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.model.Workout;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {
    CardView item;
    ImageView favorite;
    private TextView title;
    private TextView description;
    private TextView difficult;
    private TextView recordReps;
    private TextView recordDate;
    private TextView executingTime;
    private ImageView image;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView.findViewById(R.id.workout_list_item);
        favorite = itemView.findViewById(R.id.list_item_favorite);
        title = itemView.findViewById(R.id.list_item_title);
        description = itemView.findViewById(R.id.list_item_description);
        difficult = itemView.findViewById(R.id.list_item_difficult);
        recordReps = itemView.findViewById(R.id.list_item_record_repeats);
        recordDate = itemView.findViewById(R.id.list_item_record_date);
        executingTime = itemView.findViewById(R.id.list_item_executing_time);
        image = itemView.findViewById(R.id.list_item_image);
    }

    public void initUI(Workout workout) {
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        difficult.setText(workout.getDifficult());
        recordReps.setText(String.valueOf(workout.getLastRecordRepeats()));
//            recordDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(workout.getLastRecordDate()));
        executingTime.setText(workout.getExecutingTime());
        image.setImageResource(workout.getImagePreview());
        if (workout.isFavorite()) {
            favorite.setImageResource(R.drawable.ic_favorite_on_24dp);
        } else if (!workout.isFavorite()) {
            favorite.setImageResource(R.drawable.ic_favorite_off_24dp);
        }
    }
}
