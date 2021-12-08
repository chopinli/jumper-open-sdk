package com.jumper.open.sdk.request;

import com.jumper.open.sdk.response.GetStoryResponse;

public class GetStoryRequest extends BaseRequest<GetStoryResponse> {
    @Override
    protected String method() {
        return "story.get";
    }

}
