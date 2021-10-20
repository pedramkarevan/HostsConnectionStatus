package thread.ping.execution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import thread.ping.pattern.BaseMethod;
import thread.ping.pattern.concreatemethod.PingTCPImpl;


import static org.mockito.Mockito.*;

public class PingTCPTest {

    private PingTCPImpl myObj;
    BaseMethod mock;

    @Before
    public void setUp() throws Exception {
        mock = mock(BaseMethod.class);
        myObj= spy(new PingTCPImpl("oranum.com"));
    }

    @Test
    public void callReportIfStatusCodeNot200(){
        String response = "302:781";
        doNothing().when((BaseMethod )myObj).
                responseReportHandler(response);

    }


    @Test
    public void pingTCPTest() throws Exception {
        PingTCPImpl spy = Mockito.spy(new PingTCPImpl("oranum.com"));
        spy.runCommand();
    }

    @Test
    public void reportIfPingTCPImplException() throws Exception {

        PingTCPImpl spy = Mockito.spy(new PingTCPImpl("google.com"));
        spy.runCommand();
        doThrow(new RuntimeException()).when(spy).runCommand();
        verify(spy).report("google.com");
    }


}
