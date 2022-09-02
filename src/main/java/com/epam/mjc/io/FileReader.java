package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {
    final String start="startWord";
    final String end="endWord";
    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream reader = new FileInputStream(file)) {
            byte[] bytes = new byte[reader.available()];
            reader.read(bytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte c : bytes) {
                stringBuilder.append((char) c);
            }
            String s = stringBuilder.toString();
            s=s.replace("\r\n",end);
            s=s.replace(": ",start);
            profile.setName(s.substring(s.indexOf(start)+5,s.indexOf(end)));
            s=s.substring(s.indexOf(end)+3);
            profile.setAge(Integer.parseInt(s.substring(s.indexOf(start)+5,s.indexOf(end))));
            s=s.substring(s.indexOf(end)+3);
            profile.setEmail(s.substring(s.indexOf(start)+5,s.indexOf(end)));
            s=s.substring(s.indexOf(end)+3);
            profile.setPhone(Long.parseLong(s.substring(s.indexOf(start)+5,s.indexOf(end))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}

