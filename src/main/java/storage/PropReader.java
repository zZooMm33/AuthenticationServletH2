package storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropReader
{
    public static String getVal(String key)
    {

        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = PropReader.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null)
        {
            try
            {
                prop.load(inputStream);
                return prop.getProperty(key);

            } catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }
}
