
package com.backbase.dbs.contactmanager.extension.advanced;

import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.dbs.contactmanager.contact.dto.Contact;
import com.backbase.dbs.contactmanager.contact.dto.InternalRequestWrapper;
import java.util.Arrays;
import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The custom camel Endpoints to be used in the behavior extension.
 */
@Component
public class CustomEndpoints {

    public static final String VALIDATION_INTERCEPTOR = "direct:contact.validate.interceptor";

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEndpoints.class);

    private static final String[] HIGH_RISK_COUNTRIES = {"NL"};

    @Consume(VALIDATION_INTERCEPTOR)
    public void executeValidation(InternalRequestWrapper<Contact> internalRequest) {

        LOGGER.info("======== In custom endpoint for validation ========");
        Contact contact = internalRequest.getInternalRequest().getData();
        if ("user".equals(internalRequest.getBbId()) && isHighRiskCountry(contact.getCountry())) {
            throw new BadRequestException("User is not allowed to handle a counterparty from a high risk country");
        }
    }

    private boolean isHighRiskCountry(String countryCode) {
        return Arrays.asList(HIGH_RISK_COUNTRIES).contains(countryCode);
    }
}