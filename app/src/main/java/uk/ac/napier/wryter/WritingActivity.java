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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        mName = (EditText) findViewById(R.id.writing_song_name);
        mLyrics = (EditText) findViewById(R.id.writing_song_lyrics);
    }

    /**
     * Saves the current song the user created
     * @return Whether it saved or not
     */
    private boolean save(){
        String name = this.mName.getText().toString(); //The mName of the song as a String
        String lyrics = this.mLyrics.getText().toString(); //The mLyrics of the song as a String
        long time = System.currentTimeMillis(); //The current time of the system

        Intent intent = new Intent(this, MainActivity.class);

        Song song = new Song(name, lyrics, time); //The current song that is made

        Toast toast= Toast.makeText(getApplicationContext(),"Lyrics saved!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 800); //Making the toast display a little above the bottom navigation bar

        if(Features.saveSong(this, song)){

            //Giving user confirmation on their save
            //Toast.makeText(this, "Lyrics saved!", Toast.LENGTH_SHORT).show();
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
