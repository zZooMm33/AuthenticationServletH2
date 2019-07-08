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
    public static String salt = "";

    /**
     * Зашифровать строку
     *
     * @param value строка
     * @return зашифрованная строка
     */
    public static String encode(String value)
    {

        if (salt.equals("")) setSaltFromProp();
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        value += salt;
        byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    private static void setSaltFromProp()
    {
        salt = "secretCode";
    }
}
