package com.somedomain.counter.core.persistence;

import com.somedomain.counter.core.entity.Counter;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

/*
 This interface provides the operations needed to handle a counter entry in the database.
 We are using default methods, to facilitate easy creation of dummy classes.
 */
public interface CounterManager {

    default boolean create(Session session, String counterName) {
        return true;
    }
    /*
    INSERT INTO Counter (name) VALUES ('<counterName>');
    or with Hibernate
    Counter c = new Counter(counterName);
    session.save(c);
    */

    default boolean bumpValue(Session session, String counterName) {
        return true;
    }
    /*
    UPDATE Counter SET value = 1 + value WHERE name = '<counterName>'
    or with Hibernate
    Counter c = find(counterName);
    c.bumpValue();
    session.update(c);
    */

    default int getValue(Session session, String counterName) {
        return 0;
    }
    /*
    SELECT value FROM Counter WHERE name =  '<counterName>'
    or with Hibernate
    Counter c = find(counterName);
    return (c != null) ? c.getValue() : -1;   // To signal 'no such counter'
    */

    default Counter find(Session session, String counterName) {
        return new Counter("dummyCounter", 0);
    }
    /*
    With Hibernate
    String hql = "select c from Counter c where c.name = " + counterName;
    Query query = session.createQuery(hql);
    return query.getSingleResult();
    */

    default List<Counter> findAll(Session session) {
        return Arrays.asList(new Counter("dummyCounter1", 0),
                             new Counter("dummyCounter2", 0));
    }
    /*
    With Hibernate
    String hql = "select c from Counter c";
    Query query = session.createQuery(hql);
    return query.list();
    Not using Hibernate would of course involve a 'SELECT * FROM Counter'
    and then manually creating Counter instances and adding them to the returned list.
    */

}
