package com.somedomain.counter.rest.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CounterOperationResult {

    private List<CounterDTO> counters = new LinkedList<>();
    private boolean success = true;
    private String errorMessage;



    public CounterOperationResult() {
    }

    public CounterOperationResult(String errorMessage) {
        success = false;
        this.errorMessage = errorMessage;
    }

    public CounterOperationResult(CounterDTO counter) {
        this.counters.add(counter);
    }

    public CounterOperationResult(List<CounterDTO> counters) {
        this.counters.addAll(counters);
    }

    public List<CounterDTO> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterDTO> counters) {
        this.counters = counters;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
