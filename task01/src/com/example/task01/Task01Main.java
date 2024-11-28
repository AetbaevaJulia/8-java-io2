package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException {
        ProcessBuilder process = new ProcessBuilder("cmd.exe", "/c",
                ".\\ffprobe -v error -of flat -show_format \"" + file.getAbsolutePath() +"\"");
        process.directory(new File("C:\\ffmpeg\\bin"));
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(process.start().getInputStream()))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                if(str.contains("title")) {
                    String name = str.split("=")[1];
                    return name.substring(1, name.length()-1);
                }
            }
        }
        return null;
    }

}