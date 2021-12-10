<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비타500(bita500) - 암호화폐 거래소의 새로운 기준</title>

		<link rel="icon" href="/resources/path/alibit.png" type="image/x-icon">
        
        <style>
        .inner-popup-wrap2 {
            position: fixed;
            width: 800px;
            height: 600px;
            top: 50%;
            left: 50%;
            margin-left: -400px;
            transform: translateY(-50%);
            border: 0;
            background: url(/resources/img/error/error_500.png) no-repeat;
            background-position: center center;
            z-index: 101;
        }
        .inner-msg1 {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            display: inline-block;
            text-align: center;
            padding-top:335px;
            color: #172a88;
            font-weight: 700;
            font-size: 15px
        }
        .inner-msg2 {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            display: inline-block;
            text-align: center;
            padding-top:355px;
            font-weight: 700;
            font-size: 15px
        }
        </style>
	</head>
	<body>
        <div class="dimmed inner-pop" style="top:0"></div>
        <div class="inner-popup-wrap2"></div>
	</body>
</html>