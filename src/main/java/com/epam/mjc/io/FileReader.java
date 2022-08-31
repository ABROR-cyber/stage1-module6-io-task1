package com.epam.mjc.io;

import java.io.*;


public class FileReader {


    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream reader = new FileInputStream(file)) {

            byte[] bytes = new byte[reader.available()];
            while (reader.read(bytes)>0) {

            }
            int count=0;
            StringBuilder stringBuilder = new StringBuilder();
            for (byte c : bytes) {
                stringBuilder.append((char) c);
            }
            stringBuilder.insert(stringBuilder.indexOf("Name") , "1-chapter");
            stringBuilder.insert(stringBuilder.indexOf("Age") , "2-chapter");
            stringBuilder.insert(stringBuilder.indexOf("Email") , "3-chapter");
            stringBuilder.insert(stringBuilder.indexOf("Phone") , "4-chapter");
            String text = stringBuilder.toString();
            String s = text.substring(text.indexOf(": ")+2, text.indexOf("\r"));
            profile.setName(s);
            text=text.substring(text.indexOf("2-chapter"));
            s = text.substring(text.indexOf(": ")+2, text.indexOf("\r"));
            profile.setAge(Integer.parseInt(s));
            text=text.substring(text.indexOf("3-chapter"));
            s = text.substring(text.indexOf(": ")+2, text.indexOf("\r"));
            profile.setEmail(s);
            text=text.substring(text.indexOf("4-chapter"));
            s = text.substring(text.indexOf(": ")+2,text.indexOf("\r"));
            profile.setPhone(Long.parseLong(s));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }
}
