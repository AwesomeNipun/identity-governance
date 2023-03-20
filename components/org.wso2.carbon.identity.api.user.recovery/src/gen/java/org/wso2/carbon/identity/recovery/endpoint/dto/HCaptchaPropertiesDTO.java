package org.wso2.carbon.identity.recovery.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class HCaptchaPropertiesDTO  {
  
  
  
  private Boolean hCaptchaEnabled = null;
  
  
  private String hCaptchaKey = null;
  
  
  private String hCaptchaAPI = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("hCaptchaEnabled")
  public Boolean getHCaptchaEnabled() {
    return hCaptchaEnabled;
  }
  public void setHCaptchaEnabled(Boolean hCaptchaEnabled) {
    this.hCaptchaEnabled = hCaptchaEnabled;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("hCaptchaKey")
  public String getHCaptchaKey() {
    return hCaptchaKey;
  }
  public void setHCaptchaKey(String hCaptchaKey) {
    this.hCaptchaKey = hCaptchaKey;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("hCaptchaAPI")
  public String getHCaptchaAPI() {
    return hCaptchaAPI;
  }
  public void setHCaptchaAPI(String hCaptchaAPI) {
    this.hCaptchaAPI = hCaptchaAPI;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class HCaptchaPropertiesDTO {\n");
    
    sb.append("  hCaptchaEnabled: ").append(hCaptchaEnabled).append("\n");
    sb.append("  hCaptchaKey: ").append(hCaptchaKey).append("\n");
    sb.append("  hCaptchaAPI: ").append(hCaptchaAPI).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
