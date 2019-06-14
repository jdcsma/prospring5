package jun.prospring5.ch11.service;

import java.util.concurrent.Future;

public interface AsyncService {

    void asyncTask();

    Future<String> asyncWithReturn(String name);
}
