package thread.ping.pattern.methodinterface;

/**
 * running commands in threads
 * @author Pedram Karevan
 */
public interface CommandMethods extends Runnable {

       public void runCommand() throws Exception;
}
