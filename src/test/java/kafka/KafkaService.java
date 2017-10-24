package kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaService {
    static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public void processMessage(Map<String, Map<Integer, String>> msgs) {
        for (Map.Entry < String,Map<Integer, String>>entry:
        msgs.entrySet()){
            logger.info("Suchit Topic:" + entry.getKey());
            for (String msg : entry.getValue().values()) {
                logger.info("Suchit Consumed Message: " + msg);
            }
        }
    }

}
