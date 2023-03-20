package org.wso2.carbon.identity.recovery.endpoint;

import org.wso2.carbon.identity.recovery.endpoint.factories.HcaptchaApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.recovery.endpoint.dto.HCaptchaPropertiesDTO;
import org.wso2.carbon.identity.recovery.endpoint.dto.HCaptchaVerificationResponseDTO;
import org.wso2.carbon.identity.recovery.endpoint.dto.HCaptchaResponseTokenDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/hcaptcha")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/hcaptcha", description = "the hcaptcha API")
public class HcaptchaApi  {

   private final HcaptchaApiService delegate = HcaptchaApiServiceFactory.getHcaptchaApi();

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get HCaptcha\n", notes = "return the hCaptcha information if its supported for the given tenant.\n", response = HCaptchaPropertiesDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response getHCaptcha(@ApiParam(value = "type of captcha",required=true) @QueryParam("captcha-type")  String captchaType,
    @ApiParam(value = "username recovery or password recovery",required=true) @QueryParam("recovery-type")  String recoveryType,
    @ApiParam(value = "tenant domain. Default `carbon.super`") @QueryParam("tenant-domain")  String tenantDomain)
    {
    return delegate.getHCaptcha(captchaType,recoveryType,tenantDomain);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Verify HCaptcha\n", notes = "return true or false after verify the captcha response.\n", response = HCaptchaVerificationResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response verifyHCaptcha(@ApiParam(value = "hcaptcha response." ,required=true ) HCaptchaResponseTokenDTO hCaptchaResponse,
    @ApiParam(value = "type of captcha",required=true) @QueryParam("captcha-type")  String captchaType,
    @ApiParam(value = "tenant domain. Default `carbon.super`") @QueryParam("tenant-domain")  String tenantDomain)
    {
    return delegate.verifyHCaptcha(hCaptchaResponse,captchaType,tenantDomain);
    }
}

