package interceptor;

import java.util.Map;  

import com.opensymphony.xwork2.Action;  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  
  
@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {  
  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
  
    	 // 取得请求相关的ActionContext实例  
        ActionContext ctx = invocation.getInvocationContext();  
        Map<String, Object> session = ctx.getSession();  
        Object user = session.get("account"); 
        Object admin = session.get("admin");
  
     // 如果没有登陆，返回重新登陆   
  
        if (user != null || admin!=null) {  
            return invocation.invoke();  
        }
        System.out.println("登录已拦截");
        return Action.LOGIN;  
  
    }  
  
}  
