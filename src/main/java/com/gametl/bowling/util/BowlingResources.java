package com.gametl.bowling.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BowlingResources {

    public static String errorMessageInvalidScore;

    @Value("${error.message.invalid.score}")
    public void setErrorMessageInvalidScore (String messageInvalidScore) {
        BowlingResources.errorMessageInvalidScore = messageInvalidScore;
    }

}