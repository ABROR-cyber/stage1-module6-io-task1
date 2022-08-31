package com.epam.mjc.io;

import java.io.*;


public class FileReader {


    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream reader = new FileInputStream(file)) {

            byte[] bytes = new byte[reader.available()];
            reader.read(bytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte c : bytes) {
                if (c == ' ')
                    stringBuilder.append("start");
                else
                    stringBuilder.append((char) c);
            }
            stringBuilder.insert(stringBuilder.indexOf("Age")-1,"end");
            stringBuilder.insert(stringBuilder.indexOf("Email")-1,"end");
            stringBuilder.insert(stringBuilder.indexOf("Phone")-1,"end");
            stringBuilder.insert(stringBuilder.length()-1,"end");

            String s = stringBuilder.toString();
            for (int i = 0; i < 4; i++) {
                String text = s.substring(s.indexOf("start") + 5, s.indexOf("end")-1);
                if (i==0)
                    profile.setName(text);
                else if (i==1)
                    profile.setAge(Integer.parseInt(text));
                else if (i==2)
                    profile.setEmail(text);
                else if (i==3)
                    profile.setPhone(Long.parseLong(text));
                if (i != 3)
                        s = s.substring(s.indexOf("end") + 3);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }
}
