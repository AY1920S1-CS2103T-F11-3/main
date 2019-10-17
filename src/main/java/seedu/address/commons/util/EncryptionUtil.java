package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * A class for handling encryption and decryption.
 */
public class EncryptionUtil {
    /**
     * An enumeration to represent encryption mode and decryption mode.
     */
    private enum EncryptionMode {
        ENCRYPT,
        DECRYPT
    }

    private static final byte[] salt = {
        (byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7,
        (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17
    };

    private static final int iteration = 68;

    /**
     * Encrypts or decrypts a byte array using a given password.
     * @param input the byte array to be encrypted or decrypted.
     * @param password the password used for encryption or decryption.
     * @param mode whether to encrypt or decrypt the byte array.
     * @return the encrypted or decrypted byte array.
     */
    private static byte[] cipherBytes(byte[] input, String password, EncryptionMode mode)
            throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
        SecretKey key = keyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, iteration);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
        switch (mode) {
        case ENCRYPT:
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            break;
        case DECRYPT:
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            break;
        default:
            break;
        }
        return cipher.doFinal(input);
    }

    /**
     * Encrypts a byte array using a given password.
     * @param input the byte array to be encrypted.
     * @param password the password used for encryption.
     * @return the encrypted byte array.
     */
    public static byte[] encryptBytes(byte[] input, String password) throws GeneralSecurityException {
        return cipherBytes(input, password, EncryptionMode.ENCRYPT);
    }

    /**
     * Decrypts a byte array using a given password.
     * @param input the byte array to be decrypted.
     * @param password the password used for encryption.
     * @return the decrypted byte array.
     */
    public static byte[] decryptBytes(byte[] input, String password) throws GeneralSecurityException {
        return cipherBytes(input, password, EncryptionMode.DECRYPT);
    }

    /**
     * Encrypts a string into byte array using a given password.
     * @param input the string to be encrypted.
     * @param password the password used for encryption.
     * @return the encrypted byte array.
     */
    public static byte[] encryptBytesFromString(String input, String password) throws GeneralSecurityException {
        return encryptBytes(input.getBytes(), password);
    }

    /**
     * Decrypts a byte array into string using a given password.
     * @param input the byte array to be decrypted.
     * @param password the password used for decryption.
     * @return the decrypted string.
     */
    public static String decryptBytesToString(byte[] input, String password) throws GeneralSecurityException {
        return new String(decryptBytes(input, password));
    }

    /**
     * Encrypts a file using the given file path and password.
     * @param path the path of the file.
     * @param password the password used for decryption.
     * @throws IOException if the encryption fails.
     */
    public static void encryptFile(String path, String password) throws IOException, GeneralSecurityException {
        Path oldPath = Paths.get(path);
        byte[] fileData = Files.readAllBytes(oldPath);
        byte[] encryptedData = encryptBytes(fileData, password);
        Path newPath = Paths.get(oldPath.getParent().toString()
                + "/[LOCKED] " + oldPath.getFileName().toString());
        Files.write(newPath, encryptedData);
        Files.deleteIfExists(oldPath);
    }

    /**
     * Decrypts a file using the given file path and password.
     * @param path the path of the file.
     * @param password the password used for decryption.
     * @throws IOException if the decryption fails.
     */
    public static void decryptFile(String path, String password) throws IOException, GeneralSecurityException {
        Path oldPath = Paths.get(path);
        byte[] fileData = Files.readAllBytes(oldPath);
        byte[] decryptedData = decryptBytes(fileData, password);
        Path newPath = Paths.get(oldPath.getParent().toString()
                + "/" + oldPath.getFileName().toString().replace("[LOCKED] ", ""));
        Files.write(newPath, decryptedData);
        Files.deleteIfExists(oldPath);
    }
}
