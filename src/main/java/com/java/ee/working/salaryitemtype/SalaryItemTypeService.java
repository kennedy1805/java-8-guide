/*
 * Copyright(c)2016 by AXON IVY AG, CH-6000 Lucerne. http://www.axonivy.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * AXON IVY AG. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with AXON IVY AG.
 */
package com.java.ee.working.salaryitemtype;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import org.yaml.snakeyaml.Yaml;

import com.java.ee.working.beans.BeanCopier;
import com.kennedy.java.lib.yaml.converter.model.SalaryItemType;

@Stateless
public class SalaryItemTypeService {

	
	public String generateYamlDocument(List<SalaryItemType> sits) {
		List<SalaryItemTypeYamlModel> sitYamls  = BeanCopier.inList(sits, SalaryItemTypeYamlModel.class);
		
		StandardConfigurationYamlRepresenter representer = new StandardConfigurationYamlRepresenter();
		Yaml yaml = new Yaml(representer);
		String yamlString = yaml.dump(sitYamls);
		return normalizeQuotes(yamlString);
	}

	public List<SalaryItemType> extractSITFromString(String yamlFormatSIT) {
		Yaml yaml = new Yaml();
		List<SalaryItemTypeYamlModel> sitYamls = null;
		sitYamls = Arrays.asList(yaml.loadAs(yamlFormatSIT, SalaryItemTypeYamlModel[].class));
		return BeanCopier.outList(sitYamls, SalaryItemType.class);
	}

	protected String normalizeQuotes(String yamlString) {
		return yamlString
				.replace("'\"''", "<editField>").replace("''\"'", "</editField>")
				.replace("''", "'").replace("'\"", "\"").replace("\"'", "\"")
				.replace("<editField>", "\"'").replace("</editField>", "'\"");
	}
}
