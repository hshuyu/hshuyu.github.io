package com.hm.blog.action;

import com.hm.blog.entity.*;
import com.hm.blog.service.IAdminArticleService;
import com.hm.blog.service.impl.AdminArticleService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminArticle extends HttpServlet {

    //业务接口
    private IAdminArticleService iAdminArticleService;

    public AdminArticle(){
        this.iAdminArticleService = new AdminArticleService();
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
        }else if ("toInsert".equals(command)){
            this.toInsert(req,resp);
        }else if ("upFile".equals(command)){
            try {
                this.upFile(req,resp);
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

                            System.out.println(filename);
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        if (!filename.equals("")){
            Article article = new Article();
            article.setArticleTitle(lists.get(0));
            article.setArticleContext(lists.get(1));
            article.setCategoryCode(Integer.parseInt(lists.get(3)));
            article.setArticleThumbnail(lists.get(5));
            article.setArticleStatus(Integer.parseInt(lists.get(6)));
            article.setArticleUserCode(0);
            article.setArticleCredate(simpleDateFormat.format(date));
            article.setArticleComment(1);
            int res = this.iAdminArticleService.articleInsert(article);
            if (res>0){
//                request.setAttribute("caretakerCode",Integer.parseInt(lists.get(0)));
                this.index(request,response);
            }
        }else {
            Date date2 = new Date();
            Article article = new Article();
            article.setArticleTitle(lists.get(0));
            article.setArticleContext(lists.get(1));
            article.setCategoryCode(Integer.parseInt(lists.get(3)));
            article.setArticleStatus(Integer.parseInt(lists.get(6)));
            article.setArticleUserCode(0);
            article.setArticleCredate(simpleDateFormat.format(date));
            article.setArticleComment(1);
            int res = this.iAdminArticleService.articleInsert(article);
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

    private void toInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        Tag tag = new Tag();
        List<Category> categorys = this.iAdminArticleService.getAllCategory(category);
        List<Tag> tags = this.iAdminArticleService.getAllTag(tag);
        req.setAttribute("categorys",categorys);
        req.setAttribute("tags",tags);
        req.getRequestDispatcher("/admin/article/insert.jsp").forward(req,resp);
    }

    private void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleTitle = req.getParameter("articleTitle");
        //访问数据库得到产品信息
        Article article = new Article();
        article.setArticleTitle(articleTitle);
        //当前页
        int page = req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"));
        int pageSize = 8;
        PageUtils<Article> articles = this.iAdminArticleService.getAllArticles(article,page,pageSize);
        //封装
        req.setAttribute("articles",articles);
        req.getRequestDispatcher("/admin/article/index.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //
        String articleCode = req.getParameter("articleCode");
        if (articleCode!=null){
            int epId = Integer.parseInt(articleCode);
            Article article = new Article();
            article.setArticleCode(epId);
            int res = this.iAdminArticleService.articleDelete(article);
            if (res>0){
                this.index(req,resp);
            }
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int articleCode = Integer.parseInt(req.getParameter("articleCode"));
        int articleStatus = Integer.parseInt(req.getParameter("articleStatus"));
        //
        Article article = new Article();
        article.setArticleCode(articleCode);
        article.setArticleStatus(articleStatus);
        int res = this.iAdminArticleService.articleEdit(article);
        if (res>0){
            this.index(req,resp);
        }
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int articleCode = Integer.parseInt(req.getParameter("articleCode"));
        Article article = new Article();
        article.setArticleCode(articleCode);
        article = this.iAdminArticleService.getArticle(article);
        if (article != null){
            req.setAttribute("article",article);
            req.getRequestDispatcher("/admin/article/edit.jsp").forward(req,resp);
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //访问数据库得到产品信息
        Article article = null;
        //当前页
        int page = req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"));
        int pageSize = 8;
        PageUtils<Article> articles = this.iAdminArticleService.getAllProduct(article,page,pageSize);
        //封装
        req.setAttribute("articles",articles);
        req.getRequestDispatcher("/admin/article/index.jsp").forward(req,resp);
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
