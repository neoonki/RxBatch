package neoonki.data.rx;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskManager {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    private TaskData taskData;
	
//	public abstract Future<String> run(String message) throws Exception;
	
	@Around("execution(* neoonki.task..*.run(..))")
    public Object onAroundLogServiceAccess(final ProceedingJoinPoint joinPoint) {
        Object objRet = null;
        try {
            /// 대상 메소드 실행 전
        	log.info("onAroundReturningLogServiceAccess Before : " + joinPoint);
        	taskData.add(joinPoint.getSignature().getName());
            objRet = joinPoint.proceed();
            log.info("onAroundReturningLogServiceAccess After  : " + joinPoint);
            /// 대상 메소드 실행 후
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if (objRet != null) {
            String msg = (String)objRet;
            return (Object)msg.toUpperCase();
        }

        return objRet;
    }
}
