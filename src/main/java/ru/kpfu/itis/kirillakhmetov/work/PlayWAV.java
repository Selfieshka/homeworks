package ru.kpfu.itis.kirillakhmetov.work;

import javax.sound.sampled.*;
import java.io.File;

public class PlayWAV {
    public static void playWavFromFile(File soundFile) {
        SourceDataLine clipSDL = null;
        AudioInputStream ais = null;
        byte[] b = new byte[2048];
        try {
            ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat af = ais.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
            if (AudioSystem.isLineSupported(info)) {
                clipSDL = (SourceDataLine) AudioSystem.getLine(info);
                clipSDL.open(af);
                clipSDL.start();
                int num = 0;
                while ((num = ais.read(b)) != -1)
                    clipSDL.write(b, 0, num);
                clipSDL.drain();
                ais.close();
//                Thread.sleep(clipSDL.getMicrosecondLength()/1000);
                clipSDL.stop();
                clipSDL.close();
            } else {
                System.out.println("Format " + soundFile.getName() + " not support!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
