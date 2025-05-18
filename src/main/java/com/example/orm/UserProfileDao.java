package com.example.orm;

import com.example.accounts.UserProfileDataSet;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserProfileDao {

    public void save(UserProfileDataSet userProfileDataSet)
    throws PersistentObjectException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();
        session.persist(userProfileDataSet);
        tx.commit();

        session.close();
    }

    public UserProfileDataSet getUserProfileByUsernameAndPassword(String username, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfileDataSet> criteriaQuery = criteriaBuilder.createQuery(UserProfileDataSet.class);
        Root<UserProfileDataSet> root = criteriaQuery.from(UserProfileDataSet.class);

        Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), username);
        Predicate passwordPredicate = criteriaBuilder.equal(root.get("password"), password);

        criteriaQuery.where(criteriaBuilder.and(usernamePredicate, passwordPredicate));

        try {
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (NoResultException ignored) {
            return null;
        } finally {
            session.close();
        }
    }
}
