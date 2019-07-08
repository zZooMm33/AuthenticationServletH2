package storage;

public class UserInStorage
{
    private String id;
    private String name;
    private String mail;
    private String info;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public UserInStorage(String id, String name, String mail, String info)
    {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.info = info;
    }
}
