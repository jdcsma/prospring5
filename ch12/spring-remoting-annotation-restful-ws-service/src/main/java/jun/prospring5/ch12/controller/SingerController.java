package jun.prospring5.ch12.controller;

import jun.prospring5.ch12.common.entity.Singer;
import jun.prospring5.ch12.common.service.SingerService;
import jun.prospring5.ch12.common.domain.Singers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(value = "/singer")
public class SingerController {

    private static final Logger logger =
            LoggerFactory.getLogger(SingerController.class);

    @PostConstruct
    public void init() {
        logger.info("SingerController - init:");
    }

    @Autowired
    private SingerService singerService;

    @RequestMapping(value = "/listdata",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Singers listData() {
        return new Singers(singerService.findAll());
    }

    @RequestMapping(value = "/id/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Singer findSingerById(@PathVariable Long id) {
        return singerService.findById(id);
    }

    @RequestMapping(value = "/firstName/{firstName}",
            method = RequestMethod.GET)
    @ResponseBody
    public Singers findSingerByName(@PathVariable String firstName) {
        return new Singers(singerService.findByFirstName(firstName));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Singer createSinger(@RequestBody Singer singer) {
        logger.info("Creating singer:" + singer);
        singerService.save(singer);
        logger.info("Singer created successfully with info:" + singer);
        return singer;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateSinger(
            @RequestBody Singer singer,
            @PathVariable Long id) {
        logger.info("Updating singer:" + singer);
        singerService.save(singer);
        logger.info("Singer updated successfully with info:" + singer);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteSinger(@PathVariable Long id) {
        logger.info("Deleting singer with id:" + id);

        Singer singer = singerService.findById(id);

        if (singer == null) {
            logger.error("Singer not found with id:" + id);
            return;
        }

        singerService.save(singer);
        logger.info("Singer deleted successfully with id:" + id);
    }
}
