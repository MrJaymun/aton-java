import java.util.LinkedList;

public class SingerThread extends Thread {

    public SongPart song;
    @Override
    public void run(){

        while (SongPart.song.size() != 0){
            try {
                song.sing(this.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
