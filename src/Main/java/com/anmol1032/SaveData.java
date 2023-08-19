package com.anmol1032;

import com.anmol1032.data.Data;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SaveData {
    Data data;
    File file = new File(Objects.requireNonNull(getClass().getResource("/Default.ser")).toURI());

    public SaveData() throws URISyntaxException {
        data = new Data();
        read();
    }

    public void read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            data = (Data) ois.readObject();
            data.update();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void write() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(data);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
