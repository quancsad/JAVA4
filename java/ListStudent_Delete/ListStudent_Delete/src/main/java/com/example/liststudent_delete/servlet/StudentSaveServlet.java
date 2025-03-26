package com.example.liststudent_delete.servlet;

import com.example.liststudent_delete.model.Student;
import com.example.liststudent_delete.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/save")
public class StudentSaveServlet extends HttpServlet {

    private final StudentRepository studentRepository = new StudentRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("name");
        Student student = new Student();
        student.setName(studentName);
        studentRepository.addStudent(student);
        resp.sendRedirect("/view");

    }
}
