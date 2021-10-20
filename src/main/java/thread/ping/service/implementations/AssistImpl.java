package thread.ping.service.implementations;


import thread.ping.ConnectionScannerApp;
import thread.ping.pattern.concretecreators.PingICMPCreator;
import thread.ping.pattern.concretecreators.PingTCPCreator;
import thread.ping.pattern.concretecreators.RouteCreator;
import thread.ping.service.interfaces.IAssist;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class AssistImpl implements IAssist {

    public static Logger logger = Logger.getLogger(ConnectionScannerApp.class.getName());
    private Properties properties = new Properties();


    /**
     * Configuration of threads based on different command method
     */

    @Override
    public void setUpAllThreads() {
        Properties properties = new Properties();
        this.appConfig(properties);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        int delay = 0;
        String[] hosts = properties.getProperty("hosts").split(",");
        for (String host : hosts) {
            scheduledExecutorService.scheduleWithFixedDelay(new PingICMPCreator(host).getMethod(), delay,
                    Long.parseLong(properties
                            .getProperty("ping.icmp.delay")),
                    TimeUnit.MILLISECONDS);
            scheduledExecutorService.scheduleWithFixedDelay(new PingTCPCreator(host).getMethod(), delay,
                    Long.parseLong(properties
                            .getProperty("ping.http.delay")),
                    TimeUnit.MILLISECONDS);
            scheduledExecutorService.scheduleWithFixedDelay(new RouteCreator(host).getMethod(), delay,
                    Long.parseLong(properties
                            .getProperty("trace.delay")),
                    TimeUnit.MILLISECONDS);

        }

    }

    /**
     * Configuration set up based on application property file
     *
     * @param properties
     * @return
     */
    @Override
    public Properties appConfig(Properties properties) {
        InputStream inputStream = ConnectionScannerApp.class.getClassLoader().getResourceAsStream("application.properties");
        if (inputStream != null) {
            try {
                properties.load(inputStream);
                return properties;
            } catch (IOException e) {
                logger.info(e.getMessage());
            }

        }
        return properties;
    }


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}




