package com.solvd;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.utils.config.Configuration;


@Endpoint(url = "${config.api_url}/users/${id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/users/get_user_rs.json")
public class GetUserById extends AbstractApiMethodV2 {
    public GetUserById(int id) {
        replaceUrlPlaceholder("id", String.valueOf(id));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}