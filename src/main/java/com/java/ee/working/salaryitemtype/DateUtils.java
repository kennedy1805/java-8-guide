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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.ValidationException;

public class DateUtils {

	/**
	 * 
	 * Check if one day is within two others day. To make sure this method works
	 * well. We must make sure in the database, we have no overlap when talking
	 * about validity.
	 *
	 * @param dateToBeChecked
	 * @param validFrom
	 * @param validTo
	 * @return true if the date to be checked is in two others day, false if the
	 *         date to be checked is out of two others day
	 *         
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static boolean isDateWithin(LocalDate dateToBeChecked, LocalDate validFrom, LocalDate validTo) {
		boolean result = false;
		if (dateToBeChecked == null) {
			throw new ValidationException("Date used to check can not be null!");
		}
		if (validFrom == null) {
			validFrom = LocalDate.MIN;
		}
		if (validTo == null) {
			validTo = LocalDate.MAX;
		}
		if ((dateToBeChecked.isEqual(validFrom) || dateToBeChecked.isAfter(validFrom))
				&& dateToBeChecked.isBefore(validTo)) {
			result = true;
		}
		return result;
	}
	
	public static boolean isRangeDateWithin(LocalDate fromDateChecked, LocalDate toDateChecked, LocalDate validFrom, LocalDate validTo) {
		boolean result = false;
		if(fromDateChecked == null || toDateChecked == null || validFrom == null || validTo == null) {
			return result;
		}
		
		if(validFrom.compareTo(fromDateChecked) <= 0 && toDateChecked.compareTo(validTo) <= 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Convert String with date time and zone '2011-12-03T10:15:30Z' information to LocalDate.
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static LocalDate zonedStringToLocalDate(String str) {
		ZonedDateTime dateTimeWithZone = ZonedDateTime.parse(str);
        dateTimeWithZone = dateTimeWithZone.withZoneSameInstant(ZoneId.of(Constants.UTF_TIMEZONE_STRING));
		return LocalDate.from(dateTimeWithZone);
	}
	
	/**
	 * Convert String with date time and zone '2011-12-03T10:15:30Z' information to LocalDateTime.
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static LocalDateTime zonedStringToLocalDateTime(String str) {
		ZonedDateTime dateTimeWithZone = ZonedDateTime.parse(str);
        dateTimeWithZone = dateTimeWithZone.withZoneSameInstant(ZoneId.of(Constants.UTF_TIMEZONE_STRING));
		return LocalDateTime.from(dateTimeWithZone);
	}
	
	/**
	 * Convert String with time format to LocalDateTime with specific DateTimeFormatter
	 */
	public static LocalDateTime timeStringToLocalDate(String str, DateTimeFormatter dateTimeFormatter) {
		if (dateTimeFormatter == null) {
			return timeStringToLocalDate(str);
		}
		return LocalDateTime.parse(str, dateTimeFormatter);
	}
	
