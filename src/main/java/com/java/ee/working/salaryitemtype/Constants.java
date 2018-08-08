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

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

public class Constants {

    private static final String INSURANCE_CONTRACTS_CONTEXT = "/insurance-contracts";

	public static final String INSURANCE_CONTRACT_ID = "{insurance-contract-id}";

	private static final String CONTRACT_ID_IN_COMPANY_ID = "contract id: CONTRACT_ID in company id: COMPANY_ID";

    private static final String CONTRACT_ID = "CONTRACT_ID";

    private static final String COMPANY_ID = "COMPANY_ID";
    
	public static final String UTF_TIMEZONE_STRING = "+00:00";
	public static final String COMMON_TIME_FORMAT = "T00:00:00Z";
	public static final String DATE_FORMAT_IN_USING_EXAMPLE = "2017-01-01" + COMMON_TIME_FORMAT;
	public static final String DEFAULT_LANGUAGE_DE = "DE";
	public static final String DATE_TIME_FORMAT_DD_MM_YYYY_LINE = "dd-MM-yyyy";
	public static final String DATE_TIME_FORMAT_MM_YYYY_DOT = "MM.yyyy";
	public static final String COLON = ":";
	public static final String SLASH = "/";
	public static final String NEW_LINE_AND_TAB = "\n\t";
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String BASIC = "Basic";
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENTITY_NOT_FOUND = "Entity not found";
	public static final String CAN_NOT_SEAL_PAYSLIP = "Can not update a SEALED payslip !";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String INVALID_PATH = "Invalid update path";
	public static final String PERSON_NOT_FOUND = " Person not found ";
	public static final String WORKPLACE_NOT_FOUND = " Workplace not found ";
	private static final String COMPANY_NOT_FOUND_WITH_ID = "Cannot find a company with id: COMPANY_ID";
	private static final String COMPANY_NOT_FOUND_WITH_URI = "Cannot find a company with uri: COMPANY_URI";
	private static final String INSURANCE_CONTRACT_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find an insurance contract with id: INSURANCE_CONTRACT_ID in company id: COMPANY_ID";
	private static final String INSURER_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find an insurer with id: INSURER_ID in company id: COMPANY_ID";
	private static final String EMPLOYEE_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a employee with id: EMPLOYEE_ID in company id: COMPANY_ID";
	private static final String CONTRACT_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a contract with id: CONTRACT_ID in company id: COMPANY_ID";
	private static final String PAYSLIP_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a payslip with id: PAYSLIP_ID and contract id: CONTRACT_ID in company id: COMPANY_ID";
	private static final String SALARY_ITEM_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a salary item with id: SALARY_ITEM_ID and payslip id PAYSLIP_ID and contract id CONTRACT_ID in company id COMPANY_ID";
	private static final String PAYSLIP_NOT_FOUND_WITH_MONTH_AND_PARENTS_ID = "Cannot find a payslip with month: PAYSLIP_MONTH and contract id: CONTRACT_ID in company id: COMPANY_ID";
	private static final String PAYSLIP_NOT_FOUND_WITH_URI_AND_PARENTS_ID = "Cannot find a payslip with uri: PAYSLIP_URI and contract id: CONTRACT_ID in company id: COMPANY_ID";
	private static final String WORKPLACE_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a workplace with id: WORKPLACE_ID in company id: COMPANY_ID";
	private static final String CONTRACT_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a contract temporal with id: CONTRACT_TEMPORAL_ID and "
																			+ CONTRACT_ID_IN_COMPANY_ID;
	private static final String SALARY_CONFIGURATION_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a salary configuration temporal with id: SCTEMPORAL_ID and "
																			+ CONTRACT_ID_IN_COMPANY_ID;
	private static final String SALARY_ITEM_TYPE_GROUP_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a salary item type group temporal with id: SITGTEMPORAL_ID and "
																			+ CONTRACT_ID_IN_COMPANY_ID;
	private static final String SALARY_ITEM_TYPE_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a salary item type temporal with id: SITTEMPORAL_ID and "
																			+ CONTRACT_ID_IN_COMPANY_ID;
	private static final String FAK_ALLOCATION_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a fak allocation with id: FAKALLOCATION_ID and "
																			+ CONTRACT_ID_IN_COMPANY_ID;
	private static final String THIRD_PARTY_RECEIVER_NOT_FOUND_WITH_ID_AND_PARENTS_ID = "Cannot find a fak allocation with id: THIRD_PARTY_RECEIVER_ID and "
																			+ CONTRACT_ID_IN_COMPANY_ID;
	public static final String PERSON_INSERTED_FAILED = "Person inserted failed";
	public static final String MSG_OK = "Successful operation";
	public static final String MSG_VALIDATION = "Validation exception";
	public static final String MSG_INTERNAL_ERROR = "Server error";
	public static final String MSG_NO_CONTENT = "No content";
	public static final String MSG_INVALID_GENDER_SUPPLIED = "Invalid gender supplied";
	public static final String ID_IS_NOT_ALLOW_IN_API = "The property Id does not allow in this API.";
	public static final String HTTP_PROTOCOL = "http://";
	public static final String AMOUNT_SHOULD_NOT_BE_A_NEGATIVE_NUMBER = "Third_party amount should not be a negative number.";
	public static final String EMAIL_TYPE_WORK = "WORK";
	public static final String EMAIL_TYPE_HOME = "HOME";
	public static final String PHONE_TYPE_WORK = "WORK";
	public static final String PHONE_TYPE_HOME = "HOME";
	public static final String MSG_UNMATCHED_DATA = "Unmatched data";
	public static final String JSON_EXCEPTION = "Json exception";
	public static final Integer FIRST_PAYSLIP_VERSION_OF_A_MONTH = 1;

