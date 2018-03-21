package com.kennedy.java.unit.hamcrest.ext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class TimeExecutionMatcherBuilder {
	private final List<Long> matchers = new ArrayList<>();
	void set(Long matcher) {
		if (CollectionUtils.isNotEmpty(matchers)) {
			matchers.clear();
		}
        matchers.add(matcher);
    }

    Long get() {
    	if (matchers.size() == 1) {
    		return matchers.get(0);
        }
        return null;
    }
}
