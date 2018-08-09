package com.java.ee.working.salaryitemtype;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yaml.snakeyaml.Yaml;

import com.kennedy.java.lib.general.converter.DataTableUtils;
import com.kennedy.java.lib.yaml.converter.model.SalaryItemType;
import com.kennedy.java.lib.yaml.converter.model.Variable;

@RunWith(CdiRunner.class)
public class CompareSITTest {

	private Comparator<VariableComparable> byVarName = 
			(VariableComparable o1, VariableComparable o2)->o1.getName().compareTo(o2.getName());
	private Comparator<SalaryItemTypeComparable> bySitCode = 
					(SalaryItemTypeComparable o1, SalaryItemTypeComparable o2)->o1.getCode().compareTo(o2.getCode());
			
	@Inject
	private SalaryItemTypeService salaryItemTypeService;

	@Test
	public void test_writer_SingleObject_parseSITToYaml() throws IOException {

		String sitDev = readLineByLineJava8("C:/Users/thvu/Desktop/sit_dev.yaml");
		List<SalaryItemType> sit_dev = salaryItemTypeService.extractSITFromString(sitDev);
		Map<String, SalaryItemType> mapDev = sit_dev.stream().collect(Collectors.toMap(SalaryItemType::getCode, item -> item));
		
		String sitMaster = readLineByLineJava8("C:/Users/thvu/Desktop/sit_master.yaml");
		List<SalaryItemType> sit_master = salaryItemTypeService.extractSITFromString(sitMaster);
		Map<String, SalaryItemType> mapMaster = sit_master.stream().collect(Collectors.toMap(SalaryItemType::getCode, item -> item));
		
		Set<String> codes = new HashSet<>();
		codes.addAll(mapDev.keySet());codes.addAll(mapMaster.keySet());
		
		List<SalaryItemTypeComparable> sitYamls = new ArrayList<>();
		for (String code : codes) {
			SalaryItemTypeComparable ins = new SalaryItemTypeComparable();
			ins.setCode(code);
			
			SalaryItemType dev = mapDev.get(code);
			SalaryItemType master = mapMaster.get(code);
			
			Set<String> varNames = new HashSet<>();
			Map<String, Variable> devVariable = new HashMap<>();
			Map<String, Variable> masterVariable = new HashMap<>();
			if (dev != null && CollectionUtils.isNotEmpty(dev.getVariables())) {
				varNames.addAll(dev.getVariables().stream().map(Variable::getName).collect(Collectors.toSet()));
				devVariable = dev.getVariables().stream().collect(Collectors.toMap(Variable::getName, item -> item));
			}
			if (master != null && CollectionUtils.isNotEmpty(master.getVariables())) {
				varNames.addAll(master.getVariables().stream().map(Variable::getName).collect(Collectors.toSet()));
				masterVariable = master.getVariables().stream().collect(Collectors.toMap(Variable::getName, item -> item));
			}
			List<VariableComparable> inputObjs = new ArrayList<>();
			for (String varName : varNames) {
				VariableComparable var = new VariableComparable();
				var.setName(varName);
				var.setDevValue(devVariable.containsKey(varName) ? devVariable.get(varName).getValue() : null);
				var.setMasterValue(masterVariable.containsKey(varName) ? masterVariable.get(varName).getValue() : null);
				var.setIsFinal(compareNull(var.getMasterValue(), var.getDevValue()) ? "TRUE" : "FALSE");
				if ("TRUE".equals(var.getIsFinal())) {
					continue;
				}
				var.setJson("- { name: " + getValue(varName) + ", value: " + getValue(var.getDevValue()) + ", isFinal: false}");
				inputObjs.add(var);
			}
			if (CollectionUtils.isEmpty(inputObjs)) {
				continue;
			}
			Collections.sort(inputObjs, byVarName);
			ins.setVariable(DataTableUtils.convertFromListObjectToStringTable(inputObjs, VariableComparable.class));
			sitYamls.add(ins);
		}
		Collections.sort(sitYamls, bySitCode);
		
		Yaml yaml = new Yaml();
		String stream = yaml.dump(sitYamls);
		File file = new File("C:\\Users\\thvu\\Desktop\\sit_out_2.yaml");
		FileUtils.writeStringToFile(file, stream);
	}

	private String getValue(String var) {
		return StringUtils.isBlank(var) ? null : "\"" + var + "\"";
	}

	private static String readLineByLineJava8(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	private boolean compareNull(String master, String dev) {
		if (master == dev) {
			return true;
		} else if (master == null || dev == null) {
			return false;
		} else {
			return master.equals(dev);
		}
	}
	
}
