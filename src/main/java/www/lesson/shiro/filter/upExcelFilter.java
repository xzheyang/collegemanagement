package www.lesson.shiro.filter;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *  第一种方案(利用AOP拦截所有controller包参数是file的方法,然后检验file)
 *  缺点:但是这就限制了上传必须是file,否则就要拦截所有方法并判断有无File类型的参数(效率低)
 */


@Component
@Aspect
public class upExcelFilter {
    final public static String excelType="D0CF11E0";

    @Pointcut("execution(* *.controller.*(..))")
    public void controllerPointCut(){}

    @Around("controllerPointCut() && args(file)")
    public void aroundUpFileOnlyExcel(ProceedingJoinPoint pjp){


    }

}
