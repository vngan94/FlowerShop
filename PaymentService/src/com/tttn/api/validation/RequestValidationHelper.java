package com.tttn.api.validation;

import com.tttn.api.entities.request.PostAccountPayLoad;

public class RequestValidationHelper {

    @SuppressWarnings("null")
    public static void validateRequest(PostAccountPayLoad request) throws Exception {
       ModelValidationHelper.validate(request);
    }

}

