import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private final File[] listOfSongs;
    private int currentSong;
    Clip clip;

    public MusicPlayer(String folderPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File folder = new File(folderPath);
        listOfSongs = folder.listFiles();
        currentSong = 0;

        if (listOfSongs != null) {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(listOfSongs[currentSong]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
    }
    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void reset() {
        clip.setMicrosecondPosition(0);
    }

    public void next() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentSong++;
        // Wrap around to the first song if reached the end of a list
        if (currentSong >= listOfSongs.length) {
            currentSong = 0;
        }
        switchSong();
    }

    public void previous() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentSong--;
        if (currentSong < 0) {
            currentSong = listOfSongs.length - 1;
        }
        switchSong();
    }

    public void displayCurrentSong() {
        System.out.println("Current song: " + listOfSongs[currentSong].getName());

    }

    public void isEndCheck() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        long length = clip.getMicrosecondLength();
        long position = clip.getMicrosecondPosition();

        if (length == position){
            this.next();
            this.play();
            System.out.println("Current song: " + listOfSongs[currentSong].getName());
        }
    }

    private void switchSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.close();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(listOfSongs[currentSong]);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }
}
