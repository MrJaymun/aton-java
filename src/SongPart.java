import java.util.LinkedList;

public class SongPart {

    //Текст песни, с которым будут работать потоки
    public static LinkedList<SongPart> song = new LinkedList<SongPart>();


    public SongPart(String[] singers, String songPartName) {
        this.singers = singers;
        this.songPartName = songPartName;
        //Добавление строки в песню
        song.add(this);
    }

    public SongPart(){

    }

    //Текст строки песню
    public String songPartName;

    //Список тех, кто поет строку
    public String[] singers;

    //public static

    public synchronized void  sing(String name) throws InterruptedException {

        if (song.size() != 0) {

            //Если поток не должен петь в данный момент, то он засыпает
                if(!name.equals(song.getFirst().singers[0])){

                    wait();
                }

                //Проверка, что во время сна потока песня не кончилась
            if (song.size() != 0) {

                //Выводится строка песни, поток убирается из списка певцов, становясь null
                for (int i = 0; i < song.getFirst().singers.length; i++) {
                    if(name.equals(song.getFirst().singers[i])){
                        System.out.println(name + ": " + song.getFirst().songPartName);
                        song.getFirst().singers[i] = null;
                    }
                }

                //Проверка на то, все ли певцы спели строчку песни
                boolean isEmpty = true;
                for (int j = 0; j < song.getFirst().singers.length; j++) {

                    if(song.getFirst().singers[j] != null){
                        isEmpty = false;
                    }
                }
                //Если строчка была спета всеми своими певцами, то все певцы пробуждаются и переходят к новой строке
                if(isEmpty){
                    song.removeFirst();
                }
                notifyAll();

            }

        }
    }

}
