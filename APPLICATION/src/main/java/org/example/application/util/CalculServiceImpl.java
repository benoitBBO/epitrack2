package org.example.application.util;

import org.springframework.stereotype.Service;

@Service
public class CalculServiceImpl implements ICalculService {
    @Override
    public Integer computeAverage(Integer total, Integer count) {
        Integer average = total/count;
        return average;
    }
}
