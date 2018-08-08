/*
 * Copyright(c)2016 by AXON IVY AG, CH-6000 Lucerne. http://www.axonivy.com
 * 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of
 * AXON IVY AG. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with AXON IVY AG.
 */
package com.java.ee.working.beans;

public interface Translator<F, T> {

    public T translateIn(F source);

    public F translateOut(T source);

}
