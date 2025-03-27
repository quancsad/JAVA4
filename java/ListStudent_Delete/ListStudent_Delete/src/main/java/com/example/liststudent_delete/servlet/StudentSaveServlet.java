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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("StudentName");
        String studentDiem = req.getParameter("mathScore");
        String studentDate = req.getParameter("dob");
        Student student = new Student();
        student.setName(studentName);
        student.setMathScore(studentDiem != null ? Float.parseFloat(studentDiem) : 0.0f); // Chuyển sang float
        student.setDob(studentDate);
        studentRepository.addStudent(student);
        resp.sendRedirect("/view");

    }
}
