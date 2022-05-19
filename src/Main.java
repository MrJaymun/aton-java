import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<Integer, String> singers = new HashMap<>();

    static ArrayList<SingerThread> threads = new ArrayList<>();

    public static void main(String[] args) {

        // Текст песни
        String[][] lyrics = {
                {"Cher", "They say we're young and we don't know \nWe won't find out until we grow"},
                {"Sonny", "Well I don't know if all that's true \n'Cause you got me, and baby I got you"},
                {"Sonny", "Babe"},
                {"Sonny, Cher", "I got you babe \nI got you babe"},
                {"Cher", "They say our love won't pay the rent \nBefore it's earned, our money's all been spent"},
                {"Sonny", "I guess that's so, we don't have a pot \nBut at least I'm sure of all the things we got"},
                {"Sonny", "Babe"},
                {"Sonny, Cher", "I got you babe \nI got you babe"},
                {"Sonny", "I got flowers in the spring \nI got you to wear my ring"},
                {"Cher", "And when I'm sad, you're a clown \nAnd if I get scared, you're always around"},
                {"Cher", "So let them say your hair's too long \n'Cause I don't care, with you I can't go wrong"},
                {"Sonny", "Then put your little hand in mine \nThere ain't no hill or mountain we can't climb"},
                {"Sonny", "Babe"},
                {"Sonny, Cher", "I got you babe \nI got you babe"},
                {"Sonny", "I got you to hold my hand"},
                {"Cher", "I got you to understand"},
                {"Sonny", "I got you to walk with me"},
                {"Cher", "I got you to talk with me"},
                {"Sonny", "I got you to kiss goodnight"},
                {"Cher", "I got you to hold me tight"},
                {"Sonny", "I got you, I won't let go"},
                {"Cher", "I got you to love me so"},
                {"Sonny, Cher", "I got you babe \nI got you babe \nI got you babe \nI got you babe \nI got you babe"}
        };

        // Создание песни и добавление певцов
        for (int i = 0; i < lyrics.length; i++) {

            SongPart unit = new SongPart(lyrics[i][0].replaceAll("\\s+","").split(","), lyrics[i][1]);
            for (int j = 0; j < unit.singers.length; j++) {
                if(!Main.singers.containsValue(unit.singers[j])){
                    Main.singers.put(Main.singers.size(), unit.singers[j]);
                }
            }
        }

        //Создание объекта, с помощью которого потоки будут обращаться к общему ресурсу
        SongPart a = new SongPart();

      //Создание певцов
        for (int i = 0; i < Main.singers.size(); i++) {
            SingerThread unit = new SingerThread();
            unit.setName(Main.singers.get(i));
            unit.song = a;
            threads.add(unit);
        }

        //Запуск всех потоков
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }

    }
}
