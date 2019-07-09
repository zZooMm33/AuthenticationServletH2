package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Класс шифрования паролей
 */
public class EncoderPass
{
    private static String SALT = "secretCode";
    private static String INSTANCE = "SHA-256";
    /**
     * Зашифровать строку
     *
     * @param value строка
     * @return зашифрованная строка
     */
    public static String encode(String value)
    {

        if (SALT.equals("")) setSaltFromProp();
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance(INSTANCE);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        value += SALT;
        byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    private static void setSaltFromProp()
    {
        SALT = "secretCode";
    }
}
