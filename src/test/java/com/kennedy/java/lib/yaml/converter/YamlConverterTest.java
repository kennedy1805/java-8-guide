package com.kennedy.java.lib.yaml.converter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

import com.kennedy.java.lib.yaml.converter.model.SalaryItemType;

public class YamlConverterTest {

	private static String SIT_1000 = ""
			+ "- code: '10000'\n"
			+ "  description: {en: Monthly Salary, de: Monatslohn}\n"
			+ "  formula: \"contract.getMonthlySalary(payslip.periodFrom)\"\n"
			+ "  name: MONTHLY_SALARY\n"
			+ "  variables:\n"
			+ "  - {isFinal: false, name: VAR_1, validFrom: null, validTo: null, value: \"1\"}\n"
			+ "  - {isFinal: false, name: VAR_2, validFrom: null, validTo: null, value: \"1\"}\n"
			+ "- code: '10005'\n"
			+ "  description: {en: Monthly Salary, de: Monatslohn}\n"
			+ "  formula: \"(variables.resolveVariableValue('9100')) ? (variables.resolveVariableValue('NET_OR_GROSS_SETOFF'))\n"
			+ "    : null;\"\n"
			+ "  name: HOURLY_SALARY\n"
			+ "  variables:\n"
			+ "  - {isFinal: false, name: VAR_1, validFrom: null, validTo: null, value: \"'value'\"}\n"
			+ "  - {isFinal: false, name: VAR_2, validFrom: null, validTo: null, value: \"I\"}\n";

	private Yaml yaml = new Yaml();

	@Test
	public void test_reader_SingleObject_parseToSIT() {
		InputStream stream = new ByteArrayInputStream(SIT_1000.getBytes(StandardCharsets.UTF_8));
		List<SalaryItemType> variables = Arrays.asList(yaml.loadAs(stream, SalaryItemType[].class));
		System.out.println("reader=" + variables);
	}

	@Test
	public void test_writer_SingleObject_parseSITToYaml() throws IOException {

		List<SalaryItemType> sits = Arrays.asList(
				SalaryItemType.builder().name("MONTHLY_SALARY").code("1000").formula("contract.getMonthlySalary(payslip.periodFrom)")
					.putDescription("en", "Monthly Salary")	.putDescription("de", "Monatslohn")
					.addVariable("VAR_1", "1", false).addVariable("VAR_2", "1", false).build(),
				SalaryItemType.builder().name("HOURLY_SALARY").code("1005")
					.formula("(variables.resolveVariableValue('9100')) ? (variables.resolveVariableValue('NET_OR_GROSS_SETOFF')) : null;")
					.putDescription("en", "Monthly Salary").putDescription("de", "Monatslohn")
					.addVariable("VAR_1", "'value'", false).addVariable("VAR_2", "I", false).build());
		
		SalaryItemTypeRepresenter representer = new SalaryItemTypeRepresenter();
		representer.setDefaultFlowStyle(FlowStyle.BLOCK);
		DumperOptions option = new DumperOptions();
		yaml = new Yaml(representer, option);
		String stream = yaml.dump(sits);
		stream = stream
				.replace("'\"''", "<editField>").replace("''\"'", "</editField>")
				.replace("''", "'").replace("'\"", "\"").replace("\"'", "\"")
				.replace("<editField>", "\"'").replace("</editField>", "'\"");
		
		File file = new File("C:\\Users\\thvu\\Desktop\\sit_out.yaml");
		FileUtils.writeStringToFile(file, stream);
		System.out.println(stream);
	}

}
