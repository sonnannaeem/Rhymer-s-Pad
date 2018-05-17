package uk.ac.napier.wryter;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Features {

    /**
     * Saves songs in a file
     * @param c The given context
     * @param s The song that needs to be saved
     * @return That it is or isn't saved
     */
    public static boolean saveSong(Context c, Song s){
        long key = s.getTime();
        String savedSong = key + ".bin";

        try{
            FileOutputStream fStream;
            ObjectOutputStream oStream;

            fStream = c.openFileOutput(savedSong, c.MODE_PRIVATE);
            oStream = new ObjectOutputStream(fStream);

            oStream.writeObject(s);

            fStream.close();
            oStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<Song> getAllSavedSongs(Context context){
        ArrayList<Song> songs = new ArrayList<>();

        File songDirectory = context.getFilesDir();
        ArrayList<String> songFiles = new ArrayList<>();

        //Getting all the song files into an ArrayList
        for (String element: songDirectory.list()){
            if (element.endsWith(".bin")){
                songFiles.add(element);
            }
        }

        FileInputStream fStream;
        ObjectInputStream oStream;

        for (int i = 0; i < songFiles.size(); i++){
            try{
                fStream = context.openFileInput(songFiles.get(i));
                oStream = new ObjectInputStream(fStream);

                songs.add((Song) oStream.readObject());

                fStream.close();
                oStream.close();

            }
            catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
        }

        return songs;
    }
}
