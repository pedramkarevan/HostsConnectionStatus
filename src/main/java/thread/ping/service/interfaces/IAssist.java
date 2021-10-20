package thread.ping.service.interfaces;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;

public interface IAssist {

  /**
   *setUp All Threades
   */
    public void setUpAllThreads();
  /**
   *properties setup
   */
    public Properties appConfig( Properties properties);





}
