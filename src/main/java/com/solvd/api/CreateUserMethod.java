package com.solvd.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/users", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/users/create_user_rq.json")
@ResponseTemplatePath(path = "api/users/create_user_rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class CreateUserMethod extends AbstractApiMethodV2 {
    public CreateUserMethod() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}