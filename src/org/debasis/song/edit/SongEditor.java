package org.debasis.song.edit;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class SongEditor {
	public static String fileDestination = "F:\\Dropbox\\Indian Music\\Raw Songs\\Panjabi";
    public static void main(String[] args) throws IOException {
        File[] songList = new File(fileDestination).listFiles();
        for (File song : songList) {
            if (song.isFile()) {
                String songName = song.getName();
                if(songName.contains(".mp3")) {
                    String songNameWithoutExtension = songName.substring(0, songName.indexOf(".mp3")).trim();
                    try {
                        AudioFile f = AudioFileIO.read(song);
                        Tag tag = f.getTag();
                        String songTitle = tag.getFirst(FieldKey.TITLE).trim();
                        if(!songTitle.equalsIgnoreCase(songNameWithoutExtension)) {
                            tag.setField(FieldKey.TITLE,songNameWithoutExtension);
                            tag.setField(FieldKey.COMMENT,"Downloaded from Heaven");
                            f.commit();
                            System.out.println(songNameWithoutExtension + "####" + songTitle);
                        }
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                }
            }
        }
        //Clearing Originals
        songList = new File(fileDestination).listFiles();
        for (File song : songList) {
            if (song.isFile()) {
                String songName = song.getName();
                if(songName.contains("original"))
                    song.delete();
            }
        }
        //End
    } 
}
