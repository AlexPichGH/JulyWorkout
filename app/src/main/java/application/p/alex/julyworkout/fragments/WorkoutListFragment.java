package application.p.alex.julyworkout.fragments;

import android.content.Context;
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
import application.p.alex.julyworkout.interfaces.OnWorkoutListItemSelectedListener;
import application.p.alex.julyworkout.list.WorkoutAdapter;
import application.p.alex.julyworkout.model.WorkoutList;

public class WorkoutListFragment extends Fragment {
    OnWorkoutListItemSelectedListener itemSelectedListener;
    private RecyclerView workoutRecycler;
    private WorkoutAdapter workoutAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemSelectedListener = (OnWorkoutListItemSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_list, container, false);
        initUi(root);
        return root;
    }

    private void initUi(View root) {
        workoutRecycler = root.findViewById(R.id.workout_list_recycler);
        workoutAdapter = new WorkoutAdapter(getContext(), WorkoutList.getInstance(getContext()).getAllWorkouts());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        workoutRecycler.setLayoutManager(linearLayoutManager);
        workoutRecycler.setAdapter(workoutAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        workoutAdapter.notifyDataSetChanged();
    }

    public void refreshList() {
        workoutAdapter.notifyDataSetChanged();
    }
}
