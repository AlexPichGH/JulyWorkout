package application.p.alex.julyworkout.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.WorkoutDetailActivity;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
    Context context;
    List<Workout> workouts;

    public WorkoutAdapter(Context context, List<Workout> workouts) {
        this.context = context;
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkoutViewHolder workoutViewHolder, final int i) {
        workoutViewHolder.initUI(workouts.get(i));
        workoutViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
                intent.putExtra(Constants.WORKOUT_INDEX, workoutViewHolder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
        workoutViewHolder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!WorkoutList.getInstance(context).getWorkout(i).isFavorite()) {
                    workoutViewHolder.favorite.setImageResource(R.drawable.ic_favorite_on_24dp);
                    WorkoutList.getInstance(context).getWorkout(i).setFavorite(true);
                    WorkoutList.getInstance(context).setFavoriteWorkout(i);
                    Toast.makeText(context, "Упражнение добавлено в избранное", Toast.LENGTH_SHORT).show();
                } else if (WorkoutList.getInstance(context).getWorkout(i).isFavorite()) {
                    workoutViewHolder.favorite.setImageResource(R.drawable.ic_favorite_off_24dp);
                    WorkoutList.getInstance(context).getWorkout(i).setFavorite(false);
                    WorkoutList.getInstance(context).removeFavoriteWorkout(i);
                    Toast.makeText(context, "Упражнение удалено из избранного", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.isEmpty() ? 0 : workouts.size();
    }
}
