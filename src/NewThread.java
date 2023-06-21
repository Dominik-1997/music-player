import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class NewThread extends Thread {
    private final MusicPlayer musicPlayer;  // Instance of the MusicPlayer class

    public NewThread(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                musicPlayer.isEndCheck();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
