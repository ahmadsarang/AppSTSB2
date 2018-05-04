package my.com.softlabs.appstsb;

/**
 * Created by Muhammad Akmal on 7/4/2017.
 */

public class Approve {
    private String name,status;

    public Approve(String name,String status)
    {
        this.setName(name);
        this.setStatus(status);

    }

    public String getName(int position) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