	public static final List<String> LIST_PERSON_PROPERTIES = Arrays.asList(new String[] { "/salutation", "/socialSecurityNumber", "/sex", 
			"/firstName", "/lastName", "/phoneList", "/birthday", "/nationality", "/language", "/addressList", "/emailList" });
	public static final List<String> LIST_PATCH_IGNORED_PROPERTIES = Arrays.asList(new String[] { "/personUri" });
	public static final String INSURANCE_TYPE_UVG = "UVG";
	public static final String INSURANCE_TYPE_UVGZ = "UVGZ";
	public static final String INSURANCE_TYPE_KTG = "KTG";
	public static final String INSURANCE_CODE_STRING_WITH_A = "/A";
	public static final String INSURANCE_CODE_STRING_WITH_B = "/B";
	public static final String INSURANCE_CODE_STRING_ONE_ONE = "11";
	public static final String INSURANCE_CODE_STRING_ONE_TWO = "12";
	public static final String NUMBER_ZERO = "0";
	public static final String NUMBER_ONE = "1";
	public static final String NUMBER_TWO = "2";

	// --------Kubernetes service name--------
	public static final String KUBECTL_LUZCOMPENSATION_SERVICE = "luz-compensation-service";
	public static final String KUBECTL_LUZPERSON_SERVICE = "luz-person-service";
	public static final String KUBECTL_LUZFINANCE_SERVICE = "luzfin-finance-service";
	public static final String KUBECTL_ACCOUNTING_SERVICE = "luz-accounting-service";

	// --------Context path--------
	public static final String LUZPERSON_CONTEXT_PATH = "/luz_person";
	public static final String LUZCOMPENSATION_CONTEXT_PATH = "/luz_compensation";
	public static final String LUZFINANCE_CONTEXT_PATH = "/luzfin_finance";
	public static final String LUZ_ACCOUNTING_CONTEXT_PATH = "/luz_accounting";

	// --------Full path--------
	public static final String FULLPATH_LUZ_PERSON_COMPANY = Constants.LUZPERSON_CONTEXT_PATH + Constants.SLASH + Constants.COMPANY_RESOURCE + Constants.SLASH;
	public static final String FULLPATH_LUZ_COMPENSATION_COMPANY = Constants.LUZCOMPENSATION_CONTEXT_PATH + Constants.SLASH + Constants.COMPANY_RESOURCE + Constants.SLASH;

