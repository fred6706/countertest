package com.somedomain.counter.util;

import com.somedomain.counter.core.entity.Counter;
import com.somedomain.counter.rest.api.CounterDTO;


public class Counter2CounterDTO {

    public static CounterDTO translate(Counter counter) {
        return (counter != null) ?
               new CounterDTO(counter.getName(), counter.getValue()) :
               null;
    }

}
