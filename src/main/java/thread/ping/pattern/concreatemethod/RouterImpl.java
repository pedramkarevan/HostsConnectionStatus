package thread.ping.pattern.concreatemethod;

import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.LogHandler;
import thread.ping.pattern.methodinterface.CommandMethods;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of tracert command
 */
public class RouterImpl extends BaseMethod implements CommandMethods {
    public Logger logger =Logger.getLogger(RouterImpl.class.getName());
    String host;


    public RouterImpl(String host) {
         setProcessBuilder(new ProcessBuilder("tracert", host));
        this.host=host;
    }



    @Override
    public void runCommand() throws Exception {
        try {
            setReportParams();
            super.runner(instructionBuilder());

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            setReportParams();
            LogHandler logHandler=LogHandler.getFileLogger();
            logger.addHandler(logHandler.fileHandler);
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

    void setReportParams(){
        super.setHost(host);
        setAlias("tracert");
        jsonMapper(this);
    }
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
