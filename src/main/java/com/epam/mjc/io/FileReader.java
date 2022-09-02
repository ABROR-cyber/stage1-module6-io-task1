package com.epam.mjc.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {
    public static final String start="startWord";
    public static final String end="endWord";
    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream reader = new FileInputStream(file)) {
            byte[] bytes = new byte[reader.available()];
            while (reader.read(bytes)>0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (byte c : bytes) {
                    stringBuilder.append((char) c);
                }
                String s = stringBuilder.toString();
                s = s.replace("\r\n", end);
                s = s.replace(": ", start);
                profile.setName(s.substring(s.indexOf(start) + 9, s.indexOf(end)));
                s = s.substring(s.indexOf(end) + 3);
                profile.setAge(Integer.parseInt(s.substring(s.indexOf(start) + 9, s.indexOf(end))));
                s = s.substring(s.indexOf(end) + 7);
                profile.setEmail(s.substring(s.indexOf(start) + 9, s.indexOf(end)));
                s = s.substring(s.indexOf(end) + 7);
                profile.setPhone(Long.parseLong(s.substring(s.indexOf(start) + 9, s.indexOf(end))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}

