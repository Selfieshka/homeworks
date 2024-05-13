package ru.kpfu.itis.kirillakhmetov.work;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static ru.kpfu.itis.kirillakhmetov.work.PlayWAV.playWavFromFile;

public class TestTimer {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Время подошло!");
                File wav = new File(PlayWAV.class.getClassLoader().getResource("rocket.wav").getPath());
                playWavFromFile(wav);
            }
        };

        Timer timer = new Timer();

        if (args.length == 2) {
            // Чтение времени из параметров командной строки
            try {
                Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(args[0] + " " + args[1]);
                timer.schedule(task, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            // Чтение времени из файла
            try {
                File file = new File("alarm_params");
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextLine()) {
                    String timeString = scanner.nextLine();
                    Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(timeString);
                    timer.schedule(task, date);
                }
                scanner.close();
            } catch (FileNotFoundException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
