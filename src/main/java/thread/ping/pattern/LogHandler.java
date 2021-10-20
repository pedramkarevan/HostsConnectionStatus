package thread.ping.pattern;

import java.io.IOException;
import java.util.logging.FileHandler;
/**
 * Singleton pattern for initialization of FileHandler with  a file property
 * during the process we have an access to a file for writing reports
 * @author Pedram Karevan
 *
 * */
public class LogHandler extends BaseMethod{
    private static LogHandler instance;
    public FileHandler fileHandler;

    private LogHandler(){
        try {

            fileHandler =  new FileHandler(super.appConfig(super.getProperties()).getProperty("log.file"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LogHandler getFileLogger(){
        if (instance == null)
            instance = new LogHandler();
        return instance;
    }


}