	/**
	 * Convert String format '2011-12-03T10:15:30' to corresponding LocalDateTime
	 */
	public static LocalDateTime timeStringToLocalDate(String str) {
		return LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
	
	/**
	 * Convert String format to LocalDate with specific DateTimeFormatter
	 */
	public static LocalDate stringToLocalDate(String str, DateTimeFormatter dateTimeFormatter) {
		if (dateTimeFormatter == null) {
			return stringToLocalDate(str);
		}
		return LocalDate.parse(str, dateTimeFormatter);
	}
	
	/**
	 * Convert String format '2011-12-03' to LocalDate
	 */
	public static LocalDate stringToLocalDate(String str) {
		return LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	/**
	 * Convert LocalDate to common output string with a specific DateTimeFormatter <br/>
	 * If DateTimeFormatter is null then using default DateTimeFormatter.ISO_ZONED_DATE_TIME to format the output String
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static String localDateToOutputString(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
		if (localDate == null) {
			return null;
		}
		LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
    	ZonedDateTime dateTimeWithZone = localDateTime.atZone(ZoneId.of(Constants.UTF_TIMEZONE_STRING));
    	return (dateTimeFormatter == null) ? dateTimeWithZone.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) : dateTimeWithZone.format(dateTimeFormatter);
	}
	
	/**
	 * Convert LocalDate to common output string with DateTimeFormatter.ISO_ZONED_DATE_TIME to format the output String
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static String localDateToOutputString(LocalDate localDate) {
		return localDateToOutputString(localDate, null);
	}
	
	/**
	 * Convert LocalDateTime to common output string <br/>
	 * If DateTimeFormatter is null then using default DateTimeFormatter.ISO_ZONED_DATE_TIME to format the output String
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static String localDateTimeToOutputString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
		if (localDateTime == null) {
			return null;
		}
		LocalDateTime localDateTimeWithoutNanoSecond = localDateTime.withNano(0);
    	ZonedDateTime dateTimeWithZone = localDateTimeWithoutNanoSecond.atZone(ZoneId.of(Constants.UTF_TIMEZONE_STRING));
    	return (dateTimeFormatter == null) ? dateTimeWithZone.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) : dateTimeWithZone.format(dateTimeFormatter);
	}
	
	/**
	 * Convert LocalDateTime to common output string with DateTimeFormatter.ISO_ZONED_DATE_TIME to format the output String
	 * @deprecated moved to com.axonivy.common.util.DateUtils
	 */
	@Deprecated
	public static String localDateTimeToOutputString(LocalDateTime localDateTime) {
    	return localDateTimeToOutputString(localDateTime, null);
	}
	
	public static LocalDate getFirstDayOfYear(Year year) {
		LocalDate firstDay = LocalDate.ofYearDay(year.getValue(), 1);
		
		return firstDay;
	}
	
	public static LocalDate getLastDayOfYear(Year year) {
		LocalDate lastDay = LocalDate.ofYearDay(year.getValue() + 1, 1).minusDays(1);
		
		return lastDay;
	}
	
	public static LocalDate getLastDayOfNextMonth(LocalDate date) {
		return LocalDate.of(date.getYear(), date.getMonth(), 1).plusMonths(2).minusDays(1);
	}
	
	public static boolean isCoverFullMonth(LocalDate validFrom, LocalDate validTo, YearMonth month) {
		LocalDate firstDateOfMonth = LocalDate.of(month.getYear(), month.getMonthValue(), 1);
		LocalDate lastDateOfMonth = firstDateOfMonth.plusMonths(1);
		if (validFrom == null) {
			validFrom = LocalDate.MIN;
		}
		if (validTo == null) {
			validTo = LocalDate.MAX;
		}
		return (validFrom.isBefore(firstDateOfMonth) || validFrom.equals(firstDateOfMonth)) 
				&& (validTo.isAfter(lastDateOfMonth) || validTo.equals(lastDateOfMonth));
	}
	
	public static boolean isMonthWithin(YearMonth calculating, LocalDate entryDate, LocalDate exitDate) {
		YearMonth from = YearMonth.from(entryDate == null ? LocalDate.MIN : entryDate);
		YearMonth to = YearMonth.from(exitDate == null ? LocalDate.MAX : exitDate);
		return isMonthWithin(calculating, from, to);
	}
	
	public static boolean isMonthWithin(YearMonth calculating, YearMonth from, YearMonth to) {
		return (from.isBefore(calculating) || from.equals(calculating)) && (calculating.equals(to) || calculating.isBefore(to));
	}
	
	public static boolean isMonthWithinValidFromAndValidTo(YearMonth calculating, LocalDate validFrom, LocalDate validTo) {
		YearMonth from = YearMonth.from(validFrom == null ? LocalDate.MIN : validFrom);
		LocalDate validToDate = validTo != null ? validTo.minusDays(1L) : null;
		YearMonth to = YearMonth.from(validToDate == null ? LocalDate.MAX : validToDate);
		return (from.isBefore(calculating) || from.equals(calculating)) && (calculating.equals(to) || calculating.isBefore(to));
	}
	
	/*public static boolean isMonthWithinValidFromAndInclusiveValidTo(YearMonth calculating, LocalDate validFrom, LocalDate validTo) {
		YearMonth from = YearMonth.from(validFrom == null ? LocalDate.MIN : validFrom);
		YearMonth to = YearMonth.from(validToDate == null ? LocalDate.MAX : validToDate);
		return (from.isBefore(calculating) || from.equals(calculating)) && (calculating.equals(to) || calculating.isBefore(to));
	}*/

	//Implement like isDateWithin but for months
	public static boolean isDateWithinForMonth(YearMonth dateToBeChecked, YearMonth validFrom, YearMonth validTo) {
		boolean result = false;
		if (dateToBeChecked == null) {
			throw new ValidationException("Date used to check can not be null!");
		}
		if (validFrom == null) {
			validFrom = YearMonth.from(LocalDate.MIN);
		}
		if (validTo == null) {
			validTo = YearMonth.from(LocalDate.MAX);
		}
		if ((dateToBeChecked.equals(validFrom) || dateToBeChecked.isAfter(validFrom))
				&& dateToBeChecked.isBefore(validTo)) {
			result = true;
		}
		return result;
	}
	
	public static int ageOn(LocalDate birthday, LocalDate ondate){
		return (int)ChronoUnit.YEARS.between(birthday.withDayOfMonth(birthday.lengthOfMonth()), ondate.withDayOfMonth(1));
	}
	
	public static LocalDate stringToLocalDateMultipleFormatters(String string) {
		List<DateTimeFormatter> formatters = Arrays.asList(
				DateTimeFormatter.ISO_LOCAL_DATE,
				DateTimeFormatter.ISO_LOCAL_DATE_TIME,
				DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault()));
		if (string != null) {
			for (DateTimeFormatter formatter : formatters ) {
		        try {
		        	return LocalDate.parse(string, formatter);
		        } catch (DateTimeParseException exp) {
		            
		        }
		    }
			throw new DateTimeParseException("Date input just support: ISO_LOCAL_DATE (2011-12-31) | ISO_LOCAL_DATE_TIME (2011-12-31T00:00:00) | ISO_INSTANT (2011-12-31T00:00:00Z)", string, 0);
		} else {
			return null;
		}
	}
	
    public static LocalDate stringYearMonthToLocalDateWithFirstDayOfMonth(String monthYearString) {
		Objects.requireNonNull(monthYearString, "The monthYear input cannot be null");
		YearMonth yearMonth = YearMonth.parse(monthYearString, DateTimeFormatter.ofPattern("MM.yyyy"));
		LocalDate monthToAddReimbursement = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		return monthToAddReimbursement;
	}
    
    public static LocalDate localDateToLocalDateMonthYearOnly(LocalDate localDateInput) {
    	return LocalDate.of(localDateInput.getYear(), localDateInput.getMonthValue(), 1);
    }
}
