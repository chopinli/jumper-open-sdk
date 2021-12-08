package com.jumper.open.sdk.request;

import com.jumper.open.sdk.response.DemoFileUploadResponse;

/**
 * @author libin
 */
public class DemoFileUploadRequest extends BaseRequest<DemoFileUploadResponse> {
    @Override
    protected String method() {
        return "file.upload";
    }
}
