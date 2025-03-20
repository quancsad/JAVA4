package com.example.BUOI3.Servlet;


import com.example.BUOI3.Repo.SanPhamRepository;
import com.example.BUOI3.model.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/view")
public class ViewSanPhamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Danh sachs san pham</h1>");
        SanPhamRepository repository = new SanPhamRepository();
        List<SanPham> sanPhams = repository.getAll();
        if (sanPhams.isEmpty()){
            resp.getWriter().println("<h1>Danh sachs rong</h1>");
        }else {
            resp.getWriter().println("<ul>");
            for (SanPham sp : sanPhams) {
                resp.getWriter().println("<li>" + sp.getId() + " " + sp.getName() + "</li>");
            }
            resp.getWriter().println("</ul>");
        }
    }
}
