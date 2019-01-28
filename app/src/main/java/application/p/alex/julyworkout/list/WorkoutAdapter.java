package application.p.alex.julyworkout.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.interfaces.OnWorkoutListItemSelectedListener;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
    private Context context;
    private List<Workout> workouts;
    private OnWorkoutListItemSelectedListener itemSelectedListener;

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
        itemSelectedListener = (OnWorkoutListItemSelectedListener) context;

        workoutViewHolder.item.setOnClickListener(view ->
                itemSelectedListener.onWorkoutListItemSelected(workoutViewHolder.getAdapterPosition()));

        workoutViewHolder.favorite.setOnClickListener(view -> {
            if (!WorkoutList.getInstance(context).getWorkout(workoutViewHolder.getAdapterPosition()).isFavorite()) {
                workoutViewHolder.favorite.setImageResource(R.drawable.ic_favorite_on_24dp);
                WorkoutList.getInstance(context).setFavoriteWorkout(workoutViewHolder.getAdapterPosition());
                WorkoutList.getInstance(context).getWorkout(workoutViewHolder.getAdapterPosition()).setFavorite(true);
                Toast.makeText(context, "Упражнение добавлено в избранное", Toast.LENGTH_SHORT).show();
            } else if (WorkoutList.getInstance(context).getWorkout(workoutViewHolder.getAdapterPosition()).isFavorite()) {
                workoutViewHolder.favorite.setImageResource(R.drawable.ic_favorite_off_24dp);
                WorkoutList.getInstance(context).getWorkout(workoutViewHolder.getAdapterPosition()).setFavorite(false);
                WorkoutList.getInstance(context).removeFavoriteWorkout(workoutViewHolder.getAdapterPosition());
                Toast.makeText(context, "Упражнение удалено из избранного", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.isEmpty() ? 0 : workouts.size();
    }
}
