package jun.prospring5.ch11.service.implementation;


import jun.prospring5.ch11.service.AsyncService;
import jun.prospring5.ch11.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

    private static Logger logger =
            LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public void asyncTask() {
        logger.info("Start execution of async task ...");

        ThreadUtils.sleep(3_000);

        logger.info("Complete execution of async task ...");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String arg) {

        logger.info("Start execution of async task with arg:" + arg);

        ThreadUtils.sleep(3_000);

        logger.info("Complete execution of async task with name:" + arg);

        return new AsyncResult<>(arg);
    }
}
