package org.codehaus.mojo.archetypes;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class AppServlet extends HttpServlet {
 
    private static final long serialVersionUID = -1915463532411657451L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String x = null;
        x.toString();

        String username = request.getParameter("username");
        out.println("<h2>Welcome "+username+"!</h2>");
    }
}