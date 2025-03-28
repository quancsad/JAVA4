<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa học sinh</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2Lw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/webapp/WEB-INF/sTUDENTS/css.jsp">
</head>
<body>
<div class="container">
    <h1><i class="fas fa-user-edit me-2"></i>Chỉnh sửa thông tin học sinh</h1>
    <form action="/save" method="post">
        <input type="hidden" name="id" value="${student.id}">
        <div class="mb-3">
            <label for="studentName" class="form-label">Tên</label>
            <input type="text" class="form-control" id="studentName" name="StudentName" value="${student.name}">
        </div>
        <div class="mb-3">
            <label for="mathScore" class="form-label">Điểm Toán</label>
            <input type="text" class="form-control" id="mathScore" name="mathScore" value="${student.mathScore}">
        </div>
        <div class="mb-3">
            <label for="dob" class="form-label">Ngày sinh</label>
            <input type="text" class="form-control" id="dob" name="dob" value="${student.dob}">
        </div>
        <button type="submit" class="btn btn-dark btn-update"><i class="fas fa-save me-1"></i> Thêm mới</button>
        <button type="button" class="btn btn-danger btn-cancel" onclick="window.location.href='/view'"><i class="fas fa-times me-1"></i> Hủy</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>