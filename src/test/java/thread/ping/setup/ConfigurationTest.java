package thread.ping.setup;

import org.junit.Test;
import thread.ping.service.implementations.AssistImpl;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import static org.junit.Assert.assertEquals;

public class ConfigurationTest {



    @Test
    public void appConfigTest() {
        //ToDO appConfig should get parameter of property file
        Map<String, String> params = new HashMap<>();
        params.put("hosts", "google.com");
        params.put("ping.http.max.response.time" ,"7000");
        Properties prop= new Properties();
        AssistImpl assist = new AssistImpl();

        assertEquals(params, assist.appConfig(new Properties()));
    }
}
