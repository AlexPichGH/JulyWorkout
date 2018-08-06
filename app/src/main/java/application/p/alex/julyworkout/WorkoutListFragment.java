package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import application.p.alex.julyworkout.list.WorkoutAdapter;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;

public class WorkoutListActivity extends Fragment {
    private List<Workout> workoutList;
    private RecyclerView workoutRecycler;
    private WorkoutAdapter workoutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_workout_list, container, false);

        workoutRecycler = root.findViewById(R.id.workout_list_recycler);
        workoutAdapter = new WorkoutAdapter(getContext(), WorkoutList.getInstance(getContext()).getAllWorkouts());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        workoutRecycler.setLayoutManager(linearLayoutManager);
        workoutRecycler.setAdapter(workoutAdapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
