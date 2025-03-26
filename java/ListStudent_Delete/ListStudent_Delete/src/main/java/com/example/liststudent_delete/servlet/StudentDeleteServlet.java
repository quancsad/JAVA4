package com.example.liststudent_delete.servlet;

import com.example.liststudent_delete.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class StudentDeleteServlet extends HttpServlet {
    public final StudentRepository repository = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String id = req.getParameter("id");
            Integer deletedId = Integer.parseInt(id); // chuyển id từ kiểu chuỗi sang kiểu số nguyên
        repository.delete(deletedId);
        resp.sendRedirect("view");
    }
}
