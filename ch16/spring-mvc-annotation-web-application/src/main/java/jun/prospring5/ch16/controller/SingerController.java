package jun.prospring5.ch16.controller;

import jun.prospring5.ch16.entity.Singer;
import jun.prospring5.ch16.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/singers")
public class SingerController {

    private static final Logger logger =
            LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        model.addAttribute("singers", singers);
        logger.info("No. of singers:" + singers.size());

        return "singers/list";
    }
}
