package org.example.application.util;

import org.springframework.stereotype.Service;

@Service
public class CalculServiceImpl implements ICalculService {
    @Override
    public Integer computeAverage(Integer total, Integer count) {
        Integer average = 0;
        if (count>0){
            average = total/count;
        }
        return average;
    }
}
