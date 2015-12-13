package msd.com.contacts;

/**
 * Created by Johns on 10/12/2015.
 */
public class Data {

    private String name;
    private String mobile;
    private String email;
    private String latitude;
    private String longitude;
    private String addressName;
    private String address;

    public Data(String name, String mobile, String email, String latitude, String longitude, String addressName, String address)
    {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressName = addressName;
        this.address = address;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
