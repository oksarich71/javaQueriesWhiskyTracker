package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Distillery> findAllDistillariesFromRegion(String region) {
        //want to find all the distillaries for a region(give it a region)
        List<Distillery> results = null;

        Session session = entityManager.unwrap(Session.class);
        try {

            Criteria cr = session.createCriteria(Distillery.class);
            //
            cr.add(Restrictions.eq("region", region));

            results = cr.list();

        } catch (HibernateException ex) {
            // if that goes wrong do this stuff
            ex.printStackTrace();
        }
        finally {
        // no matter what happens always do this after
        session.close();

        }
        return results;
}
    }