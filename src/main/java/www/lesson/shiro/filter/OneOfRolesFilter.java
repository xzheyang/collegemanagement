package www.lesson.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class OneOfRolesFilter extends AuthorizationFilter {

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = this.getSubject(request, response);
        String[] rolesArray = (String[])((String[])o);

        if(rolesArray!=null) {
            for (int i = 0; i < rolesArray.length; i++) {

                if (subject.hasRole(rolesArray[i])) {
                    return true;
                }
            }
        }

        return false;

    }

}
