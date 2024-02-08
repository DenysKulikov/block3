package com.solvd;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/users", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/users/create_user_rq.json")
@ResponseTemplatePath(path = "api/users/create_user_rs.json")
public class CreateUserMethod extends AbstractApiMethodV2 {
    public CreateUserMethod() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}