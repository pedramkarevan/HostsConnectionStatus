package thread.ping.execution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.concreatemethod.PingICMPImpl;
import thread.ping.pattern.concreatemethod.PingTCPImpl;
import thread.ping.pattern.concreatemethod.RouterImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class BaseMethodTest {

    private static Logger logger = Logger.getLogger(BaseMethodTest.class.getName());

    BaseMethod mock;

    @Before
    public void setUp() {
        mock = mock(BaseMethod.class);

    }





}
