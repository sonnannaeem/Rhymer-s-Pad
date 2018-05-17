package uk.ac.napier.wryter;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/* This class is for representing the different note objects made by the user */
public class Song implements Serializable {

    private String name; //name of the note
    private long time; //Creation date of the note
    private String lyrics; //The content displayed in the note

    /**
     * Constructor for the class
     *
     * @param name   name of the note
     * @param time   Creation date of the note
     * @param lyrics The content displayed in the note
     */
    public Song(String name, String lyrics, long time) {
        this.name = name;
        this.lyrics = lyrics;
        this.time = time;
    }

    /* Getters */

    /**
     * Gets the name of the note
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the note
     *
     * @return The date
     */
    public long getTime() {
        return time;
    }

    /**
     * Gets the song written in the note
     *
     * @return The song
     */
    public String getLyrics() {
        return lyrics;
    }

    /* Setters */

    /**
     * Sets the name of the note
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the time of the note
     *
     * @param time The time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Sets the lyrics of the note
     *
     * @param lyrics The lyrics
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * Gets the current date as a string
     *
     * @return The date as a string
     */
    public String dayAsString() {
        String sDate = DateFormat.getDateInstance().format(new Date());
        return sDate;
    }
}
