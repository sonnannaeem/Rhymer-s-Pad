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
     * @param c The application's current context
     * @param s The song that needs to be saved
     * @return That it is or isn't saved
     */

    //A constant for the file extension
    //Makes it easier for if I want to change the type of file the songs are saved as
    public static final String FILE_EXTENSION = ".bin";

    public static boolean saveSong(Context c, Song s){

        long key = s.getTime();
        String savedSong = key + FILE_EXTENSION; //The name of the file with its type

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

    /**
     * Retrieves any saved songs and de-serializes them into a list
     * @param c The application's current c
     * @return A list of all the saved songs that are de-serialized
     */
    public static ArrayList<Song> getAllSavedSongs(Context c){
        ArrayList<Song> songs = new ArrayList<>();

        File songDirectory = c.getFilesDir(); //Gets the file directory of the app
        ArrayList<String> songFiles = new ArrayList<>();

        //Getting all the song files into an ArrayList
        for (String element: songDirectory.list()){
            if (element.endsWith(FILE_EXTENSION)){
                songFiles.add(element);
            }
        }

        FileInputStream fStream;
        ObjectInputStream oStream;

        for (int i = 0; i < songFiles.size(); i++){
            try{
                fStream = c.openFileInput(songFiles.get(i));
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

    /**
     * Gets a specific song by using its file name
     * @param c The application's current context
     * @param songName The name of the file
     * @return The song that matches the file name
     */
    public static Song getSong(Context c, String songName){

        File songDirectory = c.getFilesDir(); //Gets the file directory of the app
        File songFile = new File(songDirectory, songName);

        if (songFile.exists()){
            FileInputStream fStream;
            ObjectInputStream oStream;

            Song song;

            try {
                fStream = c.openFileInput(songName);
                oStream = new ObjectInputStream(fStream);

                song = (Song) oStream.readObject();

                fStream.close();
                oStream.close();
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return song;
        }
        return null;
    }
}
