<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách học sinh</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Font Awesome để thêm icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2Lw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- CSS tùy chỉnh -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin-top: 30px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #343a40;
            font-weight: 600;
            margin-bottom: 20px;
        }
        .btn-add {
            background-color: #28a745;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-add:hover {
            background-color: #218838;
        }
        .btn-delete {
            background-color: #dc3545;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-delete:hover {
            background-color: #c82333;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .table thead {
            background-color: #343a40;
            color: #fff;
        }
        .table tbody tr:hover {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><i class="fas fa-users me-2"></i> Danh sách học sinh</h1>
    <form action="/form" method="get">
        <button type="submit" class="btn btn-add mb-3">
            <i class="fas fa-plus me-1"></i> Thêm học sinh
        </button>
    </form>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên</th>
            <th>Điểm Toán</th>
            <th>Ngày sinh</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dsHocSinh}" var="hocSinh" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${hocSinh.name}</td>
                <td>${hocSinh.mathScore}</td>
                <td>${hocSinh.dob}</td>
                <td>
                    <button class="btn btn-sm btn-delete" onclick="if(confirm('Bạn có chắc muốn xóa?')) window.location.href='delete?id=${hocSinh.id}'">
                        <i class="fas fa-trash-alt me-1"></i> Xóa
                    </button>
                    <button class="btn btn-sm btn-detail me-1" onclick="window.location.href='/detail?id=${hocSinh.id}'">
                        <i class="fas fa-info-circle me-1">Chi tiết</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS và Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>