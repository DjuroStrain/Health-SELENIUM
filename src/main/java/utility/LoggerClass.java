package utility;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerClass {

    final static Logger log = LoggerFactory.getLogger(LoggerClass.class);

    public static void startTestLog(String sTestClassName) {
        log.info("================================================================================");
        log.info("---------------------------------- TEST START ----------------------------------");
        log.info("--------------------------------------------------------------------------------");
        log.info("--------------------------- Test: "+sTestClassName+" ---------------------------");
        log.info("================================================================================");
    }

    public static void endTestLog(String sTestClassName) {
        log.info("================================================================================");
        log.info("---------------------------------- TEST END ----------------------------------");
        log.info("--------------------------------------------------------------------------------");
        log.info("--------------------------- Test: "+sTestClassName+" ---------------------------");
        log.info("================================================================================");
    }
}
