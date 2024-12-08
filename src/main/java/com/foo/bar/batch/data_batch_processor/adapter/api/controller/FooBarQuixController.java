package com.foo.bar.batch.data_batch_processor.adapter.api.controller;

import com.foo.bar.batch.data_batch_processor.adapter.api.validators.ValidNumber;
import com.foo.bar.batch.data_batch_processor.application.service.FooBarQuixService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class FooBarQuixController {

    private final FooBarQuixService fooBarQuixService;

    public FooBarQuixController(FooBarQuixService fooBarQuixService) {
        this.fooBarQuixService = fooBarQuixService;
    }

    @GetMapping("/transform/{number}")
    public ResponseEntity<String> FooBarQuixTransformation(@PathVariable @ValidNumber int number) {
        return ResponseEntity.ok(fooBarQuixService.numberToFooBarQuixTransformation(number));
    }
}
