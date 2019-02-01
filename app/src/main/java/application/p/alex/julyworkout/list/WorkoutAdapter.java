package application.p.alex.julyworkout.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
            if (!WorkoutList.getInstance(context).getFavoriteWorkouts().contains(workouts.get(i))) {
                workoutViewHolder.favorite.setImageResource(R.drawable.ic_favorite_on_24dp);
                WorkoutList.getInstance(context).addWorkoutToFavorite(workouts.get(i));
                Snackbar.make(workoutViewHolder.itemView, "Упражнение добавлено в избранное", Snackbar.LENGTH_SHORT).show();

            } else if (WorkoutList.getInstance(context).getFavoriteWorkouts().contains(workouts.get(i))) {
                workoutViewHolder.favorite.setImageResource(R.drawable.ic_favorite_off_24dp);
                WorkoutList.getInstance(context).deleteWorkoutFromFavorite(workouts.get(i));
                Snackbar.make(workoutViewHolder.itemView, "Упражнение удалено из избранного", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.isEmpty() ? 0 : workouts.size();
    }
}
