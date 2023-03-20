package org.wso2.carbon.identity.recovery.endpoint.factories;

import org.wso2.carbon.identity.recovery.endpoint.HcaptchaApiService;
import org.wso2.carbon.identity.recovery.endpoint.impl.HcaptchaApiServiceImpl;

public class HcaptchaApiServiceFactory {

   private final static HcaptchaApiService service = new HcaptchaApiServiceImpl();

   public static HcaptchaApiService getHcaptchaApi()
   {
      return service;
   }
}
