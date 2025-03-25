import java.io.Serializable;


public class Customer implements Serializable
{
    private int id;
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;

    public Customer()
    {

    }

    public Customer(int id, String fname, String lname, String address, String phone, String email, String username, String password)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    private String getUsername(String username)
    {
        return username;
    }

    private String getPassword(String password)
    {
        return password;
    }

    public void SetUsername(String username)
    {
        this.username = username;
    }

    public void SetPassword(String password)
    {
        this.password = password;
    }



}
