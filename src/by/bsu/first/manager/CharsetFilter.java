package by.bsu.first.manager;

import com.mysql.jdbc.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null)
            encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain next) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        String locale = (String) session.getAttribute("locale");
        if(locale==null) {
            locale = "ru";
            session.setAttribute("locale", locale);
        }
        next.doFilter(request, response);
    }

    public void destroy() {
    }
}