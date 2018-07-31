package application.p.alex.julyworkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutListActivity extends AppCompatActivity {
    private List<Workout> workoutList;
    private RecyclerView workoutRecycler;
    private WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workoutRecycler = findViewById(R.id.workout_list_recycler);
        workoutAdapter = new WorkoutAdapter(this, WorkoutList.getInstance().getAllWorkouts());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        workoutRecycler.setLayoutManager(linearLayoutManager);
        workoutRecycler.setAdapter(workoutAdapter);
    }

    private static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        ImageView popUp;
        private TextView title;
        private TextView description;
        private TextView difficult;
        private TextView recordReps;
        private TextView recordDate;
        private ImageView image;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.workout_list_item);
            popUp = itemView.findViewById(R.id.list_item_popup_menu);
            title = itemView.findViewById(R.id.list_item_title);
            description = itemView.findViewById(R.id.list_item_description);
            difficult = itemView.findViewById(R.id.list_item_difficult);
            recordReps = itemView.findViewById(R.id.list_item_record_repeats);
            recordDate = itemView.findViewById(R.id.list_item_record_date);
            image = itemView.findViewById(R.id.list_item_image);
        }

        public void initUI(Workout workout) {
            title.setText(workout.getTitle());
            description.setText(workout.getDescription());
            difficult.setText(workout.getDifficult());
            recordReps.setText(String.valueOf(workout.getLastRecordRepeats()));
            recordDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(workout.getLastRecordDate()));
        }
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
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
        public void onBindViewHolder(@NonNull final WorkoutViewHolder workoutViewHolder, int i) {
            workoutViewHolder.initUI(workouts.get(i));
            workoutViewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WorkoutDetailActivity.class);
                    intent.putExtra(Constants.WORKOUT_INDEX, workoutViewHolder.getAdapterPosition());
                    startActivity(intent);
                }
            });
            workoutViewHolder.popUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopUpMenu(view, workoutViewHolder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return workouts.isEmpty() ? 0 : workouts.size();
        }

        private void showPopUpMenu(View view, final int index) {
            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.workout_list_popup_menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.workout_list_popup_delete: {
                            WorkoutList.getInstance().removeWorkout(index);
                            notifyItemRemoved(index);
                            return true;
                        }
                        case R.id.workout_list_popup_add_favorite: {
                            Toast.makeText(context, "Тут будет добавление в избранное " + index, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
        }
    }
}
