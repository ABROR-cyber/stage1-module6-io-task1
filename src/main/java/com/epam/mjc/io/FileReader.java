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

                profile.setName(split[0].split(":")[1].trim());
                profile.setAge(Integer.parseInt(split[1].split(":")[1].trim()));
                profile.setEmail(split[2].split(":")[1].trim());
                profile.setPhone(Long.parseLong(split[3].split(":")[1].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }


}

