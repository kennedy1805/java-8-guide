package com.kennedy.java.eight;

public class Range<T extends Comparable<T>> {

	protected T lowerBound;
	protected T upperBound;
	protected Type type;

	protected Range(T lower, T upper, Type type) {
		if (lower == null || upper == null) {
			
		} else if(lower.compareTo(upper) > 0){
			throw new RuntimeException("are you retarded?");
		}
		this.lowerBound = lower;
		this.upperBound = upper;
		this.type = type;
	}
	
	public boolean contains(T i) {
		return isInBoundary(i) || isOnBoundary(i);
	}

	public static <T extends Comparable<T>> Range<T> open(T i, T j) {
		return new Range<T>(i, j, Type.OPEN);
	}

	public static <T extends Comparable<T>> Range<T> closed(T i, T j) {
		return new Range<T>(i, j, Type.CLOSED);
	}

	public static <T extends Comparable<T>> Range<T> openClosed(T i, T j) {
		return new Range<T>(i, j, Type.OPENCLOSED);
	}

	public static <T extends Comparable<T>> Range<T> closedOpen(T i, T j) {
		return new Range<T>(i, j, Type.CLOSEDOPEN);
	}

	private boolean isOnBoundary(T i) {
		switch (this.type) {
		case OPEN:
			return false;
		case CLOSED:
			return isEqualsBoundary(lowerBound, i) || isEqualsBoundary(upperBound, i);
		case OPENCLOSED:
			return isEqualsBoundary(upperBound, i);
		case CLOSEDOPEN:
			return isEqualsBoundary(lowerBound, i) ;
		default:
			return false;
		}
	}

	private boolean isEqualsBoundary(T i, T j) {
		if (i == null) {
			return false;
		}
		return j.compareTo(i) == 0;
	}

	private boolean isInBoundary(T i) {
		return greaterThanBound(i, lowerBound) && lowerThanBound(i, upperBound);

	}

	private boolean lowerThanBound(T i, T bound) {
		if (bound == null) {
			return true;
		}
		return i.compareTo(bound) < 0;
	}

	private boolean greaterThanBound(T i, T bound) {
		if (bound == null) {
			return true;
		}
		return i.compareTo(bound) > 0;
	}

	enum Type {
		OPEN, CLOSED, OPENCLOSED, CLOSEDOPEN;
		/*
		 // open range excludes both bounds
		(5, 7)
		// closed range includes both bounds
		[5, 7]
		// open closed excludes lowerbound but includes upperbound
		(5, 7]
		// closed open includes lowerbound but excludes upperbound
		[5, 7)
		 */
	}

	public static <T extends Comparable<T>> Range<T> of(T i, T j) {
		return new Range<T>(i, j, Type.CLOSED);
	}

	public static <T extends Comparable<T>> Range<T> lessThan(T i) {
		return new Range<T>(null, i, Type.OPEN);
	}
	
	public static <T extends Comparable<T>> Range<T> moreThan(T i) {
		return new Range<T>(i, null, Type.OPEN);
	}

	public static <T extends Comparable<T>> Range<T> atLeast(T i) {
		return new Range<T>(i, null, Type.CLOSED);
	}
	
	public static <T extends Comparable<T>> Range<T> atMost(T i) {
		return new Range<T>(null, i, Type.CLOSED);
	}
}
