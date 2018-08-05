package application.p.alex.julyworkout.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.WorkoutDetailActivity;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.utils.Constants;

public class FavoriteWorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
    Context context;
    List<Workout> favoriteWorkouts;

    public FavoriteWorkoutAdapter(Context context, List<Workout> favoriteWorkouts) {
        this.context = context;
        this.favoriteWorkouts = favoriteWorkouts;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkoutViewHolder workoutViewHolder, final int i) {
        workoutViewHolder.initUI(favoriteWorkouts.get(i));
        workoutViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
                intent.putExtra(Constants.WORKOUT_INDEX, workoutViewHolder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteWorkouts.isEmpty() ? 0 : favoriteWorkouts.size();
    }
}