	// --------tenant id path parameter See
	// https://jira.axonivy.com/jira/browse/LUZ-3734
	public static final String COMPANY_TENANT_ID_NOTATION = "company-tenant-id";
	public static final String COMPANY_TENANT_ID_PATH_PARAMETER = "{" + COMPANY_TENANT_ID_NOTATION + "}";

	// --------Resource Name--------
	public static final String NUMBER_REGEX = "/[0-9]*";
	public static final String API_CONTEXT = "api";
	public static final String PERSON_CONTEXT = "/persons";
	public static final String COMPANY_CONTEXT = "/companies";
	public static final String WORKPLACE_CONTEXT = "/workplaces";
	public static final String EMPLOYEE_CONTEXT = "/employees";
	public static final String CONTRACT_CONTEXT = "/contracts";
	public static final String PAYSLIP_CONTEXT = "/payslips";
	public static final String SALARY_ITEM_CONTEXT = "/salary-items";
	public static final String STATE_CONTEXT = "/states";
	public static final String CITY_CONTEXT = "/cities";
	public static final String COUNTRY_CONTEXT = "/countries";
	public static final String REIMBURSEMENT_CONTEXT = "/reimbursement-requests";
	public static final String SALARY_RUN_BOOKING_CONTEXT = "/business-cases/trigger-booking-for-salary-run";
	public static final String SALARY_ITEM_TYPE_CONTEXT = "/salary-item-types";
	public static final String SALARY_ITEM_TYPE_GROUP_CONTEXT = "/salary-item-type-groups";
	public static final String SALARY_CONFIGURATION_CONTEXT = "/salary-configurations";
	public static final String FAK_BENEFICIARY_FOR_CONTEXT = "/fak-beneficiary-for";
	public static final String SWISS_BANK_CONTEXT = "/swiss-banks";
	public static final String SALARY_RUN_CONTEXT = "/salary-runs";

	public static final String PERSON_RESOURCE = API_CONTEXT + SLASH + COMPANY_TENANT_ID_PATH_PARAMETER + PERSON_CONTEXT;
	public static final String COUNTRY_RESOURCE = API_CONTEXT + COUNTRY_CONTEXT;
	public static final String COMPANY_RESOURCE = API_CONTEXT + SLASH + COMPANY_TENANT_ID_PATH_PARAMETER + COMPANY_CONTEXT;
	public static final String ADDRESSES_RESOURCE = "/addresses";
	public static final String STATE_RESOURCE = API_CONTEXT + STATE_CONTEXT;
	public static final String CITY_RESOURCE = API_CONTEXT + CITY_CONTEXT;
	public static final String SWISS_BANK_RESOURCE = API_CONTEXT + SWISS_BANK_CONTEXT;
	public static final String SALARY_ITEM_TYPE_RESOURCE = API_CONTEXT + SALARY_ITEM_TYPE_CONTEXT;
	public static final String SALARY_ITEM_TYPE_GROUP_RESOURCE = API_CONTEXT + SALARY_ITEM_TYPE_GROUP_CONTEXT;
	public static final String SALARY_CONFIGURATION_RESOURCE = API_CONTEXT + SALARY_CONFIGURATION_CONTEXT;
	public static final String FETCH_PERSONS_BY_URIS = "/fetch";
	
	// ------- Message check reference SIT - SITG - SC - Contract
	// ----------------------
	public static final String MSG_SIT_RELATED_CONTRACT = "The current SalaryItemType has related with a contract, couldn't delete.";
	public static final String MSG_SIT_RELATED_SITG = "The current SalaryItemType has related with a SalaryItemGroup, couldn't delete.";
	public static final String MSG_SIT_RELATED_SC = "The current SalaryItemType has related with a SalaryConfiguration, couldn't delete.";
	public static final String MSG_SITG_RELATED_SC = "The current SalaryItemTypeGroup has related with a SalaryConfiguration, couldn't delete.";
	public static final String MSG_SITG_RELATED_CONTRACT = "The current SalaryItemTypeGroup has related with a Contract, couldn't delete.";
	public static final String MSG_SC_RELATED_CONTRACT = "The current SalaryConfiguration has related with a Contract, couldn't delete.";

