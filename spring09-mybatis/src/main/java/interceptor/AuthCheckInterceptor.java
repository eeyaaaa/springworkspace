package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 구현하는 클래스
public class AuthCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
			//false를 인자로 받게되면 null, true는 새션정보가 있다면 새로 생성하거나 인자를 작성하면 해당 새션불러옴
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {
				System.out.println("세젼정보 있음.인터셉터 동작");
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
					//로그인 페이지로 redirect해줌
		System.out.println("인터셉터 동작");
		return false;
	}

}
