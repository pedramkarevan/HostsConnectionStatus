package thread.ping.setup;

import org.junit.Test;
import thread.ping.service.implementations.AssistImpl;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ConfigurationTest {



    @Test
    public void appConfigTest() {
        //ToDO appConfig should get parameter of property file
        Map<String, Object> params = new HashMap<>();
        params.put("ping.icmp.delay" ,7000);
        params.put("ping.http.max.response.time" ,7000);
        params.put("report.url" ,"https://api./test/api/transaction");
        params.put("log.file" ,"log.txt");
        params.put("hosts", "google.com");
        params.put("log.level" ,"warning");
        params.put("ping.http.delay" ,7000);
        params.put("trace.delay" ,7000);
        params.put("ping.http.timeout" ,7000);

        AssistImpl assist = new AssistImpl();
        Map step1 = assist.appConfig(new Properties());

       assertFalse(params.size() == step1.size() && params.containsValue(step1) );

    }
}
