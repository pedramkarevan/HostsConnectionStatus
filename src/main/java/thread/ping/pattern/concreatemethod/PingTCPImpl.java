package thread.ping.pattern.concreatemethod;


import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.methodinterface.CommandMethods;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PingTCPImpl extends BaseMethod implements CommandMethods {

/*    BaseMethod baseMethod;

    public BaseMethod getBaseMethod() {
        return baseMethod;
    }

    public void setBaseMethod(BaseMethod baseMethod) {
        this.baseMethod = baseMethod;
    }*/

    private Properties properties = new Properties();
    String host;

    public PingTCPImpl(String host) {
        this.host=host;

    }



    public static Logger logger =Logger.getLogger(PingTCPImpl.class.getName());

    @Override
    public void runCommand() throws Exception {
        try {
            super.runner(this.instructionBuilder());

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            setHost(host);
            setAlias("tcp_ping");
            jsonMapper(this);
            report(host);
        }

    }

    @Override
    public void run() {
        try {
            runCommand();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

/**
 * overriding parent class for sending different instruction
 */
    @Override
    public BufferedReader instructionBuilder() throws Exception {
        URL url = new URL("http://" + getHost());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        appConfig(properties);
        connection.setConnectTimeout(Integer.parseInt(
                properties.getProperty("ping.http.max.response.time")));
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
        long startTime = System.currentTimeMillis();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        String connectionSummary = connection.getResponseCode() + ":" + timeElapsed;
        BufferedReader bufferedReader = new BufferedReader(new StringReader(connectionSummary));
        setHost(host);
        setAlias("tcp_ping");
        jsonMapper(this);
        return bufferedReader;
    }

    /**
     * overriding parent class for mapping different result
     */
    @Override
    public String responseMapper(BufferedReader bufferedReader) throws IOException {

        String response = bufferedReader.readLine();
        logger.warning(response);
        return response;
    }

    /**
     * overriding parent class for response evaluation of TCP command
     */
    @Override
    public void responseReportHandler(String response) {
        String[] resp = response.split(":");
        if (!resp[0].equals("200")) {
            try {
               report(host);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        Long maxResponseTime = Long.parseLong(
                properties.getProperty("ping.http.max.response.time"));
        Long responseTime = Long.parseLong(resp[1]);
        if (responseTime > maxResponseTime) {
            try {
                sendReport(host);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //getters & setters
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
