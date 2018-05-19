package uk.ac.napier.wryter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SongList extends Fragment {

    private ListView mListViewSongs;

    public SongList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListViewSongs = (ListView) getView().findViewById(R.id.fragment_song_list);
    }

    @Override
    public void onResume() {
        super.onResume();

        Toast toast = Toast.makeText(getContext(), null, Toast.LENGTH_SHORT);
        //Making toasts display a little above the bottom navigation bar
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 800);

        mListViewSongs.setAdapter(null);

        ArrayList<Song> songs = Features.getAllSavedSongs(getContext());

        //Checking if there are any saved songs
        if (songs == null || songs.size() == 0) {
            toast.setText("No lyrics saved");
            toast.show();
            return;
        } else {
            //Set the song list to the adapter in order to display the saved songs
            SongAdapter sa = new SongAdapter(getContext(), R.layout.item_song, songs);
            mListViewSongs.setAdapter(sa);
        }

        //An event handler for when the user clicks on one of the songs in the list
        mListViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songName = ((Song) mListViewSongs.getItemAtPosition(position)).getTime() + Features.FILE_EXTENSION;

                //Launches the writing activity once the song in the list is clicked on
                Intent intent = new Intent (getContext(), WritingActivity.class);
                intent.putExtra("SONG_FILE", songName); //Inputs the data of the song file into the new activity
                startActivity(intent);
            }
        });
    }
}
