/*
 * Copyright(c)2016 by AXON IVY AG, CH-6000 Lucerne. http://www.axonivy.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * AXON IVY AG. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with AXON IVY AG.
 */
package com.java.ee.rest.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.java.constants.Constants;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;

@ApplicationPath("api")
public class JaxrsConfiguration extends Application {

	public JaxrsConfiguration() {
		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.getSwagger().addSecurityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
		beanConfig.setVersion(new VersionUtil().getVersion(Constants.GROUP_ID, Constants.ARTIFACT_ID));
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setBasePath("java-8-guide/api");
		beanConfig.setResourcePackage("com.java.ee.rest.resource");
		beanConfig.setScan(true);
	}
}
