package com.navigatingcancer.codingexercise.controller;


import com.navigatingcancer.codingexercise.model.MathOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MathsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenTwoAndTwo_WithAddition_shouldReturnFour() throws Exception{
        int param1 = 2;
        int param2 = 2;

        MvcResult result =  mockMvc.perform(get("/math/add")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parameter1").value(param1))
                .andExpect(jsonPath("$.parameter2").value(param2))
                .andExpect(jsonPath("$.operation").value(MathOperation.ADDITION.toString()))
                .andExpect(jsonPath("$.result").value(4))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    void givenTwoAndFive_WithAddition_shouldReturnSeven() throws Exception{
        int param1 = 2;
        int param2 = 5;

        mockMvc.perform(get("/math/add")
                        .param("parameter1", Integer.toString(param1))
                        .param("parameter2", Integer.toString(param2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parameter1").value(param1))
                .andExpect(jsonPath("$.parameter2").value(param2))
                .andExpect(jsonPath("$.operation").value(MathOperation.ADDITION.toString()))
                .andExpect(jsonPath("$.result").value(7));
    }

    @Test
    void givenTwoAndTwo_WithSubtraction_shouldReturnZero() throws Exception{
        int param1 = 2;
        int param2 = 2;

        mockMvc.perform(get("/math/subtract")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parameter1").value(param1))
                .andExpect(jsonPath("$.parameter2").value(param2))
                .andExpect(jsonPath("$.operation").value(MathOperation.SUBTRACTION.toString()))
                .andExpect(jsonPath("$.result").value(0));
    }

    @Test
    void givenTwoAndFive_WithSubtraction_shouldReturnNegativeThree() throws Exception{
        int param1 = 2;
        int param2 = 5;

        mockMvc.perform(get("/math/subtract")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parameter1").value(param1))
                .andExpect(jsonPath("$.parameter2").value(param2))
                .andExpect(jsonPath("$.operation").value(MathOperation.SUBTRACTION.toString()))
                .andExpect(jsonPath("$.result").value(-3));
    }

    @Test
    void givenTenAndEight_WithSubtraction_shouldReturnTwo() throws Exception{
        int param1 = 10;
        int param2 = 8;

        mockMvc.perform(get("/math/subtract")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parameter1").value(param1))
                .andExpect(jsonPath("$.parameter2").value(param2))
                .andExpect(jsonPath("$.operation").value(MathOperation.SUBTRACTION.toString()))
                .andExpect(jsonPath("$.result").value(2));
    }

    @Test
    void givenMaxIntegerAndOne_WithAddition_shouldThrow500ExceptionWithArithmeticError() throws Exception{
        int param1 = Integer.MAX_VALUE;
        int param2 = 1;

        mockMvc.perform(get("/math/add")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("arithmetic error")))
                .andExpect(jsonPath("$.parameter1").doesNotExist())
                .andExpect(jsonPath("$.parameter2").doesNotExist())
                .andExpect(jsonPath("$.operation").doesNotExist());

    }

    @Test
    void givenMinIntegerAndNegativeOne_WithAddition_shouldThrow500ExceptionWithArithmeticError() throws Exception{
        int param1 = Integer.MIN_VALUE;
        int param2 = -1;

        mockMvc.perform(get("/math/add")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("arithmetic error")))
                .andExpect(jsonPath("$.parameter1").doesNotExist())
                .andExpect(jsonPath("$.parameter2").doesNotExist())
                .andExpect(jsonPath("$.operation").doesNotExist());

    }

    @Test
    void givenMaxIntegerAndNegativeOne_WithSubtraction_shouldThrow500ExceptionWithArithmeticError() throws Exception{
        int param1 = Integer.MAX_VALUE;
        int param2 = -1;

        mockMvc.perform(get("/math/subtract")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("arithmetic error")))
                .andExpect(jsonPath("$.parameter1").doesNotExist())
                .andExpect(jsonPath("$.parameter2").doesNotExist())
                .andExpect(jsonPath("$.operation").doesNotExist());

    }

    @Test
    void givenMinIntegerAndOne_WithSubtraction_shouldThrow500ExceptionWithArithmeticError() throws Exception{
        int param1 = Integer.MIN_VALUE;
        int param2 = 1;

        mockMvc.perform(get("/math/subtract")
                .param("parameter1", Integer.toString(param1))
                .param("parameter2", Integer.toString(param2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("arithmetic error")))
                .andExpect(jsonPath("$.parameter1").doesNotExist())
                .andExpect(jsonPath("$.parameter2").doesNotExist())
                .andExpect(jsonPath("$.operation").doesNotExist());

    }
}
