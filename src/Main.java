import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);

        MusicPlayer musicPlayer = new MusicPlayer("Music");

        NewThread thread1 = new NewThread(musicPlayer);
        thread1.setDaemon(true);
        thread1.start();

        String response = "";

        while (!response.equals("Q")) {
            System.out.println("P = Play, S = Stop, R = Reset, N = Next, B = Previous, Q = Quit");
            musicPlayer.displayCurrentSong();

            response = scanner.next();
            response = response.toUpperCase();

            switch (response) {
                case "P" -> musicPlayer.play();
                case "S" -> musicPlayer.stop();
                case "R" -> musicPlayer.reset();
                case "N" -> {
                    musicPlayer.next();
                    musicPlayer.play();
                }
                case "B" -> {
                    musicPlayer.previous();
                    musicPlayer.play();
                }
                case "Q" -> {
                    musicPlayer.stop();
                    System.out.println("Quitting...");
                }
                default -> System.out.println("Invalid Response");
            }
        }

        System.out.println("Bye!");
    }
}
