package com.kennedy.java.anotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.beanutils.BeanUtils;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodValidator.DateValidation.class)
public @interface PeriodValidator {

	String message() default "{constraints.periodValidator}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String fromFieldName();
	String toFieldName();
	int acceptedBeforeNumberOfMonths() default 0;
	boolean isSameYear() default false;
	boolean isValidatedForYearMonthOnly() default false;

	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		PeriodValidator[] value();
	}

	public class DateValidation implements ConstraintValidator<PeriodValidator, Object> {
		private static final String DATE_TIME_FORMAT_YYYY_MM_DD_LINE = "yyyy-MM-dd";

		private String fromFieldName;
		private String toFieldName;
		private int acceptedBeforeNumberOfMonths;
		private boolean isValidatedForYearMonthOnly;
		private boolean isSameYear;
		
		@Override
		public void initialize(final PeriodValidator objectToValidate) {
			this.fromFieldName = objectToValidate.fromFieldName();
			this.toFieldName = objectToValidate.toFieldName();
			this.acceptedBeforeNumberOfMonths = objectToValidate.acceptedBeforeNumberOfMonths();
			this.isValidatedForYearMonthOnly = objectToValidate.isValidatedForYearMonthOnly();
			this.isSameYear = objectToValidate.isSameYear();
		}

		@Override
		public boolean isValid(final Object t, final ConstraintValidatorContext context) {
			final String firstDateObject;
			final String secondDateObject;
			try {
				firstDateObject = BeanUtils.getProperty(t, this.fromFieldName);
				secondDateObject = BeanUtils.getProperty(t, this.toFieldName);
			} catch (final Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}

			// leave null-checking to @NotNull on individual parameters
			if (firstDateObject == null || secondDateObject == null) {
				return true;
			}
			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_YYYY_MM_DD_LINE);
			final LocalDate fromDate = LocalDate.parse(firstDateObject, dtf);
			final LocalDate toDate = LocalDate.parse(secondDateObject, dtf);
			final int months = acceptedBeforeNumberOfMonths;
			if (isSameYear) {
				return fromDate.getYear() == toDate.getYear();
			}
			if (isValidatedForYearMonthOnly) {
				return isValidWithMonthOnly(fromDate, toDate, months);
			}
			if (toDate.isBefore(fromDate) || isOutOfRange(fromDate, toDate, months)) {
				return false;
			}
			return true;
		}

		private boolean isValidWithMonthOnly(LocalDate fromDate, LocalDate toDate, int months) {
			YearMonth from = YearMonth.from(fromDate);
			YearMonth to = YearMonth.from(toDate);
			return from.isBefore(to) || (months != 0 && from.plusMonths(months).isBefore(to));
		}

		private boolean isOutOfRange(final LocalDate fromDate, final LocalDate toDate, final int months) {
			return months != 0 && fromDate.plusMonths(months).isBefore(toDate);
		}
	}
}
