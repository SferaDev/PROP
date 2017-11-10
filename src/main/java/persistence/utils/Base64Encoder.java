package persistence.utils;

import java.io.*;
import java.util.Base64;

public class Base64Encoder {
    public static Object fromString(String string) {
        byte[] data = Base64.getDecoder().decode(string);
        Object object = null;
        try {
            ObjectInputStream objectStream = new ObjectInputStream(new ByteArrayInputStream(data));
            object = objectStream.readObject();
            objectStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static String toString(Serializable object) {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(byteArrayStream);
            objectStream.writeObject(object);
            objectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(byteArrayStream.toByteArray());
    }
}
