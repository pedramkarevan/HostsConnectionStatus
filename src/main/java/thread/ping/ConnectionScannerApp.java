package thread.ping;
import thread.ping.service.implementations.AssistImpl;
import thread.ping.service.interfaces.IAssist;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionScannerApp {

   public static Logger logger = Logger.getLogger(ConnectionScannerApp.class.getName());


    public static void main(String[] args) {

    try {
            IAssist pingRunner = new AssistImpl();
             pingRunner.setUpAllThreads();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        }

    }


