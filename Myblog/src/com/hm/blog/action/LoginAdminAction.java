package com.hm.blog.action;

import com.hm.blog.entity.Caretaker;
import com.hm.blog.service.ICaretakerService;
import com.hm.blog.service.impl.CaretakerService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAdminAction extends HttpServlet {

    //业务接口
    private ICaretakerService iCaretakerService;

    public LoginAdminAction(){
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
        }else if ("exitLogin".equals(command)){
            this.exitLogin(req,resp);
        }else if ("upFile".equals(command)){
            try {
                this.upFile(req, resp);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }else if ("profile".equals(command)){
            this.profile(req,resp);
        }else if ("edit".equals(command)){
            this.edit(req,resp);
        }else if ("toEdit".equals(command)){
            this.toEdit(req,resp);
        }else if ("index".equals(command)){
            this.index(req,resp);
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
                session.setAttribute("caretaker",caretaker1);
                out.write("RIGHT");
                out.close();
            }
        }
    }

    protected void exitLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("caretaker");  //销毁会话空间中的loginUser键值对
        //session.invalidate();  //销毁会话空间
        req.getRequestDispatcher("adminLogin.jsp").forward(req,resp);
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int caretakerCode = Integer.parseInt(req.getParameter("caretakerCode"));
        Caretaker caretaker = new Caretaker();
        caretaker.setCaretakerCode(caretakerCode);
        HttpSession session = req.getSession();
        caretaker = this.iCaretakerService.getCaretaker(caretaker);
        if (caretaker != null){
            session.setAttribute("caretaker",caretaker);
            req.getRequestDispatcher("admin/index.jsp").forward(req,resp);
        }
    }

    private void upFile(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, FileUploadException {
// 1. 创建工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
// 2. 创建FileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
// 3. 判断是否是上传表单
        boolean b = upload.isMultipartContent(request);
        if (!b) {
            // 不是文件上传
            request.setAttribute("message", "对不起，不是文件上传表单！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
// 是文件上传表单
// 4. 解析request，获得FileItem项
        List<FileItem> fileitems = upload.parseRequest(request);
// 5. 遍历集合
    //    int epId = 0;
        String filename = null;
        List<String> lists = new ArrayList<String>();
        int i = 0;
        int epId = Integer.parseInt(request.getParameter("caretakerCode"));
        for (FileItem item : fileitems) {
            // 判断是不是普通字段
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();
                // 手工的转换了
                value = new String(value.getBytes("iso-8859-1"), "utf-8");
                System.out.println(name+"="+value);
                lists.add(value);
        //        request.setAttribute(name,value);
     //           if ("epId".equals(name))
     //               epId = Integer.parseInt(value);
            } else {
                // 文件上传字段
                // 获得文件名
                filename = item.getName();
            //    System.out.println(filename);
                if (!filename.equals("")){
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);

                    //        System.out.println(filename);
                    // 创建文件
                    ServletContext context = getServletContext();
                    String dir = context.getRealPath("img");
                    File file = new File(dir, filename);
                    file.createNewFile();

                    // 获得流，读取数据写入文件
                    InputStream in = item.getInputStream();
                    FileOutputStream fos = new FileOutputStream(file);

                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = in.read(buffer)) > 0)
                        fos.write(buffer, 0, len);
                    fos.close();
                    in.close();
                    item.delete();    // 删除临时文件
                }
                lists.add(filename);
            }
        }

        if (!filename.equals("")){
            Caretaker caretaker = new Caretaker();
            caretaker.setCaretakerCode(Integer.parseInt(lists.get(0)));
            caretaker.setCaretakerAvatar(lists.get(1));
            caretaker.setCaretakerName(lists.get(2));
            caretaker.setCaretakerTel(lists.get(3));
            caretaker.setCaretakerPwd(lists.get(4));
            int res = this.iCaretakerService.caretakerEdit(caretaker);
            if (res>0){
                request.setAttribute("caretakerCode",Integer.parseInt(lists.get(0)));
                this.profile(request,response);
            }
        }else {
            Caretaker caretaker = new Caretaker();
            caretaker.setCaretakerCode(Integer.parseInt(lists.get(0)));
            caretaker.setCaretakerName(lists.get(2));
            caretaker.setCaretakerTel(lists.get(3));
            caretaker.setCaretakerPwd(lists.get(4));
            int res = this.iCaretakerService.caretakerEdit1(caretaker);
            if (res>0){
                request.setAttribute("caretakerCode",Integer.parseInt(lists.get(0)));
                this.profile(request,response);
            }
        }

//        request.setAttribute("caretakerCode",epId);
      //    request.setAttribute("caretakerAvatar", filename);
        //去修改
      //    this.toEdit(request,response,filename);
       //this.edit(request,response);
    }

    private void profile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int caretakerCode = Integer.parseInt(req.getParameter("caretakerCode"));
        Caretaker caretaker = new Caretaker();
        caretaker.setCaretakerCode(caretakerCode);
        caretaker = this.iCaretakerService.getCaretaker(caretaker);
        HttpSession session = req.getSession();
        if (caretaker != null){
            session.setAttribute("caretaker",caretaker);
            req.getRequestDispatcher("admin/caretaker/profile.jsp").forward(req,resp);
        }
    }

    protected void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int caretakerCode = Integer.parseInt(req.getParameter("caretakerCode"));
        Caretaker caretaker = new Caretaker();
        caretaker.setCaretakerCode(caretakerCode);
        caretaker = this.iCaretakerService.getCaretaker(caretaker);
        if (caretaker != null){
//            if (filename != null){
//                caretaker.setCaretakerAvatar(filename);
//            }
            req.setAttribute("caretaker",caretaker);
            req.getRequestDispatcher("admin/caretaker/edit.jsp").forward(req,resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int caretakerCode = Integer.parseInt(req.getParameter("caretakerCode"));
        String caretakerAvatar = req.getParameter("caretakerAvatar");
        String caretakerName = req.getParameter("caretakerName");
        String caretakerTel = req.getParameter("caretakerTel");
        String caretakerPwd = req.getParameter("caretakerPwd");
        //
        Caretaker caretaker = new Caretaker();
        caretaker.setCaretakerCode(caretakerCode);
        caretaker.setCaretakerAvatar(caretakerAvatar);
        caretaker.setCaretakerName(caretakerName);
        caretaker.setCaretakerTel(caretakerTel);
        caretaker.setCaretakerPwd(caretakerPwd);
        int res = this.iCaretakerService.caretakerEdit(caretaker);
        if (res>0){
            req.setAttribute("caretakerCode",caretakerCode);
            this.profile(req,resp);
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
