package com.navigatingcancer.codingexercise.controller;

import com.navigatingcancer.codingexercise.model.BasicMathResult;
import com.navigatingcancer.codingexercise.model.MathOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathsController {

    @GetMapping("/math/add")
    public BasicMathResult addTwoIntegers(@RequestParam int parameter1,
                                          @RequestParam int parameter2){
        return new BasicMathResult(parameter1, parameter2, MathOperation.ADDITION, Math.addExact(parameter1,parameter2));
    }

    /**
     * Subtracts parameter 2 from parameter 1
     * @param parameter1 int
     * @param parameter2 int
     * @return BasicMathResult
     */
    @GetMapping("/math/subtract")
    public BasicMathResult subtractInteger1FromInteger2(@RequestParam int parameter1,
                                                        @RequestParam int parameter2){
        return new BasicMathResult(parameter1, parameter2, MathOperation.SUBTRACTION, Math.subtractExact(parameter1,parameter2));
    }
}
