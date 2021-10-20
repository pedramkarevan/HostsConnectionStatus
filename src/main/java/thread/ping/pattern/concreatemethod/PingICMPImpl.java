package thread.ping.pattern.concreatemethod;


import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.methodinterface.CommandMethods;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of ICMP ping command
 */
public class PingICMPImpl extends BaseMethod implements CommandMethods {

    public static Logger logger = Logger.getLogger(PingICMPImpl.class.getName());
    String host;


    public PingICMPImpl(String host) {
        setProcessBuilder(new ProcessBuilder("ping", "-n", "1", host));
        this.host=host;
    }


    @Override
    public void runCommand() {
        try {
            setReportParams();
            super.runner(instructionBuilder());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            setReportParams();

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    void setReportParams(){
        super.setHost(host);
        setAlias("ICMP_ping");
        jsonMapper(this);
    }


}

