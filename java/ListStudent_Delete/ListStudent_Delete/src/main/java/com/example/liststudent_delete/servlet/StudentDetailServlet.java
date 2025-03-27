package com.example.liststudent_delete.servlet;

import com.example.liststudent_delete.model.Student;
import com.example.liststudent_delete.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/detail")
public class StudentDetailServlet extends HttpServlet {
    private final StudentRepository studentRepository = new StudentRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Student student = studentRepository.detailStudent(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/Students/Detail.jsp").forward(req,resp);
    }
}
