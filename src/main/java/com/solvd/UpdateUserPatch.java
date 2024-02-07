package com.solvd;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/api/users/${user_id}", methodType = HttpMethodType.PATCH)
@RequestTemplatePath(path = "api/users/update_user_rq.json")
@ResponseTemplatePath(path = "api/users/update_user_rs.json")
public class UpdateUserPatch extends AbstractApiMethodV2 {
    public UpdateUserPatch(int id) {
        replaceUrlPlaceholder("user_id", String.valueOf(id));
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}