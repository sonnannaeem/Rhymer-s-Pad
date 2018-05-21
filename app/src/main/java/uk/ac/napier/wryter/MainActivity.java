package uk.ac.napier.wryter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private SongList mSongList = new SongList();
    private RhymeGenerator mRhymeGenerator = new RhymeGenerator();
    private WordGenerator mWordGenerator = new WordGenerator();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                //Displays the song list fragment when on the home tab
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, mSongList).commit();
                    return true;
                //Displays the rhyme fragment when on the dashboard tab
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, mRhymeGenerator).commit();
                    return true;
                //Displays the word fragment when on the notifications tab
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, mWordGenerator).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Making sure that if no fragment has been added, to add the song list fragment first
        if (getSupportFragmentManager().findFragmentById(R.id.main_frameLayout) == null){
            getSupportFragmentManager().beginTransaction().add(R.id.main_frameLayout, mSongList).commit();
        }

    }

    //Create a new song through the add button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent writingActivity = new Intent(this, WritingActivity.class);
        int itemId = item.getItemId();
        int targetId = R.id.menu_song_new;

        //If the menu button that's pressed is the new button, start the writing activity
        if (itemId == targetId) {
            startActivity(writingActivity);
        }

        return true;
    }
}
