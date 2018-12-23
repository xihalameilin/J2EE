package servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private  DataSource dataSource =null;
    private final String CORRECTUSER = "密码正确";
    private final String WRONGPASSWORD = "密码错误";
    private final String UNKOWNID = "未知的ID";

    @Override
    public void init()  {
        InitialContext jndiContext = null;
        try {
            jndiContext = new InitialContext();
            dataSource  = (DataSource) jndiContext
                    .lookup("java:comp/env/jdbc/J2EE");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        System.out.println("get");
        System.out.println(username);
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().write("get");
        resp.sendRedirect(resp.encodeRedirectURL("/user/user.jsp"));
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html; charset=UTF-8");
//
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        HttpSession session = req.getSession(false);
//
//        String oldUserName = (String) session.getAttribute("username");
//        //第一次登录
//        if(null == oldUserName){
//            String res = isUser(username,password);
//            //密码正确
//            if(CORRECTUSER.equals(res)){
//                System.out.println(CORRECTUSER);
//               // session.setAttribute("username",username);
//                resp.sendRedirect("user.jsp");
//                System.out.println("执行完毕");
//            }
//            //错误的密码
//            else if(WRONGPASSWORD.equals(res)){
//                System.out.println(WRONGPASSWORD);
//                resp.sendRedirect("index.jsp");
//            }
//            //未知的ID
//            else if(UNKOWNID.equals(res)){
//                System.out.println(UNKOWNID);
//                resp.sendRedirect("index.jsp");
//            }
//        }
//
//        //不是第一次登录界面
//        else{
//            resp.sendRedirect("shopcar.html");
//        }
//    }

    private String isUser(String username,String password){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USER");
            while(rs.next()){
                String name = rs.getString("name");
                String pass = rs.getString("password");
                if(name.equals(username)){
                    if(pass.equals(password))
                        return "密码正确";
                    else
                        return "密码错误";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "未知的ID";
    }
}
