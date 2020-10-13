package com.glocki.springbootmealer.web.payload;

import com.glocki.springbootmealer.web.payload.request.RegistrationRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RegistrationRequestTests {

    private Validator validator;

    @Before
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validate_blankPayload_shouldFail(){
        RegistrationRequest request = new RegistrationRequest();
        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);
        assertEquals(3, violations.size());
    }

    @Test
    public void validate_payloadWithInvalidEmail_shouldFail(){
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("CorrectUsername");
        request.setEmail("Invalid email Address");
        request.setPassword("CorrectPassword123!");

        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithEmailAddressLongerThan100_shouldFail(){
        String randomString = RandomStringUtils.randomAlphanumeric(60);

        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("CorrectUsername");
        request.setEmail(randomString + "@" + randomString);
        request.setPassword("CorrectPassword123!");

        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithUsernameLongerThan50_shouldFail(){
        String randomString = RandomStringUtils.randomAlphanumeric(60);

        RegistrationRequest request = new RegistrationRequest();
        request.setUsername(randomString);
        request.setEmail("correct@email.com");
        request.setPassword("CorrectPassword123!");

        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithPasswordShorterThan6_shouldFail(){
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("CorrectUsername");
        request.setEmail("correct@email.com");
        request.setPassword("bad");

        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithPasswordLongerThan40_shouldFail(){
        String randomString = RandomStringUtils.randomAlphanumeric(60);

        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("CorrectUsername");
        request.setEmail("correct@email.com");
        request.setPassword(randomString);

        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);
        assertEquals(1, violations.size());
    }


}
