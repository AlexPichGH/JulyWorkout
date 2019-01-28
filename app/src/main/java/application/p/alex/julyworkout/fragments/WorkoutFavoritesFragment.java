package application.p.alex.julyworkout.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.list.WorkoutAdapter;
import application.p.alex.julyworkout.model.WorkoutList;

public class WorkoutFavoritesFragment extends Fragment {
    private RecyclerView workoutRecycler;
    private WorkoutAdapter workoutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_favorites, container, false);
        initUi(root);
        return root;
    }

    private void initUi(View root) {
        workoutRecycler = root.findViewById(R.id.workout_favorites_recycler);
        workoutAdapter = new WorkoutAdapter(getContext(), WorkoutList.getInstance(getContext()).getFavoriteWorkouts());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        workoutRecycler.setLayoutManager(linearLayoutManager);
        workoutRecycler.setAdapter(workoutAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
