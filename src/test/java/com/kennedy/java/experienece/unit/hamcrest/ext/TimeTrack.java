package com.kennedy.java.experienece.unit.hamcrest.ext;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TimeTrack implements TestRule{

	private final TimeExecutionMatcherBuilder matcherBuilder = new TimeExecutionMatcherBuilder();

	@Override
	public Statement apply(Statement base, Description description) {
		return new ExpectedTimeExecution(base);
	}
	
	public static TimeTrack track() {
        return new TimeTrack();
    }
	
	public void expectTime(Long millis) {
		matcherBuilder.set(millis);
    }
	
	private class ExpectedTimeExecution extends Statement {
        private final Statement next;

        public ExpectedTimeExecution(Statement base) {
            next = base;
        }

        @Override
        public void evaluate() throws Throwable {
        	Instant start = Instant.now();
        	next.evaluate();
        	Instant end = Instant.now();
        	Duration duration = Duration.between(start, end);
        	handleAssert(duration.toMillis());
        }
    }

    private void handleAssert(Long duration) throws Throwable {
    	if (matcherBuilder.get() != null) {
//    		"Expected time execution is " + matcherBuilder.get() + " milliseconds but " + duration + " milliseconds", 
    		assertThat(duration, lessThanOrEqualTo(matcherBuilder.get()));
    		
		}
    }
}
