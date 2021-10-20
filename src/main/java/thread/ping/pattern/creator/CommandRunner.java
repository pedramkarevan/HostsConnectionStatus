package thread.ping.pattern.creator;


import thread.ping.pattern.methodinterface.CommandMethods;

/**
 * Creating different implementation of commands
 */
public abstract class CommandRunner {



    public abstract CommandMethods getMethod();


}
