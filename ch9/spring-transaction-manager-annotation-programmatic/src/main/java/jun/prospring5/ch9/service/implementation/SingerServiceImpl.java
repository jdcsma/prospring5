package jun.prospring5.ch9.service.implementation;

import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.repository.SingerRepository;
import jun.prospring5.ch9.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("singerService")
public class SingerServiceImpl
        implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private TransactionTemplate txTemplateDefault;

    @Autowired
    private TransactionTemplate txTemplateReadOnly;

    @Autowired
    private TransactionTemplate txTemplatePropagationNever;

    @Override
    public List<Singer> findAll() {
        return txTemplateReadOnly.execute(status -> {
            List<Singer> singers = new ArrayList<>();
            singerRepository.findAll().forEach(singers::add);
            return singers;
        });
    }

    @Override
    public Singer findById(Long id) {
        return txTemplateReadOnly.execute(
                status -> singerRepository
                        .findById(id).orElse(null));
    }

    @Override
    public long countAllSingers() {
        return txTemplatePropagationNever.execute(
                status -> singerRepository.countAllSingers());
    }

    @Override
    public Singer save(Singer singer) {
        return txTemplateDefault.execute(
                status -> singerRepository.save(singer));
    }

    @Override
    public void delete(Singer singer) {
        txTemplateDefault.execute(
                new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        singerRepository.delete(singer);
                    }
                });
    }
}
