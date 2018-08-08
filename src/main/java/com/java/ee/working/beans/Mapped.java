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

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(Mappeds.class)
public @interface Mapped {
    
    /** Specifies the other attribute name. Only needed if it differs from the target name */
    String name() default "";
    
    /** Specifies the translator class to be used to translate the object from other to target and vice versa. */
    Class<? extends Translator> translator() default Translator.class;
    
    /** Specified the translator class to be used to translate the elements of a collection. */
    Class<? extends Translator> elementTranslator() default Translator.class;
    
    /** Specifies the other types for which this annotation is valid. */
    Class other() default Object.class;
    
}
