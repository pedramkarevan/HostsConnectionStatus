package thread.ping.pattern;


import com.google.gson.Gson;
import okhttp3.*;
import thread.ping.service.implementations.AssistImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseMethod extends AssistImpl {

    private static Logger logger = Logger.getLogger(BaseMethod.class.getName());

    private ProcessBuilder processBuilder;
    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private String host;
    private String lastResult;
    private String alias;



    /**
     * For Ping Execution
     */

    public BufferedReader instructionBuilder() throws Exception {
        processBuilder.redirectErrorStream(true);
        Process p = processBuilder.start();
        bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return bufferedReader;
    }

    /**
     * getting the result of commands
     * @param bufferedReader
     * @throws IOException
     */
    public void runner(BufferedReader bufferedReader) throws IOException {

        String formattedResponse = responseMapper(bufferedReader);
        setLastResult(formattedResponse);
        responseReportHandler(formattedResponse);
        logger.info("last result: " + formattedResponse);
    }

    /**
     *
     * Mapping output ping to string
     */

    public String responseMapper(BufferedReader bufferedReader) throws IOException {
        stringBuilder = new StringBuilder();
        String line = null;
        final String LINE_SEP = System.getProperty("line.separator");
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(LINE_SEP);
        }
        return stringBuilder.toString();
    }

    /**
     * ping result error analyser
     */

    public void responseReportHandler(String response) {
       if( getAlias().contentEquals("tracert") ){
           LogHandler logHandler=LogHandler.getFileLogger();
           logger.addHandler(logHandler.fileHandler);
       }else{

           String packetLossRegex = "(\\d+)\\%\\spacket\\sloss";
           Pattern pattern = Pattern.compile(packetLossRegex, Pattern.MULTILINE);
           Matcher matcher = pattern.matcher(response);
           if (matcher.find()) {
               logger.info(matcher.group(0));
               if (!matcher.group(1).equals("0")) {
                   report(host) ;
               }
           } else {
               report(host);
           }
       }


    }

    /**
     * group mapping by host.
     */
    private Map<String, List<BaseMethod>> baseMethodMappers = new HashMap<String, List<BaseMethod>>();

    /**
     * creating json format
     * @param baseMethod
     */
    public  synchronized void jsonMapper(BaseMethod baseMethod) {
        List<BaseMethod> basedMethods = baseMethodMappers.get(baseMethod.getHost());
        if (basedMethods == null) {
            basedMethods = new ArrayList<BaseMethod>();
            baseMethodMappers.put(baseMethod.getHost(), basedMethods);
        }
        basedMethods.add(baseMethod);
    }

    public void report(String host) {

        Map<String, String> reportMap = new HashMap<String, String>();
        reportMap.put("host", host);
        baseMethodMappers.get(host).forEach((mapper) -> {
            reportMap.put(mapper.getAlias(), mapper.reportFormatBuilder());
        });
        String jsonReport = new Gson().toJson(reportMap);
        logger.warning(jsonReport);
        try {

            sendReport(jsonReport);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Performing post method
     */
    public void sendReport(String jsonBody) throws Exception {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        LogHandler logHandler=LogHandler.getFileLogger();
        logger.addHandler(logHandler.fileHandler);
        String reportURL = super.appConfig(super.getProperties()).getProperty("report.url");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder().url(reportURL).post(body).build();
        Response response = client.newCall(request).execute();
    }

    public String reportFormatBuilder() {
        String lastResult = getLastResult();
        String[] splittedResult = lastResult.split(":");
        String formattedResult = "Response code: " + splittedResult[0] + " - " + "Connection time: "
                + splittedResult[1];
        return formattedResult;
    }


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BaseMethod.logger = logger;
    }

    public ProcessBuilder getProcessBuilder() {
        return processBuilder;
    }

    public void setProcessBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
