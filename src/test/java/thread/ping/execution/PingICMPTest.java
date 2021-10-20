package thread.ping.execution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.concreatemethod.PingICMPImpl;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

public class PingICMPTest {
    private static Logger logger = Logger.getLogger(BaseMethodTest.class.getName());



    @Test
    public void pingICMPTest() throws Exception {
        PingICMPImpl spy = Mockito.spy(new PingICMPImpl("google.com"));
        spy.runCommand();

    }

    @Test
    public void reportIfPingICMPImplException() throws Exception {

        PingICMPImpl spy = Mockito.spy(new PingICMPImpl("google.com"));
        spy.runCommand();
        doThrow(new RuntimeException()).when(spy).runCommand();
        verify(spy).report("google.com");
    }
}
