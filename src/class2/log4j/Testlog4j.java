package class2.log4j;

import org.apache.log4j.Logger;

public class Testlog4j {
    public static void main(String[] args){
        Logger logger = Logger.getLogger(Testlog4j.class);
        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        try{
            int a = 10/0;
        }catch(Exception e){
            logger.error("error",e);
        }
    }
}
