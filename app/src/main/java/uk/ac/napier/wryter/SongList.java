package uk.ac.napier.wryter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        Toast shortToast = Toast.makeText(getContext(), null, Toast.LENGTH_SHORT);
        //Making toasts display a little above the bottom navigation bar
        shortToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 800);

        mListViewSongs.setAdapter(null);

        ArrayList<Song> songs = Features.getAllSavedSongs(getContext());

        //Checking if there are any saved songs
        if (songs == null || songs.size() == 0) {
            shortToast.setText("No lyrics saved");
            shortToast.show();
            return;
        } else {
            SongAdapter sa = new SongAdapter(getContext(), R.layout.item_song, songs);
            mListViewSongs.setAdapter(sa);
        }
    }
}
