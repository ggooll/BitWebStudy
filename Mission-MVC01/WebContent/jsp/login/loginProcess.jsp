<%@page import="kr.co.bit.member.dao.MemberDAO"%>
<%@page import="kr.co.bit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	alert("${msg}");
	location.href = "${url}";
</script>