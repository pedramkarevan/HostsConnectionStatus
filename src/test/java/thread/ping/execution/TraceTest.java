package thread.ping.execution;

import org.junit.Test;
import org.mockito.Mockito;
import thread.ping.pattern.concreatemethod.RouterImpl;


public class TraceTest {

    @Test
    public void traceRoutTestWithLastResult() throws Exception {
        RouterImpl spy = Mockito.spy(new RouterImpl("google.com"));
        spy.runCommand();
    }




}
