package jun.prospring5.ch7.dao.implementation;

import jun.prospring5.ch7.dao.SingerDao;
import jun.prospring5.ch7.entity.Singer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("singerDao")
public class SingerDaoImpl
        extends TransactionDaoSupports
        implements SingerDao {

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Singer> findAll() {
        return getSession().createQuery("FROM Singer s").list();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Singer> findAllWithAlbum() {
        return getSession()
                .getNamedQuery("Singer.findAllWithAlbum")
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public Singer findById(Long id) {
        return (Singer) getSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Singer save(Singer singer) {
        return (Singer) super.saveToDB(singer);
    }

    @Override
    public void delete(Singer singer) {
        super.deleteFromDB(singer);
    }
}
