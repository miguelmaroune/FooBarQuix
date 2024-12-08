package com.foo.bar.batch.data_batch_processor.application.service;

import org.springframework.stereotype.Service;

@Service
public class FooBarQuixService {
    public String numberToFooBarQuixTransformation(int number) {
        StringBuilder result = new StringBuilder();

        if (number % 3 == 0) result.append("FOO");
        if (number % 5 == 0) result.append("BAR");

        String numberStr = String.valueOf(number);
        for (char c : numberStr.toCharArray()) {
            if (c == '3') result.append("FOO");
            if (c == '5') result.append("BAR");
            if (c == '7') result.append("QUIX");
        }
        return !result.isEmpty() ? result.toString() : numberStr;
    }
}
