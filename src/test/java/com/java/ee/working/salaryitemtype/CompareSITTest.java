package com.java.ee.working.salaryitemtype;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
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

	private static final String OUTPUT_FILE = "C:\\Users\\thvu\\Desktop\\sit_out_2.yaml";
	private static final String FILE_2 = "C:/Users/thvu/Desktop/sit_master.yaml";
	private static final String FILE_1 = "C:/Users/thvu/Desktop/sit_dev.yaml";
	private Comparator<VariableComparable> byVarName = 
			(VariableComparable o1, VariableComparable o2)->o1.getName().compareTo(o2.getName());
	private Comparator<SalaryItemTypeComparable> bySitCode = 
					(SalaryItemTypeComparable o1, SalaryItemTypeComparable o2)->o1.getCode().compareTo(o2.getCode());
			
	@Inject
	private SalaryItemTypeService salaryItemTypeService;

	@Test
	public void print_all_SIT_Have_condition() {
		Map<String, String> conditions = new HashMap<>();
		conditions.put("ACCOUNTING_GROUP", "GROSS_SALARY");
		
		
		String sitDev = readLineByLineJava8(FILE_1);
		List<SalaryItemType> sit_dev = salaryItemTypeService.extractSITFromString(sitDev);
		
		List<String> codes = new ArrayList<>();
		for (SalaryItemType salaryItemType : sit_dev) {
			List<Variable> vars = salaryItemType.getVariables();
			
			if (isMatchedWithCondtion(vars, conditions)) {
				codes.add(salaryItemType.getCode());
			}
		}
		Collections.sort(codes);
		System.err.println(codes);
	}
	
	
	private boolean isMatchedWithCondtion(List<Variable> vars, Map<String, String> conditions) {
		for (Entry<String, String> entry : conditions.entrySet()) {
			boolean foundItem = false;
			for (Variable var : vars) {
				if (entry.getKey().equals(var.getName()) && entry.getValue().equals(var.getValue())) {
					foundItem = true;
					break;
				}
			}
			if (!foundItem) {
				return false;
			}
		}
		return true;
	}


	@SuppressWarnings("deprecation")
	@Test
	public void output_differences_between_Dev_master() throws IOException {

		String sitDev = readLineByLineJava8(FILE_1);
		List<SalaryItemType> sit_dev = salaryItemTypeService.extractSITFromString(sitDev);
		Map<String, SalaryItemType> mapDev = sit_dev.stream().collect(Collectors.toMap(SalaryItemType::getCode, item -> item));
		
		String sitMaster = readLineByLineJava8(FILE_2);
		List<SalaryItemType> sit_master = salaryItemTypeService.extractSITFromString(sitMaster);
		Map<String, SalaryItemType> mapMaster = sit_master.stream().collect(Collectors.toMap(SalaryItemType::getCode, item -> item));
		
		Set<String> codes = new HashSet<>();
		codes.addAll(mapDev.keySet());codes.addAll(mapMaster.keySet());
		
		List<SalaryItemTypeComparable> sitYamls = new ArrayList<>();
		for (String code : codes) {
			SalaryItemTypeComparable ins = new SalaryItemTypeComparable();
			
			SalaryItemType dev = mapDev.get(code);
			SalaryItemType master = mapMaster.get(code);
			ins.setDifferentFormula(isDifferentFormula(dev.getFormula(), master.getFormula()));
			ins.setVariable(getDataTableDifferencesVariables(dev, master));
			ins.setTags(getDataTableDifferencesTags(dev, master));
			ins.setDescriptions(getDataTableDifferencesDescriptions(dev, master));
			if (!ins.isEmpty()) {
				continue;
			}
			ins.setCode(code);
			sitYamls.add(ins);
		}
		Collections.sort(sitYamls, bySitCode);
		
		Yaml yaml = new Yaml();
		String stream = yaml.dump(sitYamls);
		exportToFile(stream);
	}


	private Boolean isDifferentFormula(String first, String second) {
		return adjustFormula(first).equals(adjustFormula(second)) ? null : true;
	}

	private String adjustFormula(String formula) {
		formula = formula.replace("\n", "").replace(" ", "");
		return formula;
	}
	
	@Test
	public void output_SIT_Accounting_dataTables() throws IOException {
//		List<String> acceptedSITs = Arrays.asList("1000","1900","1910","5081","6000","5111","1972","5093","5120","5050","1961","6600","6551","6552","6553");
		String sitDev = readLineByLineJava8(FILE_1);
		List<SalaryItemType> sit_dev = salaryItemTypeService.extractSITFromString(sitDev);
		List<Map<String, String>> sitsTags = sit_dev.stream()
//				.filter(sit -> acceptedSITs.contains(sit.getCode()))
				.filter(SalaryItemType::containsAccountingVariables)
				.map(sit -> {
			Map<String, String> map = new HashMap<>();
			map.put("code", sit.getCode());
			map.put("description_en", sit.getI18n().get(Locale.ENGLISH));
			map.put("description_de", sit.getI18n().get(Locale.GERMAN));
			for (String varName : sit.getAccountingVariables()) {
				String variableValueByName = sit.getVariableValueByName(varName);
				map.put(varName, variableValueByName == null ? "" : variableValueByName);
			}
			return map;
		}).collect(Collectors.toList());
		
		String stream = DataTableUtils.convertFromListObjectToStringTable(sitsTags);
		exportToFile(stream);
	}
	
	
	@Test
	public void output_Tags_dataTables() throws IOException {
		List<String> acceptedSITs = Arrays.asList("5011","5021","5031","5032");
		
		String sitDev = readLineByLineJava8(FILE_1);
		List<SalaryItemType> sit_dev = salaryItemTypeService.extractSITFromString(sitDev);
		
		List<Map<String, String>> sitsTags = sit_dev.stream()
				.filter(sit -> acceptedSITs.contains(sit.getCode()))
				.map(sit -> {
			Map<String, String> map = new HashMap();
			map.put("code", sit.getCode());
			for (Entry<Locale, String> tags : sit.getTagI18N().entrySet()) {
				map.put(tags.getKey().getLanguage(), tags.getValue());
			}
			return map;
		}).collect(Collectors.toList());
		String stream = DataTableUtils.convertFromListObjectToStringTable(sitsTags);
		exportToFile(stream);
	}


	private void exportToFile(String stream) throws IOException {
		File file = new File(OUTPUT_FILE);
		FileUtils.writeStringToFile(file, stream);
	}
	
	
	private String getDataTableDifferencesDescriptions(SalaryItemType dev, SalaryItemType master) {
		Set<String> keys = new HashSet<>();
		Map<Locale, String> devTag = new HashMap<>();
		Map<Locale, String> masterTags = new HashMap<>();
		if (dev != null && MapUtils.isNotEmpty(dev.getTagI18N())) {
			keys.addAll(dev.getI18n().keySet().stream().map(locale -> locale.toString()).collect(Collectors.toSet()));
			devTag = dev.getI18n();
		}
		if (master != null && MapUtils.isNotEmpty(master.getTagI18N())) {
			keys.addAll(master.getI18n().keySet().stream().map(locale -> locale.toString()).collect(Collectors.toSet()));
			masterTags = master.getI18n();
		}
		List<TagComparable> tags = new ArrayList<>();
		for (String key : keys) {
			TagComparable tag = TagComparable.builder().key(key).devValue(devTag.get(Locale.forLanguageTag(key))).masterValue(masterTags.get(Locale.forLanguageTag(key))).build();
			if (compareNull(tag.getDevValue(), tag.getMasterValue())) {
				continue;
			}
			tags.add(tag);
		}
		
		if (CollectionUtils.isEmpty(tags)) {
			return null;
		}
		return DataTableUtils.convertFromListObjectToStringTable(tags, TagComparable.class);
	}
	
	private String getDataTableDifferencesTags(SalaryItemType dev, SalaryItemType master) {
		Set<String> keys = new HashSet<>();
		Map<Locale, String> devTag = new HashMap<>();
		Map<Locale, String> masterTags = new HashMap<>();
		if (dev != null && MapUtils.isNotEmpty(dev.getTagI18N())) {
			keys.addAll(dev.getTagI18N().keySet().stream().map(locale -> locale.toString()).collect(Collectors.toSet()));
			devTag = dev.getTagI18N();
		}
		if (master != null && MapUtils.isNotEmpty(master.getTagI18N())) {
			keys.addAll(master.getTagI18N().keySet().stream().map(locale -> locale.toString()).collect(Collectors.toSet()));
			masterTags = master.getTagI18N();
		}
		List<TagComparable> tags = new ArrayList<>();
		for (String key : keys) {
			TagComparable tag = TagComparable.builder().key(key).devValue(devTag.get(Locale.forLanguageTag(key))).masterValue(masterTags.get(Locale.forLanguageTag(key))).build();
			if (compareNull(tag.getDevValue(), tag.getMasterValue())) {
				continue;
			}
			tags.add(tag);
		}
		
		if (CollectionUtils.isEmpty(tags)) {
			return null;
		}
		return DataTableUtils.convertFromListObjectToStringTable(tags, TagComparable.class);
	}

	private String getDataTableDifferencesVariables(SalaryItemType dev, SalaryItemType master) {
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
		StringBuilder difs = new StringBuilder();
		for (String varName : varNames) {
			VariableComparable var = new VariableComparable();
			var.setName(varName);
			var.setDevValue(devVariable.containsKey(varName) ? devVariable.get(varName).getValue() : null);
			var.setMasterValue(masterVariable.containsKey(varName) ? masterVariable.get(varName).getValue() : null);
			var.setIsFinal(compareNull(var.getMasterValue(), var.getDevValue()) ? "TRUE" : "FALSE");
			if ("TRUE".equals(var.getIsFinal())) {
				continue;
			}
			String str = "- { name: " + getValue(varName) + ", value: " + getValue(var.getDevValue()) + ", isFinal: false}";
			var.setJson(str);
			difs.append(str).append("\n");
			inputObjs.add(var);
		}
		if (CollectionUtils.isEmpty(inputObjs)) {
			return null;
		}
		Collections.sort(inputObjs, byVarName);
		return DataTableUtils.convertFromListObjectToStringTable(inputObjs, VariableComparable.class);
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
		List<Integer> intis = new ArrayList<>();
		intis.add(1/2);
		if (master == dev) {
			return true;
		} else if (master == null || dev == null) {
			return false;
		} else {
			return master.equals(dev);
		}
	}
	
}
