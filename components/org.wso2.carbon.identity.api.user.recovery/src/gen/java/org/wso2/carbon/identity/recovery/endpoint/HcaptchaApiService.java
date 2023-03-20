package org.wso2.carbon.identity.recovery.endpoint;

import org.wso2.carbon.identity.recovery.endpoint.dto.HCaptchaResponseTokenDTO;

import javax.ws.rs.core.Response;

public abstract class HcaptchaApiService {
    public abstract Response getHCaptcha(String captchaType,String recoveryType,String tenantDomain);
    public abstract Response verifyHCaptcha(HCaptchaResponseTokenDTO hCaptchaResponse,String captchaType,String tenantDomain);
}

