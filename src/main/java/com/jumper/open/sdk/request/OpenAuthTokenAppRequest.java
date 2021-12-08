package com.jumper.open.sdk.request;

import com.jumper.open.sdk.response.OpenAuthTokenAppResponse;

/**
 * @author libin
 */
public class OpenAuthTokenAppRequest extends BaseRequest<OpenAuthTokenAppResponse> {
    @Override
    protected String method() {
        return "open.auth.token.app";
    }
}
