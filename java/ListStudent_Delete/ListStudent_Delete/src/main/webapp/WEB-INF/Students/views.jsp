
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sach hoc sinh</title>
</head>
<body>
<h1>list of students</h1>

<table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>điểm toan</th>
    <th>date</th>

  </tr>
  <c:forEach items="${dsHocSinh}" var="hocSinh">
  <tr>

      <td>${hocSinh.id}</td>
      <td>${hocSinh.name}</td>
      <td>${hocSinh.mathScore}</td>
      <td>${hocSinh.dob}</td>
      <td>
        <a href="delete?id=${hocSinh.id}"> Delete</a>
      </td>
  </tr>
  </c:forEach>
</table>


</body>
</html>
