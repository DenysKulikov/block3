package com.solvd;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/users/${id}", methodType = HttpMethodType.PATCH)
@RequestTemplatePath(path = "api/users/update_user_rq.json")
@ResponseTemplatePath(path = "api/users/update_user_rs.json")
public class UpdateUserPatchMethod extends AbstractApiMethodV2 {
    public UpdateUserPatchMethod(int id) {
        replaceUrlPlaceholder("id", String.valueOf(id));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}