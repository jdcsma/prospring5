package jun.prospring5.ch12.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@Controller
public class PingController {

    private static final Logger logger =
            LoggerFactory.getLogger(SingerController.class);

    @PostConstruct
    public void init() {
        logger.info("PingController - init:");
    }

    @RequestMapping(value = "/ping/{message}")
    @ResponseBody
    public String pong(@PathVariable String message) {
        return message;
    }
}
