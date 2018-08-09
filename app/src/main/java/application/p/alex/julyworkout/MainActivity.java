package application.p.alex.julyworkout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import application.p.alex.julyworkout.fragments.WorkoutDetailFragment;
import application.p.alex.julyworkout.fragments.WorkoutFavoritesFragment;
import application.p.alex.julyworkout.fragments.WorkoutListFragment;
import application.p.alex.julyworkout.interfaces.OnWorkoutListItemSelectedListener;
import application.p.alex.julyworkout.interfaces.RefreshWorkoutListListener;
import application.p.alex.julyworkout.model.WorkoutList;

public class MainActivity extends AppCompatActivity implements OnWorkoutListItemSelectedListener, RefreshWorkoutListListener {
    WorkoutListFragment listFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        listFragment = new WorkoutListFragment();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, listFragment).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WorkoutDetailFragment detailFragment = WorkoutDetailFragment.initFragment(0);
            fragmentManager.beginTransaction().replace(R.id.list_container, listFragment).commit();
            fragmentManager.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_to_favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!WorkoutList.getInstance(this).getFavoriteWorkouts().isEmpty()) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new WorkoutFavoritesFragment()).addToBackStack(getString(R.string.tag_fragment_favorites)).commit();
            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentManager.beginTransaction().replace(R.id.list_container, new WorkoutFavoritesFragment()).commit();
            }
        } else {
            Toast.makeText(this, R.string.toast_empty_favorites, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWorkoutListItemSelected(int index) {
        WorkoutDetailFragment detailFragment = WorkoutDetailFragment.initFragment(index);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, detailFragment).addToBackStack(null).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentManager.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }
    }

    @Override
    public void refreshList() {
        listFragment.refreshList();
    }
}
