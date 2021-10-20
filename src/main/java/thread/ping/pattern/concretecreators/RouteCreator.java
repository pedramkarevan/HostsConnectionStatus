package thread.ping.pattern.concretecreators;

import thread.ping.pattern.concreatemethod.RouterImpl;
import thread.ping.pattern.creator.CommandRunner;
import thread.ping.pattern.methodinterface.CommandMethods;

import java.util.logging.Logger;

public class RouteCreator  extends CommandRunner {

    public static Logger logger= Logger.getLogger(RouteCreator.class.getName());
    public RouteCreator(String host) {
        this.host = host;
    }

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public CommandMethods getMethod() {
        return new RouterImpl(this.host);
    }
}
