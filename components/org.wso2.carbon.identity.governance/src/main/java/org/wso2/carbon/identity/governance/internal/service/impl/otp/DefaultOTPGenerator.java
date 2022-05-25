/*
 * Copyright (c) 2022, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.governance.internal.service.impl.otp;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.governance.IdentityMgtConstants;
import org.wso2.carbon.identity.governance.exceptions.otp.OTPGeneratorClientException;
import org.wso2.carbon.identity.governance.exceptions.otp.OTPGeneratorServerException;
import org.wso2.carbon.identity.governance.service.otp.OTPGenerator;

import java.security.SecureRandom;

/**
 * Default class to generate OTP.
 */
public class DefaultOTPGenerator implements OTPGenerator {

    public DefaultOTPGenerator() {}

    /**
     * Generates the OTP based on the provided charSet and length.
     *
     * @param charSet Character set allowed for OTP.
     * @param otpLength Length of OTP.
     * @return String Value of OTP string.
     * @throws OTPGeneratorClientException OTP Generator Client Exception
     * @throws OTPGeneratorServerException OTP Generator Server Exception
     */
    @Override
    public String generateOTP(String charSet, int otpLength) throws OTPGeneratorClientException,
            OTPGeneratorServerException {

        if (StringUtils.isBlank(charSet)) {
            throw new OTPGeneratorClientException(
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_INVALID_OTP_CHARACTER_SET.getCode(),
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_INVALID_OTP_CHARACTER_SET.getMessage()
            );
        }
        if (otpLength == -1) {
            throw new OTPGeneratorClientException(
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_INVALID_OTP_LENGTH.getCode(),
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_INVALID_OTP_LENGTH.getMessage()
            );
        }
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            char[] otpCharacters = charSet.toCharArray();
            for (int otpCharacterIndex = 0; otpCharacterIndex < otpLength; otpCharacterIndex++) {
                stringBuilder.append(otpCharacters[secureRandom.nextInt(otpCharacters.length)]);
            }
        } catch (Exception e) {
            throw new OTPGeneratorServerException(
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_ERROR_GENERATING_OTP.getCode(),
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_ERROR_GENERATING_OTP.getMessage()
            );
        }
        return stringBuilder.toString();
    }

    /**
     * Generates the OTP based on the OTP properties.
     *
     * @param useNumeric Whether numeric values should be used.
     * @param useUppercaseLetters Whether upper case letters should be used.
     * @param useLowercaseLetters Whether lower case letters should be used.
     * @param otpLength Length of OTP.
     * @return String Value of OTP string.
     * @throws OTPGeneratorClientException OTP Generator Client Exception
     * @throws OTPGeneratorServerException OTP Generator Server Exception
     */
    @Override
    public String generateOTP(boolean useNumeric, boolean useUppercaseLetters, boolean useLowercaseLetters,
                              int otpLength) throws OTPGeneratorClientException, OTPGeneratorServerException {

        if (!useNumeric && !useUppercaseLetters && !useLowercaseLetters) {
            throw new OTPGeneratorClientException(
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_INVALID_OTP_CHARACTER_SET.getCode(),
                    IdentityMgtConstants.ErrorMessages.ERROR_CODE_INVALID_OTP_CHARACTER_SET.getMessage());
        }
        StringBuilder charSet = new StringBuilder();
        if (useUppercaseLetters) {
            charSet.append(IdentityMgtConstants.SMS_OTP_GENERATE_ALPHABET_CHAR_SET);
        }
        if (useLowercaseLetters) {
            charSet.append(IdentityMgtConstants.SMS_OTP_GENERATE_ALPHABET_CHAR_SET.toLowerCase());
        }
        if (useNumeric) {
            charSet.append(IdentityMgtConstants.SMS_OTP_GENERATE_NUMERIC_CHAR_SET);
        }

        return generateOTP(charSet.toString(), otpLength);
    }
}
