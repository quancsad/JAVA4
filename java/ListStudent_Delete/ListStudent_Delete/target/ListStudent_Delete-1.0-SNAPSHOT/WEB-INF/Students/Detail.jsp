<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chi tiết học sinh</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2Lw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <style>
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }
    .container {
      max-width: 600px;
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
    .detail-label {
      font-weight: bold;
      color: #495057;
    }
    .detail-value {
      color: #212529;
    }
    .btn-back {
      background-color: #6c757d;
      border: none;
      transition: background-color 0.3s;
    }
    .btn-back:hover {
      background-color: #5a6268;
    }

  </style>
</head>
<body>
<div class="container">
  <h1><i class="fas fa-info-circle me-2 text-warning"></i> Chi tiết học sinh</h1>
  <div class="mb-3">
    <span class="detail-label">ID:</span>
    <span class="detail-value">${student.id}</span>
  </div>
  <div class="mb-3">
    <span class="detail-label">Tên:</span>
    <span class="detail-value">${student.name}</span>
  </div>
  <div class="mb-3">
    <span class="detail-label">Điểm Toán:</span>
    <span class="detail-value">${student.mathScore}</span>
  </div>
  <div class="mb-3">
    <span class="detail-label">Ngày sinh:</span>
    <span class="detail-value">${student.dob}</span>
  </div>
  <button class="btn btn-back" onclick="window.location.href='/view'">
    <i class="fas fa-arrow-left me-1">Quay lại</i>
  </button>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>