package com.somedomain.counter.rest;

import com.somedomain.counter.exception.CounterException;
import com.somedomain.counter.rest.api.CounterDTO;
import com.somedomain.counter.rest.api.CounterOperationResult;
import com.somedomain.counter.core.service.CounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value = "/counter/api/v1")
public class CounterController {

    // This service would most likely be injected, or otherwise obtained, in a real situation, i.e., not created here
    // @Autowired
    private CounterService counterService = new CounterService();


    @PutMapping(value = "/create")
    public @ResponseBody ResponseEntity<CounterOperationResult> createCounter(@RequestBody CounterDTO input) {
        try {
            CounterDTO outcome = counterService.createCounter(input.getName());
            return new ResponseEntity<>(new CounterOperationResult(outcome),
                                        CREATED);
        }
        catch (Exception e) {
            return getErrorResponse(e);
        }
    }

    // I chose to call this 'bumpvalue' instead of 'increment', in the hope that it should be clearer that the
    // value can only be increased by one, not with an arbitrary value.
    @PutMapping(value = "/bumpvalue")
    public @ResponseBody ResponseEntity<CounterOperationResult> bumpCounterValue(@RequestBody CounterDTO input) {
        try {
            CounterDTO outcome = counterService.bumpValue(input.getName());
            return new ResponseEntity<>(new CounterOperationResult(outcome),
                                        OK);
        }
        catch (Exception e) {
            return getErrorResponse(e);
        }
    }

    @GetMapping(value = "/getvalue")
    public @ResponseBody ResponseEntity<CounterOperationResult> getCounterValue(@RequestBody CounterDTO input) {
        try {
            CounterDTO outcome = counterService.getValue(input.getName());
            return new ResponseEntity<>(new CounterOperationResult(outcome),
                                        OK);
        }
        catch (Exception e) {
            return getErrorResponse(e);
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody ResponseEntity<CounterOperationResult> listCounters() {
        List<CounterDTO> outcome = counterService.findAll();
        return new ResponseEntity<>(new CounterOperationResult(outcome),
                                    OK);
    }

    private ResponseEntity<CounterOperationResult> getErrorResponse(Exception e) {
        if (e instanceof CounterException) {
            return new ResponseEntity<>(new CounterOperationResult(e.getMessage()),
                                        BAD_REQUEST);
        }
        return new ResponseEntity<>(new CounterOperationResult("Something went wrong :-("),
                                    INTERNAL_SERVER_ERROR);
    }

}
