package msd.com.contacts;

/**
 * Created by Johns on 10/12/2015.
 */
public class Data {

    private String name;
    private String mobile;
    private String email;

    public Data(String name, String mobile, String email)
    {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