	public static final String VTO_MUST_GREATER_THAN_VFROM = "The valid to must greater than or equal the valid from";

	// --------JPA--------
	public static final String LUZ_COMPENSATION_PERSISTENCE_UNIT_NAME = "luzCompensationPU";
	public static final String LUZ_COMPENSATION_DATA_SOURCE = "java:jboss/datasources/luz_compensation";
	public static final String PRINCIPAL_DEFAULT_NAME = "SYSTEM";
	public static final Long DEFAULT_ID_OF_GLOBAL_VARIABLE = 1L;

	// --------Swagger-------
	public static final String GROUP_ID = "com.axonivy.luzcomp";
	public static final String ARTIFACT_ID = "luz_compensation";

	public static final String ADDRESS_TYPE_PRIVATE = "PRIVATE";
	// -------- Tax At Source --------
	public static final String THE_PATTERN_FOR_RECORD_TYPE = "([0-9]{2})([0-9]+{2})([A-Z]+{2})(.{10})([0-9]{8})([0-9]{9})([0-9]{9})(.{1})([0-9]{2})([0-9]{9})([0-9]{5})(.{3})*?";
	public static final String THE_PATTERN_FOR_FORWARD_RECORD = "([0-9]{2})([A-Z]+{2})(.{15})([0-9]{8})(.{40})(.{40})(.{3})?";
	public static final String THE_PATTERN_FOR_END_RECORD = "([0-9]{2})(.{15})([A-Z]{2})([0-9]{8})(.{9})(.{3})?";

	public static final String CONTENT_TYPE_TXT = "text/plain";
	public static final String[] CONTENT_TYPE_ZIPS = new String[] { "application/x-zip-compressed", "application/zip" };

	// ------------URI----------------
	public static final String DEFAULT_ID_PATTERN = "{id}";
	public static final String COMPANY_ID_PATTERN = "{companyId}";
	public static final String PERSON_ID_PATTERN = "{personId}";
	public static final String STATE_ID_PATTERN = "{stateId}";
	public static final String EMPLOYEE_ID_PATTERN = "{employeeId}";
	public static final String WORKPLACE_ID_PATTERN = "{workplaceId}";
	public static final String CONTRACT_ID_PATTERN = "{contractId}";
	public static final String PAYSLIPS_ID_PATTERN = "{payslipId}";
	public static final String SALARY_ITEM_ID_PATTERN = "{salaryItemId}";
	public static final String FAK_BENEFICIARY_FOR_ID_PATTERN = "{fakBeneficiaryForId}";
	public static final String SALARY_CONFIGURATION_ID_PATTERN = "{salaryConfigurationId}";
	public static final String SALARY_RUN_ID_PATTERN = "{salaryRunId}";
	
	public static final String COMPANY_URI = LUZCOMPENSATION_CONTEXT_PATH + SLASH + COMPANY_RESOURCE + SLASH + COMPANY_ID_PATTERN;
	public static final String PERSON_URI = LUZPERSON_CONTEXT_PATH + SLASH + PERSON_RESOURCE + SLASH + PERSON_ID_PATTERN;
	public static final String STATE_URI = LUZPERSON_CONTEXT_PATH + SLASH + STATE_RESOURCE + SLASH + STATE_ID_PATTERN;
	public static final String EMPLOYEE_URI = COMPANY_URI + EMPLOYEE_CONTEXT + SLASH + EMPLOYEE_ID_PATTERN;

	public static final String WORKPLACE_URI = COMPANY_URI + WORKPLACE_CONTEXT + SLASH + WORKPLACE_ID_PATTERN;

