package com.epam.mjc.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {
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
                String[] split = s.split("\n");
                profile.setName(getValue(split[0]));
                profile.setAge(Integer.parseInt(getValue(split[1])));
                profile.setEmail(getValue(split[2]));
                profile.setPhone(Long.parseLong(getValue(split[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public String getValue(String str) {
        return str.substring(str.indexOf(" ") + 1, str.indexOf("\r"));
    }
}

