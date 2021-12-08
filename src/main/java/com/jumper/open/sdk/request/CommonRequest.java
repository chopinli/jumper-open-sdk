package com.jumper.open.sdk.request;

import com.jumper.open.sdk.response.CommonResponse;

/**
 * @author libin
 */
public class CommonRequest extends BaseRequest<CommonResponse> {

    public CommonRequest(String method) {
        super(method, null);
    }

    public CommonRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
