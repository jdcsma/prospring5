package jun.prospring5.ch8.service;

import jun.prospring5.ch8.view.SingerSummary;

import java.util.List;

public interface SingerSummaryService {

    void displayAllSingerSummary();

    List<SingerSummary> findAllSingerSummery();
}
