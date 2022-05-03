package com.somedomain.counter.core.service;

import com.somedomain.counter.core.entity.Counter;
import com.somedomain.counter.exception.CounterException;
import com.somedomain.counter.core.persistence.CounterManager;
import com.somedomain.counter.core.persistence.Dao;
import com.somedomain.counter.rest.api.CounterDTO;
import com.somedomain.counter.util.Counter2CounterDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
public class CounterService {

    private static final String MISSING_NAME = "No name specified for the counter";
    private static final String NO_SUCH_COUNTER = "No such counter exists: ";

    // In real life we would, e.g., through injection, gain access to some kind of session factory here,
    // from which we could obtain the current session, but for simplicity we are now just marking it's
    // existence with a dummy class.
    private Dao dao = new Dao();

    // In real life a real implementation would be injected or otherwise obtained here.
    private CounterManager counterManager = new CounterManager() {};


    // I have chosen to throw an exception here when an error occurs, another alternative
    // could be to return a record containing result, status, and error message.


    @Transactional
    public CounterDTO createCounter(String name) throws CounterException {
        validateInput(name);
        if (counterManager.create(dao.getCurrentSession(), name)) {
            return new CounterDTO(name);
        }
        throw new CounterException("Couldn't create the requested counter due to <some reason>");
    }

    @Transactional
    public CounterDTO bumpValue(String name) throws CounterException {
        validateInput(name);
        int value = counterManager.getValue(dao.getCurrentSession(), name);
        if (value >= 0) {
            return new CounterDTO(name, value);
        }
        throw new CounterException(NO_SUCH_COUNTER + name);
    }

    @Transactional(readOnly = true)
    public CounterDTO getValue(String name) throws CounterException {
        validateInput(name);
        int value = counterManager.getValue(dao.getCurrentSession(), name);
        if (value >= 0) {
            return new CounterDTO(name, value);
        }
        throw new CounterException(NO_SUCH_COUNTER + name);
    }

    @Transactional(readOnly = true)
    public CounterDTO find(String name) throws CounterException {
        validateInput(name);
        Counter counter = counterManager.find(dao.getCurrentSession(), name);
        if (counter != null) {
            return Counter2CounterDTO.translate(counter);
        }
        throw new CounterException(NO_SUCH_COUNTER + name);
    }

    @Transactional(readOnly = true)
    public List<CounterDTO> findAll() {
        List<Counter> counters = counterManager.findAll(dao.getCurrentSession());
        List<CounterDTO> result = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(counters)) {
            for (Counter c : counters) {
                result.add(Counter2CounterDTO.translate(c));
            }
        }
        return result;
    }

    private void validateInput(String name) throws CounterException {
        if (StringUtils.isBlank(name)) {
            throw new CounterException(MISSING_NAME);
        }
    }

}
