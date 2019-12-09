package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exercici1Equips {
    static String directorio = "equipsBinary.data";

    public static void main(String[] args) throws IOException {

        List<Equip> listEquips = new ArrayList<>();

        for (int i = 0; i <4 ; i++) {
            Equip equip = new Equip("equipo" + i, "liga" + i, String.valueOf(i), "equipo"+i);
            listEquips.add(equip);
        }

        ExportaEquips(listEquips);
    }

    public static void ExportaEquips(List<Equip> listEquips) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(directorio, false);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (int i = 0; i <listEquips.size() ; i++) {
            objectOutputStream.writeObject(listEquips.get(i));
        }

        fileOutputStream.close();
        objectOutputStream.close();
    }
}
