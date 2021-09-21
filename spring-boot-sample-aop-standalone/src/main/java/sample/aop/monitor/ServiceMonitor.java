/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.aop.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {
	ThreadLocal<Long> startTime = new ThreadLocal<>();


	@Before("execution(* sample..*Client.*(..))")
	public void logClientAccess(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint);
		startTime.set(System.currentTimeMillis());
	}

	@Around("execution(* sample..*Client.*(..))")
	public void aroundClient(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("[AspectClient] aroundLogger advise1");
		pjp.proceed();
		System.out.println("[AspectClient] aroundLogger advise2");
	}

	@After("execution(* sample..*Client.*(..))")
	public void afterLog() {
		System.out.println("AfterLogger");
		long start = startTime.get();
		System.out.println("Time used ：" + (System.currentTimeMillis() - start) + "ms");
		startTime.remove();
	}
	@AfterReturning(value = "execution(* sample..*Client.*(..))",returning = "o")
	public void afterRunningLog(Object o){
		System.out.println("afterRunningLog");
	}

	//ordre -->	@Around ->@Before->Méthodes->@Around pjp.proceed()->@After->@AfterReturning

	@Before("execution(* sample..*Store.*(..))")
	public void logStoreAccess(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint);
	}


}
