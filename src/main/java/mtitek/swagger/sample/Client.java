package mtitek.swagger.sample;

import okhttp3.HttpUrl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationPath("/")
public class Client extends Application {
    private final String CONST = "users";
    public void getEndpointTest1() throws MalformedURLException {
        HttpUrl aseUrl1 = HttpUrl.get("http://api.example.com");

        System.out.println("sdf");

        new URL("");

    }


}