	public static final String FAK_BENEFICIARY_FOR_URI = COMPANY_URI 
			+ EMPLOYEE_CONTEXT + SLASH + EMPLOYEE_ID_PATTERN
			+ FAK_BENEFICIARY_FOR_CONTEXT + SLASH + FAK_BENEFICIARY_FOR_ID_PATTERN;

	public static final String CONTRACT_URI = COMPANY_URI + CONTRACT_CONTEXT + SLASH + CONTRACT_ID_PATTERN;
	public static final String PAYSLIP_URI = CONTRACT_URI + PAYSLIP_CONTEXT + SLASH + PAYSLIPS_ID_PATTERN;
	public static final String SALARY_ITEM_URI = PAYSLIP_URI + SALARY_ITEM_CONTEXT + SLASH + SALARY_ITEM_ID_PATTERN;
	public static final String SALARY_ITEM_TYPE_URI = LUZCOMPENSATION_CONTEXT_PATH + SLASH + SALARY_ITEM_TYPE_RESOURCE + SLASH + DEFAULT_ID_PATTERN;
	public static final String SALARY_ITEM_TYPE_GROUP_URI = LUZCOMPENSATION_CONTEXT_PATH + SLASH + SALARY_ITEM_TYPE_GROUP_RESOURCE + SLASH + DEFAULT_ID_PATTERN;
	public static final String SALARY_CONFIGURATION_URI = LUZCOMPENSATION_CONTEXT_PATH + SLASH + SALARY_CONFIGURATION_RESOURCE + SLASH + DEFAULT_ID_PATTERN;

	public static final String SALARY_CONFIGURATION_TEMPORAL_URI = COMPANY_URI 
			+ CONTRACT_CONTEXT + SLASH + CONTRACT_ID_PATTERN 
			+ SALARY_CONFIGURATION_CONTEXT + SALARY_CONFIGURATION_ID_PATTERN;
	public static final String SALARY_RUN_URI = COMPANY_URI + SALARY_RUN_CONTEXT + SLASH + SALARY_RUN_ID_PATTERN;

	public static final String SALARY_RUN_BOOKING_RESOURCE = LUZ_ACCOUNTING_CONTEXT_PATH + SLASH + COMPANY_RESOURCE + SLASH + COMPANY_ID_PATTERN + SALARY_RUN_BOOKING_CONTEXT;
	public static final String REIMBURSEMENT_RESOURCE = LUZ_ACCOUNTING_CONTEXT_PATH + SLASH + COMPANY_RESOURCE + SLASH + COMPANY_ID_PATTERN + REIMBURSEMENT_CONTEXT;
	
	//luz_accounting
	public static final String ACCOUNTING_CONFIGURATION_CONTEXT = "/accounting-configurations";
	public static final String ACCOUNTING_CONFIGURATION_RESOURCE = LUZ_ACCOUNTING_CONTEXT_PATH + SLASH + COMPANY_RESOURCE + SLASH + COMPANY_ID_PATTERN + ACCOUNTING_CONFIGURATION_CONTEXT;
	
	//URI luzfin_finance
	public static final String COMPANY_VAT_CONTEXT = "/company-vats";
	public static final String COMPANY_VAT_RESOUCRCE = LUZFINANCE_CONTEXT_PATH + SLASH + COMPANY_RESOURCE + SLASH + COMPANY_ID_PATTERN + COMPANY_VAT_CONTEXT;
	
	// ------------URI_PATTERN------------
	public static final String TENANT_ID_PATTERN = "([a-z0-9_-]*)";
	public static final String COMPANY_BASE_URI_PATTERN = API_CONTEXT + SLASH + TENANT_ID_PATTERN + COMPANY_CONTEXT;
	public static final String PERSON_BASE_URI_PATTERN = API_CONTEXT + SLASH + TENANT_ID_PATTERN + PERSON_CONTEXT;

