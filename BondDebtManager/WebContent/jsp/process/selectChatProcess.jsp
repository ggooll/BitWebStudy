<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach items="${ chatVOList }" var="chatVO" varStatus="i" >
<c:if test="${ i.count != 1 }">, </c:if>
{ "no" : "${ chatVO.no }", "groupNo" : "${ chatVO.groupNo }", "memberNo" : "${ chatVO.memberNo }", "memberName" : "${ chatVO.memberName }", "message" : "${ chatVO.message }", "chatDate" : "${ chatVO.chatDate }" }
</c:forEach>
]