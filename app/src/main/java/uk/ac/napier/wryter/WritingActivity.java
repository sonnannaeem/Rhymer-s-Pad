package uk.ac.napier.wryter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class WritingActivity extends AppCompatActivity {

    private EditText mName; //Name of the song
    private EditText mLyrics; //Lyrics of the song

    private String mSongFileName; //Name of the song's file
    private Song mExistingSong; //A song that is already made

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        mName = (EditText) findViewById(R.id.writing_song_name);
        mLyrics = (EditText) findViewById(R.id.writing_song_lyrics);

        mSongFileName = getIntent().getStringExtra("SONG_FILE");
        if (mSongFileName != null && !mSongFileName.isEmpty()){ //Checking if the file name exists
            mExistingSong = Features.getSong(this, mSongFileName);

            if (mExistingSong != null){ //Checking if the song exists
                //Setting the content of the view to the already existing song
                mName.setText(mExistingSong.getName());
                mLyrics.setText(mExistingSong.getLyrics());
            }
        }
    }

    /**
     * Saves the current song the user created
     * @return Whether it saved or not
     */
    private boolean save(){
        Toast toast= Toast.makeText(getApplicationContext(),"Lyrics saved!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 800); //Making the toast display a little above the bottom navigation bar

        String name = this.mName.getText().toString(); //The mName of the song as a String
        String lyrics = this.mLyrics.getText().toString(); //The mLyrics of the song as a String
        long time = System.currentTimeMillis(); //The current time of the system

        Intent intent = new Intent(this, MainActivity.class);

        Song song;

        //Checking whether to save as a new song or to update an existing one
        if (mExistingSong == null) {
            song = new Song(name, lyrics, time); //The current song that is made
        }
        else{
            song = new Song(name, lyrics, mExistingSong.getTime()); //The current song that is updated
        }

        if(Features.saveSong(this, song)){

            //Giving user confirmation on their save
            toast.show();

            startActivity(intent); //Return specifically to the song list after saved
            return true; //Saved
        }
        else{

            //Letting user know of error with their attempt to save
            toast.setText("Error: Please make sure you have enough space");
            toast.show();
        }
        return false; //Not Saved
    }

    /* Options Menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    //Saving a new song with the save button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent writingActivity = new Intent(this, WritingActivity.class);
        int itemId = item.getItemId();
        int targetId = R.id.menu_song_save;

        //If the menu button that's pressed is the save button, save the song
        if (itemId == targetId){
            save();
        }

        return true;
    }
}
