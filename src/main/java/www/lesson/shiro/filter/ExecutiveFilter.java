package www.lesson.shiro.filter;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutiveFilter extends AuthorizationFilter {

    protected static final String[] blackUrlPathPattern = new String[] { "*.aspx*", "*.asp*", "*.php*", "*.exe*",
            "*.jsp*", "*.pl*", "*.py*", "*.groovy*", "*.sh*", "*.rb*", "*.dll*", "*.bat*", "*.bin*", "*.dat*",
            "*.bas*", "*.c*", "*.cmd*", "*.com*", "*.cpp*", "*.jar*", "*.class*", "*.lnk*" };

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String reqUrl = httpRequest.getRequestURI().toLowerCase().trim();

        for (String pattern : blackUrlPathPattern) {
            if (PatternMatchUtils.simpleMatch(pattern, reqUrl)) {
                log.error(new StringBuffer().append("unsafe request >>> ").append(" request time: ").append(
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("; request ip: ")
                        .append(getRealIp(httpRequest)).append("; request url: ").append(httpRequest.getRequestURI())
                        .toString());

                return false;
            }
        }

        return true;


    }

    //获得真实IP
    protected String getRealIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }


}
