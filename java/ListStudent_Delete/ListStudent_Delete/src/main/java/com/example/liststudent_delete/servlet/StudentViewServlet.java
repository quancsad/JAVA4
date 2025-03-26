package com.example.liststudent_delete.servlet;

import com.example.liststudent_delete.model.Student;
import com.example.liststudent_delete.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/view")
public class StudentViewServlet extends HttpServlet {
    private final StudentRepository repository = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = repository.getAll();
        req.setAttribute("dsHocSinh", students);
        req.getRequestDispatcher("/WEB-INF/Students/views.jsp").forward(req, resp);
    }
}
