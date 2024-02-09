package com.solvd.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/users/${id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/users/get_user_rs.json")
public class GetUserByIdMethod extends AbstractApiMethodV2 {
    public GetUserByIdMethod(int id) {
        replaceUrlPlaceholder("id", String.valueOf(id));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}