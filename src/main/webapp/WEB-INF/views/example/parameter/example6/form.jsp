<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Example</title>
</head>
<body>
    <h2 style="text-align: center; margin-top: 100px;">Check web console</h2>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
           var json = {
               user: {
                   name: 'ABC',
                   age: 33,
                   address: 'seoul south korea'
               }
           };
           console.log(JSON.stringify(json));
           $.ajax({
               url: '/example/parameter/example6/saveData',
               type: 'post',
               data: JSON.stringify(json),
               contentType: 'application/json',
               dataType: 'json',
               success: function (data) {
                    console.log(data);
               }
           });
        });
    </script>
</body>
</html>