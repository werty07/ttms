package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Cus;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.CusSrv;
import xupt.se.ttms.service.StudioSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/CusServlet")
public class CusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if (type.equalsIgnoreCase("add"))
            add(request, response);
        else if (type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if (type.equalsIgnoreCase("update"))
            update(request, response);
        else if (type.equalsIgnoreCase("search"))
            search(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cus cus = null;
        int id = 0;
        try {
            String name = request.getParameter("name");
            int gender = Integer.valueOf(request.getParameter("gender"));
            String telnum = request.getParameter("telnum");
            String email = request.getParameter("email");
            String uid = request.getParameter("uid");
            String pwd = request.getParameter("pwd");
            int balance = Integer.valueOf(request.getParameter("balance"));
            String paypwd = request.getParameter("paypwd");
            cus = new Cus(id, name, gender,telnum,email,uid,pwd,balance,paypwd);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if (new CusSrv().add(cus) == 1)
                out.write("数据添加成功");
            else
                out.write("数据添加失败，请重试");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("" + new CusSrv().delete(id));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cus cus = null;
        int id = 0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            int gender = Integer.valueOf(request.getParameter("gender"));
            String telnum = request.getParameter("telnum");
            String email = request.getParameter("email");
            String uid = request.getParameter("uid");
            String pwd = request.getParameter("pwd");
            int balance = Integer.valueOf(request.getParameter("balance"));
            String paypwd = request.getParameter("paypwd");
            cus = new Cus(id, name, gender, telnum, email,uid,pwd,balance,paypwd);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if (new CusSrv().modify(cus) == 1)
                out.write("数据修改成功");
            else
                out.write("数据修改失败，请重试");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        List<Cus> result = null;
        if (name != null && name.length() > 0)
            result = new CusSrv().Fetch(name);
        else
            result = new CusSrv().FetchAll();
        String jsonStr = "";
        try {
            JSONArray array = new JSONArray();
            JSONObject json;
            for (Cus s : result) {
                json = new JSONObject();
                json.put("id", s.getId());
                json.put("name", s.getName());
                json.put("gender", s.getGender());
                json.put("telnum", s.getTelnum());
                json.put("email", s.getEmail());
                json.put("uid", s.getUid());
                json.put("pwd", s.getPwd());
                json.put("balance", s.getBalance());
                json.put("paypwd", s.getPaypwd());
                array.put(json);
            }
            jsonStr = array.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            out.println(jsonStr);
            out.flush();
            out.close();
        }
        // System.out.print(jsonStr);
    }
}