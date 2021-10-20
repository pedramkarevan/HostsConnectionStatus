package thread.ping.execution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.concreatemethod.PingTCPImpl;
import thread.ping.pattern.concreatemethod.RouterImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class PingTCPTest {

    BaseMethod mock;

    @Before
    public void setUp() {
        mock = mock(BaseMethod.class);

    }

    @Test
    public void pingTCPTest() throws Exception {
        PingTCPImpl spy = Mockito.spy(new PingTCPImpl("google.com"));
        spy.runCommand();
    }

    @Test
    public void reportIfPingTCPImplException() throws Exception {

        PingTCPImpl spy = Mockito.spy(new PingTCPImpl("google.com"));
        spy.runCommand();
        doThrow(new RuntimeException()).when(spy).runCommand();
        verify(spy).report("google.com");
    }

    @Test
    public void callReportIfStatusCodeNot200() throws Exception {

        PingTCPImpl spy = Mockito.spy(new PingTCPImpl("google.com"));
        String response = "302:781";
        doNothing().when(spy).report("google.com");
        spy.report("hh");
        verify(spy).responseReportHandler(response);



    }
}
