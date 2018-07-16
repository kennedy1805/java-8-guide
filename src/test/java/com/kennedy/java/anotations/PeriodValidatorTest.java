package com.kennedy.java.anotations;

import java.io.Serializable;
import java.time.LocalDate;

import static org.hamcrest.Matchers.startsWith;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PeriodValidatorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	// when I input wrong fromFieldName (let's say entryDate to id) then I
	// expect javax.validation.ValidationException will be thrown. This
	// Exception happen due to the wrong usage of developer
	@Test
	public void contractModelSample_wrongFieldName() throws ValidationException {
		ContractWrongUsingPeriodValidatorModel contractModel = new ContractWrongUsingPeriodValidatorModel();
		contractModel.set_employeeUri("abc/xyz");
		contractModel.setId(1L);
		contractModel.setEntryDate(LocalDate.of(2013, 1, 1));
		contractModel.setExitDate(LocalDate.of(2014, 1, 1));
		expectedException.expect(javax.validation.ValidationException.class);
		expectedException.expectMessage("Unexpected exception during isValid call");
		ValidatorHelper.validate(contractModel);
	}

	@PeriodValidator(fromFieldName = "id", toFieldName = "exitDate", message = "Exit date must happen after entry date")
	public class ContractWrongUsingPeriodValidatorModel implements Serializable {
		private static final long serialVersionUID = 1L;
		public Long id;
		public String _employeeUri;
		@NotNull(message = "entryDate cannot be null")
		public LocalDate entryDate;
		public LocalDate exitDate;
		public ContractWrongUsingPeriodValidatorModel() {
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String get_employeeUri() {
			return _employeeUri;
		}
		public void set_employeeUri(String _employeeUri) {
			this._employeeUri = _employeeUri;
		}
		public LocalDate getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(LocalDate entryDate) {
			this.entryDate = entryDate;
		}
		public LocalDate getExitDate() {
			return exitDate;
		}
		public void setExitDate(LocalDate exitDate) {
			this.exitDate = exitDate;
		}
	}

	@Test
	public void contractModelSample_validateAcceptedMonthRange() throws ValidationException {
		ContractWithAcceptedMonthRanged contractModel = new ContractWithAcceptedMonthRanged();
		contractModel.setDateFrom(LocalDate.of(2013, 1, 1));
		contractModel.setDateTo(LocalDate.of(2013, 12, 2));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from within 11 months");
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ContractWithAcceptedMonthRanged();
		contractModel.setDateFrom(LocalDate.of(2013, 1, 1));
		contractModel.setDateTo(LocalDate.of(2013, 12, 1));
		ValidatorHelper.validate(contractModel);
	}
	
	@PeriodValidator(fromFieldName = "dateFrom", toFieldName = "dateTo", acceptedBeforeNumberOfMonths = 11, message = "Date to must happen after date from within 11 months")
	public class ContractWithAcceptedMonthRanged implements Serializable {
		private static final long serialVersionUID = 1L;
		@NotNull(message = "entryDate cannot be null")
		private LocalDate dateFrom;
		private LocalDate dateTo;
		public LocalDate getDateFrom() {
			return dateFrom;
		}
		public void setDateFrom(LocalDate dateFrom) {
			this.dateFrom = dateFrom;
		}
		public LocalDate getDateTo() {
			return dateTo;
		}
		public void setDateTo(LocalDate dateTo) {
			this.dateTo = dateTo;
		}
		public ContractWithAcceptedMonthRanged() {
		}
	}
	
	@Test
	public void normalCase_DoNotInputAcceptedBeforeNumberOfMonths_DateFromIsAfterDateTo() throws ValidationException {
		NormalObjectValidator contractModel = new NormalObjectValidator();
		contractModel.setDateFrom(LocalDate.of(2013, 1, 1));
		contractModel.setDateTo(LocalDate.of(2012, 1, 1));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from");
		ValidatorHelper.validate(contractModel);
	}
	
	@Test
	public void normalCase_DoNotInputAcceptedBeforeNumberOfMonths_DateFromIsBeforeDateTo() throws ValidationException {
		NormalObjectValidator contractModel = new NormalObjectValidator();
		contractModel.setDateFrom(LocalDate.of(2013, 1, 1));
		contractModel.setDateTo(LocalDate.of(2014, 1, 1));
		ValidatorHelper.validate(contractModel);
		
		contractModel = new NormalObjectValidator();
		contractModel.setDateFrom(LocalDate.of(2013, 1, 1));
		contractModel.setDateTo(LocalDate.of(2015, 1, 1));
		ValidatorHelper.validate(contractModel);
		
		contractModel = new NormalObjectValidator();
		contractModel.setDateFrom(LocalDate.of(2013, 1, 1));
		contractModel.setDateTo(LocalDate.of(9999, 1, 1));
		ValidatorHelper.validate(contractModel);
	}
	
	@PeriodValidator(fromFieldName = "dateFrom", toFieldName = "dateTo", message = "Date to must happen after date from")
	public class NormalObjectValidator implements Serializable {
		private static final long serialVersionUID = 1L;
		@NotNull(message = "entryDate cannot be null")
		private LocalDate dateFrom;
		private LocalDate dateTo;
		public LocalDate getDateFrom() {
			return dateFrom;
		}
		public void setDateFrom(LocalDate dateFrom) {
			this.dateFrom = dateFrom;
		}
		public LocalDate getDateTo() {
			return dateTo;
		}
		public void setDateTo(LocalDate dateTo) {
			this.dateTo = dateTo;
		}
		public NormalObjectValidator() {
		}
	}
	
	@Test
	public void getException_WithRange_20Months_DoNotInputAcceptedBeforeNumberOfMonths_DateFromIsBeforeDateTo() throws ValidationException {
		ObjectWithAcceptedMonthRangeIs20 contractModel = new ObjectWithAcceptedMonthRangeIs20();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 1, 1));
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectWithAcceptedMonthRangeIs20();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 9, 1));
		ValidatorHelper.validate(contractModel);
	}
	
	@Test
	public void getException_OverPoint_20Months() throws ValidationException {
		ObjectWithAcceptedMonthRangeIs20 contractModel = new ObjectWithAcceptedMonthRangeIs20();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 9, 2));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from within 20 months");
		ValidatorHelper.validate(contractModel);
	}
	
	@PeriodValidator(fromFieldName = "dateFrom", toFieldName = "dateTo", acceptedBeforeNumberOfMonths = 20, message = "Date to must happen after date from within 20 months")
	public class ObjectWithAcceptedMonthRangeIs20 implements Serializable {
		private static final long serialVersionUID = 1L;
		@NotNull(message = "entryDate cannot be null")
		private LocalDate dateFrom;
		private LocalDate dateTo;
		public LocalDate getDateFrom() {
			return dateFrom;
		}
		public void setDateFrom(LocalDate dateFrom) {
			this.dateFrom = dateFrom;
		}
		public LocalDate getDateTo() {
			return dateTo;
		}
		public void setDateTo(LocalDate dateTo) {
			this.dateTo = dateTo;
		}
		public ObjectWithAcceptedMonthRangeIs20() {
		}
	}
	
	@Test
	public void testInput_0_ToAcceptedMonths() throws ValidationException {
		ObjectWithAcceptedMonthRangeIs0 contractModel = new ObjectWithAcceptedMonthRangeIs0();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(1999, 12, 31));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from");
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectWithAcceptedMonthRangeIs0();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 1, 1));
		ValidatorHelper.validate(contractModel);
	}
	
	@PeriodValidator(fromFieldName = "dateFrom", toFieldName = "dateTo", acceptedBeforeNumberOfMonths = 0, message = "Date to must happen after date from")
	public class ObjectWithAcceptedMonthRangeIs0 implements Serializable {
		private static final long serialVersionUID = 1L;
		@NotNull(message = "entryDate cannot be null")
		private LocalDate dateFrom;
		private LocalDate dateTo;
		public LocalDate getDateFrom() {
			return dateFrom;
		}
		public void setDateFrom(LocalDate dateFrom) {
			this.dateFrom = dateFrom;
		}
		public LocalDate getDateTo() {
			return dateTo;
		}
		public void setDateTo(LocalDate dateTo) {
			this.dateTo = dateTo;
		}
		public ObjectWithAcceptedMonthRangeIs0() {
		}
	}
	
	@Test
	public void testValidateForMonthYearOnly() throws ValidationException {
		ObjectValidateMonthOnly contractModel = new ObjectValidateMonthOnly();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2000, 12, 01));
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectValidateMonthOnly();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2000, 12, 31));
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectValidateMonthOnly();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(1999, 12, 31));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from");
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectValidateMonthOnly();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 1, 1));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from");
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectValidateMonthOnly();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 1, 31));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from");
		ValidatorHelper.validate(contractModel);
	}
	
	@PeriodValidator(fromFieldName = "dateFrom", toFieldName = "dateTo", acceptedBeforeNumberOfMonths = 11, isValidatedForYearMonthOnly = true, message = "Date to must happen after date from")
	public class ObjectValidateMonthOnly implements Serializable {
		private static final long serialVersionUID = 1L;
		@NotNull(message = "entryDate cannot be null")
		private LocalDate dateFrom;
		private LocalDate dateTo;
		public LocalDate getDateFrom() {
			return dateFrom;
		}
		public void setDateFrom(LocalDate dateFrom) {
			this.dateFrom = dateFrom;
		}
		public LocalDate getDateTo() {
			return dateTo;
		}
		public void setDateTo(LocalDate dateTo) {
			this.dateTo = dateTo;
		}
		public ObjectValidateMonthOnly() {
		}
	}
	
	@Test
	public void testValidateIsSameYear() throws ValidationException {
		ObjectValidateIsSameYear contractModel = new ObjectValidateIsSameYear();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2000, 12, 01));
		ValidatorHelper.validate(contractModel);
		
		contractModel = new ObjectValidateIsSameYear();
		contractModel.setDateFrom(LocalDate.of(2000, 1, 1));
		contractModel.setDateTo(LocalDate.of(2001, 1, 1));
		expectedException.expect(ValidationException.class);
		expectedException.expectMessage("Date to must happen after date from and must be same year");
		ValidatorHelper.validate(contractModel);
	}
	
	@PeriodValidator(fromFieldName = "dateFrom", toFieldName = "dateTo", isSameYear = true, message = "Date to must happen after date from and must be same year")
	public class ObjectValidateIsSameYear implements Serializable {
		private static final long serialVersionUID = 1L;
		@NotNull(message = "entryDate cannot be null")
		private LocalDate dateFrom;
		private LocalDate dateTo;
		public LocalDate getDateFrom() {
			return dateFrom;
		}
		public void setDateFrom(LocalDate dateFrom) {
			this.dateFrom = dateFrom;
		}
		public LocalDate getDateTo() {
			return dateTo;
		}
		public void setDateTo(LocalDate dateTo) {
			this.dateTo = dateTo;
		}
		public ObjectValidateIsSameYear() {
		}
	}
}
