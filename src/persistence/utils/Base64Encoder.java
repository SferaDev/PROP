package persistence.utils;

import java.io.*;
import java.util.Base64;

/**
 * The type Base 64 encoder
 *
 * @author Alexis Rico Carreto
 */
public class Base64Encoder {
    /**
     * Converts string to object
     *
     * @param string the string
     * @return the object
     */
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

    /**
     * Converts an object to String
     *
     * @param object the object
     * @return the string
     */
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
