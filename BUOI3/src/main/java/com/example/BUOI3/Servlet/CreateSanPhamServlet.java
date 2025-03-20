package com.example.BUOI3.Servlet;

import com.example.BUOI3.Repo.SanPhamRepository;
import com.example.BUOI3.model.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/create")
public class CreateSanPhamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SanPhamRepository sanPhamRepository = new SanPhamRepository();
        SanPham sanPham = new SanPham();
        sanPham.setName("ahihi");
        Object object = sanPhamRepository.create(sanPham);
        resp.getWriter().println(object);
    }
}
