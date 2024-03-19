package com.hm.blog.action;

import com.hm.blog.entity.Caretaker;
import com.hm.blog.service.ICaretakerService;
import com.hm.blog.service.impl.CaretakerService;
import javafx.scene.control.Alert;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAdmin extends HttpServlet {

    //业务接口
    private ICaretakerService iCaretakerService;

    public LoginAdmin(){
        this.iCaretakerService = new CaretakerService();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //逻辑分流（区分请求源）
        //解析请求包（得到指令需求）
        String command = req.getParameter("command");
        if ("adminLogin".equals(command)){
            this.adminLogin(req,resp);
        }
    }

    protected void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到用户输入数据
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");
        System.out.println(userName);
        System.out.println(passWord);
        Caretaker caretaker = new Caretaker();
        caretaker.setCaretakerName(userName);
        caretaker = this.iCaretakerService.checkUserName(caretaker);
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        if (caretaker==null){
            System.out.println("NO");
            out.write("NO");
            out.close();
        }else {
            Caretaker caretaker1 = new Caretaker();
            caretaker1.setCaretakerName(userName);
            caretaker1.setCaretakerPwd(passWord);
            //业务组件（登陆验证）
            caretaker1 = this.iCaretakerService.adminLogin(caretaker1);
            System.out.println(caretaker1==null?"ERROR":"RIGHT");
            if (caretaker1==null){
                out.write("ERROR");
                out.close();
            }
            else {
                if (caretaker1.getCaretakerLoginStatus()==2){
                    out.write("SAME");
                    out.close();
                }else {
                    caretaker1 = this.iCaretakerService.upStatus(caretaker1);
                    session.setAttribute("caretaker",caretaker1);
                    out.write("RIGHT");
                    out.close();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }
}