	public static final String COMPANY_URI_PATTERN = LUZCOMPENSATION_CONTEXT_PATH + SLASH + COMPANY_BASE_URI_PATTERN + NUMBER_REGEX;
	public static final String PERSON_COMPANY_URI_PATTERN = LUZCOMPENSATION_CONTEXT_PATH + SLASH + COMPANY_BASE_URI_PATTERN + NUMBER_REGEX;

	public static final String WORKPLACE_URI_PATTERN = COMPANY_URI_PATTERN + WORKPLACE_CONTEXT + NUMBER_REGEX;

	public static final String EMPLOYEE_URI_PATTERN = COMPANY_URI_PATTERN + EMPLOYEE_CONTEXT + NUMBER_REGEX;

	public static final String FAK_BENEFICIARY_FOR_URI_PATTERN = COMPANY_URI_PATTERN + EMPLOYEE_CONTEXT + NUMBER_REGEX
			+ FAK_BENEFICIARY_FOR_CONTEXT + NUMBER_REGEX;

	public static final String CONTRACT_URI_PATTERN = COMPANY_URI_PATTERN + CONTRACT_CONTEXT + NUMBER_REGEX;
	public static final String PAYSLIP_URI_PATTERN = CONTRACT_URI_PATTERN + PAYSLIP_CONTEXT + NUMBER_REGEX;

	public static final String SALARY_ITEM_TYPE_PATTERN = LUZCOMPENSATION_CONTEXT_PATH + SLASH + SALARY_ITEM_TYPE_RESOURCE + NUMBER_REGEX;
	public static final String SALARY_ITEM_TYPE_GROUP_PATTERN = LUZCOMPENSATION_CONTEXT_PATH + SLASH + SALARY_ITEM_TYPE_GROUP_RESOURCE + NUMBER_REGEX;
	public static final String SALARY_CONFIGURATION_PATTERN = LUZCOMPENSATION_CONTEXT_PATH + SLASH + SALARY_CONFIGURATION_RESOURCE + NUMBER_REGEX;

	public static final String SALARY_CONFIGURATION_TEMPORAL_PATTERN = COMPANY_URI_PATTERN 
			+ CONTRACT_CONTEXT + NUMBER_REGEX 
			+ SALARY_CONFIGURATION_CONTEXT + NUMBER_REGEX;

	public static final String ELM_REPORT_FOR_SWISSDEC_STRING = "com_axonivy_luz_compensation_elmReportForSwissdecFlag";
	public static final String INSURANCE_CONTRACT_URI = COMPANY_URI + INSURANCE_CONTRACTS_CONTEXT + SLASH + INSURANCE_CONTRACT_ID;

	// ------------- REFERENCE INFO -------------
	public static final String COMPENSATION_MODULE = "luz_compensation";
	public static final String EMPLOYEES_REFERENCE_TYPE = "employees";
	public static final String CHILDREN_REFERENCE_TYPE = "children";
	public static final String COMPANIES_REFERENCE_TYPE = "companies";
	public static final String REFERENCE_CONTEXT_PATH = "references";
	
	public static final String getCompanyExceptionMessageWithId(Long companyId) {
		return Constants.COMPANY_NOT_FOUND_WITH_ID.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL");
	}
	
	public static final String getCompanyExceptionMessageWithUri(String companyUri) {
		return Constants.COMPANY_NOT_FOUND_WITH_URI.replace("COMPANY_URI", companyUri != null ? companyUri : "NULL");
	}
	
	public static final String getInsuranceContractExceptionMessageWith(Long companyId, Long insurContractId) {
		return Constants.INSURANCE_CONTRACT_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace("INSURANCE_CONTRACT_ID", insurContractId != null ? insurContractId.toString() : "NULL");
	}

	public static final String getInsurerExceptionMessageWith(Long companyId, Long insurerId) {
		return Constants.INSURER_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace("INSURER_ID", insurerId != null ? insurerId.toString() : "NULL");
	}
	
