package thread.ping.pattern.concretecreators;



import thread.ping.pattern.concreatemethod.PingICMPImpl;
import thread.ping.pattern.creator.CommandRunner;
import thread.ping.pattern.methodinterface.CommandMethods;

import java.util.logging.Logger;


public class PingICMPCreator extends CommandRunner {

    public static Logger logger= Logger.getLogger(PingICMPCreator.class.getName()) ;
    private String host;

    public PingICMPCreator(String host) {
        this.host=host;
    }



    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }


    @Override
    public CommandMethods getMethod() {

        return new PingICMPImpl(host);
    }
}
