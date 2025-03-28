package com.example.lab31;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/*") // Xử lý tất cả các đường dẫn
public class cau4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đặt mã hóa UTF-8
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");

        // Lấy phần đường dẫn sau "/ph47140"
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            // Câu 1: Trả về "Hello, [MSSV]"
            resp.getWriter().println("Hello, ph47140");
            return;
        }

        switch (path) {
            case "/poem":
                // Câu 2: Trả về bài thơ X
                resp.getWriter().println("Nội dung bài thơ X");
                break;

            case "/about-me":
                // Câu 3: Trả về thông tin cá nhân
                resp.getWriter().println("Mã số sinh viên: ph47140\nHọ tên: Vào Hậu Quân\nQuê quán: Hải Phòng");
                break;

            case "/calculator/sum":
                // Câu 5: Tính tổng 2 số
                String firstNumber = req.getParameter("firstNumber");
                String secondNumber = req.getParameter("secondNumber");

                if (firstNumber != null && secondNumber != null) {

                        int sum = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber);
                        resp.getWriter().println("Tổng: " + sum);

                } else {
                    resp.getWriter().println("Vui lòng truyền tham số firstNumber và secondNumber.");
                }
                break;

            default:
                // Đường dẫn không hợp lệ
                resp.getWriter().println("Lỗi 404: Đường dẫn không hợp lệ.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String path = req.getPathInfo();
        if ("/poem".equals(path)) {
            // Câu 4: Trả về bài thơ Y khi gửi POST
            resp.getWriter().println("Nội dung bài thơ Y");
        } else {
            resp.getWriter().println("Hành động không hợp lệ hoặc không hỗ trợ POST.");
        }
    }
}
