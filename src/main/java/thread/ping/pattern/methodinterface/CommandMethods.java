package thread.ping.pattern.methodinterface;

/**
 * running commands in threads
 */
public interface CommandMethods extends Runnable {

       public void runCommand() throws Exception;
}
