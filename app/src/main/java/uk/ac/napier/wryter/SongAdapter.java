package uk.ac.napier.wryter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {


    public SongAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Song> songs) {
        super(context, resource, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_song, null);
        }

        Song song = getItem(position);

        //Setting the song's appropriate texts to their respective views
        if (song != null){
            TextView name = (TextView) convertView.findViewById(R.id.list_song_name);
            TextView time = (TextView) convertView.findViewById(R.id.list_song_time);
            TextView lyrics = (TextView) convertView.findViewById(R.id.list_song_lyrics);

            name.setText(song.getName());
            time.setText(song.dateAsString());

            //If the lyrics are too large to preview
            if (song.getLyrics().length() > 50){
                lyrics.setText(song.getLyrics().substring(0, 50) + "…");
            }
            else {
                lyrics.setText(song.getLyrics());
            }
        }
        return convertView;
    }
}
