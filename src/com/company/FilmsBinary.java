package com.company;

import java.io.*;

public class FilmsBinary {
    static String directorio = "filmsBinary.bin";


    public static void main(String[] args) throws IOException {

        FileOutputStream sortida = new FileOutputStream(directorio);
        DataOutputStream dataOutputStream = new DataOutputStream(sortida);

        int caracs[] = new int[10];

        for (int i = 0; i < caracs.length ; i++) {
            dataOutputStream.write(i+1);
            dataOutputStream.write(199+i);
        }

        dataOutputStream.close();

    }
}
