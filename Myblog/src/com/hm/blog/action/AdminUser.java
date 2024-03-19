package com.hm.blog.action;

import com.hm.blog.entity.User;
import com.hm.blog.service.IAdminUserService;
import com.hm.blog.service.impl.AdminUserService;
import com.hm.blog.util.PageUtils;
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
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminUser extends HttpServlet {

    //业务接口
    private IAdminUserService iAdminUserService;

    public AdminUser(){
        this.iAdminUserService = new AdminUserService();
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
        if ("index".equals(command)){
            this.index(req,resp);
        }else if ("toEdit".equals(command)){
            this.toEdit(req,resp);
        }else if ("edit".equals(command)){
            this.edit(req,resp);
        }else if ("delete".equals(command)){
            this.delete(req,resp);
        }else if ("select".equals(command)){
            this.select(req,resp);
        }else if ("upFile".equals(command)){
            try {
                this.upFile(req, resp);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
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
            Date date1 = new Date();
            User user = new User();
            user.setUserAvatar(lists.get(0));
            user.setUserName(lists.get(1));
            user.setUserPwd(lists.get(2));
            user.setUserTel(lists.get(3));
            user.setUserBirth(lists.get(4));
            user.setUserId(lists.get(5));
            user.setUserEmail(lists.get(6));
            user.setUserRegisterTime(date1.toString());
            int res = this.iAdminUserService.userInsert(user);
            if (res>0){
//                request.setAttribute("caretakerCode",Integer.parseInt(lists.get(0)));
                this.index(request,response);
            }
        }else {
            Date date2 = new Date();
            User user = new User();
            user.setUserAvatar(lists.get(0));
            user.setUserName(lists.get(1));
            user.setUserPwd(lists.get(2));
            user.setUserTel(lists.get(3));
            user.setUserBirth(lists.get(4));
            user.setUserId(lists.get(5));
            user.setUserEmail(lists.get(6));
            user.setUserRegisterTime(date2.toString());
            int res = this.iAdminUserService.userInsert(user);
            if (res>0){
//                request.setAttribute("caretakerCode",Integer.parseInt(lists.get(0)));
                this.index(request,response);
            }
        }

//        request.setAttribute("caretakerCode",epId);
        //    request.setAttribute("caretakerAvatar", filename);
        //去修改
        //    this.toEdit(request,response,filename);
        //this.edit(request,response);
    }


    private void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        //访问数据库得到产品信息
        User user = new User();
        user.setUserName(userName);
        //当前页
        int page = req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"));
        int pageSize = 8;
        PageUtils<User> users = this.iAdminUserService.getAllUsers(user,page,pageSize);
        //封装
        req.setAttribute("users",users);
        req.getRequestDispatcher("/admin/user/index.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //
        String userCode = req.getParameter("userCode");
        if (userCode!=null){
            int epId = Integer.parseInt(userCode);
            User user = new User();
            user.setUserCode(epId);
            int res = this.iAdminUserService.userDelete(user);
            if (res>0){
                this.index(req,resp);
            }
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userCodes = req.getParameter("userCode");
        int userCode = Integer.parseInt(userCodes);
        int userLoginStatus = Integer.parseInt(req.getParameter("userLoginStatus"));
        //
        User user = new User();
        user.setUserCode(userCode);
        user.setUserLoginStatus(userLoginStatus);
        int res = this.iAdminUserService.userEdit(user);
        if (res>0){
            this.index(req,resp);
        }
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userCode = Integer.parseInt(req.getParameter("userCode"));
        User user = new User();
        user.setUserCode(userCode);
        user = this.iAdminUserService.getUser(user);
        if (user != null){
            req.setAttribute("user",user);
            req.getRequestDispatcher("/admin/user/edit.jsp").forward(req,resp);
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //访问数据库得到产品信息
        User user = null;
        //当前页
        int page = req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"));
        int pageSize = 8;
        PageUtils<User> users = this.iAdminUserService.getAllUser(user,page,pageSize);
        //封装
        req.setAttribute("users",users);
        req.getRequestDispatcher("/admin/user/index.jsp").forward(req,resp);
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