	public static final String getEmployeeExceptionMessageWith(Long companyId, Long employeeId) {
		return Constants.EMPLOYEE_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace("EMPLOYEE_ID", employeeId != null ? employeeId.toString() : "NULL");
	}
	
	public static final String getContractExceptionMessageWith(Long companyId, Long contractId) {
		return Constants.CONTRACT_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL");
	}
	
	public static final String getPayslipExceptionMessageWith(Long companyId, Long contractId, Long payslipId) {
		return Constants.PAYSLIP_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("PAYSLIP_ID", payslipId != null ? payslipId.toString() : "NULL");
	}
	
	public static final String getSalaryItemExceptionMessageWith(Long companyId, Long contractId, Long payslipId, Long salaryItemId) {
		return Constants.SALARY_ITEM_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("PAYSLIP_ID", payslipId != null ? payslipId.toString() : "NULL")
						.replace("SALARY_ITEM_ID", payslipId != null ? salaryItemId.toString() : "NULL");
	}
	
	
	public static final String getPayslipExceptionMessageWithMonth(Long companyId, Long contractId, LocalDate periodFrom) {
		return Constants.PAYSLIP_NOT_FOUND_WITH_MONTH_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("PAYSLIP_MONTH", periodFrom != null ? YearMonth.from(periodFrom).toString() : "NULL");
	}
	
	public static final String getPayslipExceptionMessageWithUri(Long companyId, Long contractId, String payslipUri) {
		return Constants.PAYSLIP_NOT_FOUND_WITH_URI_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("PAYSLIP_URI", payslipUri != null ? payslipUri : "NULL");
	}
	
	public static final String getWorkplaceExceptionMessageWith(Long companyId, Long workplaceId) {
		return Constants.WORKPLACE_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace("WORKPLACE_ID", workplaceId != null ? workplaceId.toString() : "NULL");
	}
	
	public static final String getContractTemporalExceptionMessageWith(Long companyId, Long contractId, Long contractTemporalId) {
		return Constants.CONTRACT_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("CONTRACT_TEMPORAL_ID", contractTemporalId != null ? contractTemporalId.toString() : "NULL");
	}
	
	public static final String getSalaryConfigurationTemporalExceptionMessageWith(Long companyId, Long contractId, Long scTemporalId) {
		return Constants.SALARY_CONFIGURATION_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("SCTEMPORAL_ID", scTemporalId != null ? scTemporalId.toString() : "NULL");
	}
	
	public static final String getSalaryItemTypeGroupTemporalExceptionMessageWith(Long companyId, Long contractId, Long sitgTemporalId) {
		return Constants.SALARY_ITEM_TYPE_GROUP_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("SITGTEMPORAL_ID", sitgTemporalId != null ? sitgTemporalId.toString() : "NULL");
	}
	
	public static final String getSalaryItemTypeTemporalExceptionMessageWith(Long companyId, Long contractId, Long sitTemporalId) {
		return Constants.SALARY_ITEM_TYPE_TEMPORAL_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("SITTEMPORAL_ID", sitTemporalId != null ? sitTemporalId.toString() : "NULL");
	}
	
	public static final String getFAKAllocationExceptionMessageWith(Long companyId, Long contractId, Long fakAllocationId) {
		return Constants.FAK_ALLOCATION_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("FAKALLOCATION_ID", fakAllocationId != null ? fakAllocationId.toString() : "NULL");
	}
	
	public static final String getThirdPartyReceiverExceptionMessageWith(Long companyId, Long contractId, Long thirdPartyId) {
		return Constants.THIRD_PARTY_RECEIVER_NOT_FOUND_WITH_ID_AND_PARENTS_ID
						.replace(COMPANY_ID, companyId != null ? companyId.toString() : "NULL")
						.replace(CONTRACT_ID, contractId != null ? contractId.toString() : "NULL")
						.replace("THIRD_PARTY_RECEIVER_ID", thirdPartyId != null ? thirdPartyId.toString() : "NULL");
	}
}
