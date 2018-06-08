package com.kennedy.java.core.eight;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;

import org.junit.Test;

import com.kennedy.java.core.eight.Range;


public class RangeTest {

	@Test
	public void rangeTest_caseInteger() {
		Range<Integer> range = Range.of(13, 100);
		assertThat(range.contains(5), is(false));
		assertThat(range.contains(22), is(true));
		
		Range<Integer> open = Range.open(5, 7);
		assertThat(open.contains(5), is(false));

		Range<Integer> closed = Range.closed(5, 7);
		assertThat(closed.contains(5), is(true));

		Range<Integer> openClosed = Range.openClosed(5, 7);
		assertThat(openClosed.contains(5), is(false));
		assertThat(openClosed.contains(7), is(true));

		Range<Integer> closedOpen = Range.closedOpen(5, 7);
		assertThat(closedOpen.contains(5), is(true));
		assertThat(closedOpen.contains(7), is(false));
		
		Range<Integer> lessThanFive = Range.lessThan(5);
		assertThat(lessThanFive.contains(-9000), is(true));
		assertThat(lessThanFive.contains(5), is(false));
		
		Range<Integer> moreThanTen = Range.moreThan(10);
		assertThat(moreThanTen.contains(10000), is(true));
		assertThat(moreThanTen.contains(10), is(false));
		
		Range<Integer> atLeastTen = Range.atLeast(10);
		assertThat(atLeastTen.contains(10000), is(true));
		assertThat(atLeastTen.contains(10), is(true));
		assertThat(atLeastTen.contains(9), is(false));
		
		Range<Integer> atMostTen = Range.atMost(10);
		assertThat(atMostTen.contains(-10000), is(true));
		assertThat(atMostTen.contains(10), is(true));
		assertThat(atMostTen.contains(11), is(false));
	}
	
	@Test
	public void rangeTest_caseLocalDate() {
		LocalDate from = LocalDate.of(2013, Month.JANUARY, 1);
		LocalDate until = LocalDate.of(2014, Month.JANUARY, 1);
		Range<ChronoLocalDate> rangeDate = Range.of(from, until);
		assertThat(rangeDate.contains(LocalDate.of(2013, Month.JANUARY, 1)), is(true));
		assertThat(rangeDate.contains(LocalDate.of(2013, Month.NOVEMBER, 11)), is(true));
		assertThat(rangeDate.contains(LocalDate.of(2014, Month.JANUARY, 1)), is(true));
		assertThat(rangeDate.contains(LocalDate.of(2014, Month.JANUARY, 2)), is(false));
	}
}
