package thread.ping.pattern.concretecreators;

import thread.ping.pattern.concreatemethod.PingTCPImpl;
import thread.ping.pattern.creator.CommandRunner;
import thread.ping.pattern.methodinterface.CommandMethods;
import java.util.logging.Logger;

public class PingTCPCreator extends CommandRunner {

    public static  Logger logger = Logger.getLogger(PingTCPCreator.class.getName());
    private String host;

    public PingTCPCreator(String host) {
        this.host=host;


    }


    @Override
    public CommandMethods getMethod() {
        return new PingTCPImpl( host);
    }


  //getters & setters
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
