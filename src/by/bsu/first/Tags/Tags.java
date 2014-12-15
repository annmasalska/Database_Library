package by.bsu.first.Tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
@SuppressWarnings("serial")
public class Tags extends TagSupport {
    private String role;
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            String to = null;
            if ("administrator".equalsIgnoreCase(role)) {
                to = "Hello, " + role;
            } else {
                to = "Welcome, " + role;
            }
            pageContext.getOut().write(to);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}