/*
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.faulttolerance;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The fallback annotation to define the fallback handler class so that
 * a failure can be handled properly.
 * 
 * <li>If value is specified, use {@link FallbackHandler#handle()} on the specified handler to execute the fallback.
 * <li>If fallbackMethod is specified, invoke the method specified by the fallbackMethod on the same class.
 * <li>If both are specified, try to fallback to fallbackMethod first, 
 * if it fails then call {@link FallbackHandler#handle()}
 * 
 * 
 * 
 *  
 * 
 * @author <a href="mailto:emijiang@uk.ibm.com">Emily Jiang</a>
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Inherited
public @interface Fallback {

    /**
     * Specify the fallback class to be used. An new instance of the fallback class
     * is returned. The instance is unmanaged. The type parameter of the fallback class must be assignable to the
     * return type of the annotated method. Otherwise, the {@link javax.enterprise.inject.spi.DeploymentException} must be thrown.
     * 
     * @return the fallback class
     */
    Class<? extends FallbackHandler<?>> value();
    /**
    * Specify the method name to be fallbacked to. This method belongs
    * to the same class as the method to fallback.
    * The method must be either zero argument or have the exactly same arguments as the method being annotated.
    * The method return type must be assignable to the return type of the method annotated. 
    * Otherwise, the {@link javax.enterprise.inject.spi.DeploymentException} must be thrown.
    * @return the local method to fallback to
    */
    String fallbackMethod() default "";
}
