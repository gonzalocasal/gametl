package com.gametl.bowling.util;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:bowling.properties")
public class BowlingResources {

    public static String errorMessageInvalidScore;
    public static String filePath;

    @Value("${file.path}")
    public void setFilePath (String filePath) {
        BowlingResources.filePath = filePath;
    }

    @Value("${error.message.invalid.score}")
    public void setErrorMessageInvalidScore (String messageInvalidScore) {
        BowlingResources.errorMessageInvalidScore = messageInvalidScore;
    }

}