package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Whisky> findAllWhiskiesForThisYear(int year) {
        //want to find all the distillaries for a region(give it a region)
        List<Whisky> results = null;

        Session session = entityManager.unwrap(Session.class);
        try {

            Criteria cr = session.createCriteria(Whisky.class);
            //
            cr.add(Restrictions.eq("year", year));

            results = cr.list();

        } catch (HibernateException ex) {
            // if that goes wrong do this stuff
            ex.printStackTrace();
        } finally {
            // no matter what happens always do this after
            session.close();

        }
        return results;
    }

    @Transactional
    public List<Whisky> findAllWhiskiesFromThisRegion(String region) {
        List<Whisky> results = null;

        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distillery");
            cr.add(Restrictions.eq("distillery.region", region));

            results = cr.list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
}



