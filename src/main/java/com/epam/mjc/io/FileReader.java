package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream reader = new FileInputStream(file)){

            byte[] bytes = new byte[reader.available()];
            reader.read(bytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte c : bytes) {
                stringBuilder.append((char) c);
            }
            String s = stringBuilder.toString();
            System.out.println(s);
            for (int i = 0; i < 4; i++) {
                String temp = s.substring(s.indexOf(" ") + 1, s.indexOf("\r"));
                s = s.substring(s.indexOf("\n") + 1);
                if (i==0)
                    profile.setName(temp);
                else if (i==1)
                    profile.setAge(Integer.parseInt(temp));
                else if (i==2)
                    profile.setEmail(temp);
                else if (i==3)
                    profile.setPhone(Long.parseLong(temp));

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return profile;
    }
}
