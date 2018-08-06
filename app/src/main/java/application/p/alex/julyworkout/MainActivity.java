package application.p.alex.julyworkout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import application.p.alex.julyworkout.model.WorkoutList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new WorkoutListFragment()).addToBackStack(getString(R.string.tag_fragment_workout_list)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_to_favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!WorkoutList.getInstance(this).getFavoriteWorkouts().isEmpty()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkoutFavoritesFragment()).addToBackStack(getString(R.string.tag_fragment_favorites)).commit();
            item.setEnabled(false);
        } else {
            Toast.makeText(this, R.string.toast_empty_favorites, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
