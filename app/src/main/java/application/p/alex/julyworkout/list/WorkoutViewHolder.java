package application.p.alex.julyworkout.list;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import application.p.alex.julyworkout.GlideApp;
import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.model.Workout;

class WorkoutViewHolder extends RecyclerView.ViewHolder {
    CardView item;
    ImageView favorite;
    private TextView title;
    private TextView description;
    private TextView difficult;
    private TextView recordReps;
    private TextView recordDate;
    private TextView executingTime;
    private ImageView image;

    WorkoutViewHolder(@NonNull View itemView) {
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

    void initUI(Workout workout) {
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        difficult.setText(workout.getDifficult());
        recordReps.setText(String.valueOf(workout.getLastRecordRepeats()));
        recordDate.setText(String.valueOf(workout.getLastRecordDate()));
        executingTime.setText(workout.getExecutingTime());
        try {
            setImage(workout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (workout.isFavorite()) {
            favorite.setImageResource(R.drawable.ic_favorite_on_24dp);
        } else if (!workout.isFavorite()) {
            favorite.setImageResource(R.drawable.ic_favorite_off_24dp);
        }
    }

    private void setImage(Workout workout) throws IOException {
        InputStream imageStream = item.getContext().getAssets().open(workout.getPreview());

        GlideApp
                .with(itemView)
                .load(Drawable.createFromStream(imageStream, null))
                .centerInside()
                .into(image);
    }
}
