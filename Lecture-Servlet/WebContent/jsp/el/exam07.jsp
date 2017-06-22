<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bit.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// 성공 / 실패 ? ${boardList[0].title}<br> <!-- 성공 -->와 같이 뽑아내는 방법
	// 성공 / 실패 ? ${boardList[1].title}<br> <!-- 실패 -->와 같이 뽑아내는 방법
	BoardVO board = new BoardVO();
	board.setTitle("성공");	
	BoardVO board2 = new BoardVO();
	board2.setTitle("실패");

	// 1. ArrayList
	// List<BoardVO> boardList = new ArrayList<>();
	// boardList.add(board);
	// boardList.add(board2);
	// pageContext.setAttribute("boardList", boardList);
	
	// 2. array
	// BoardVO[] boardList = new BoardVO[]{board, board2};
	// pageContext.setAttribute("boardList", boardList);
	
	HashMap<String, String> map = new HashMap<>();
	map.put("title", "성공");
	HashMap<String, String> map2 = new HashMap<>();
	map2.put("title", "실패");
	
	// 3. ArrayList<Map>
	// List<HashMap<String, String>> boardMapList = new ArrayList<>();
	// boardMapList.add(map);
	// boardMapList.add(map2);
	// pageContext.setAttribute("boardList", boardMapList);
	
	// 4. array[Map]
	// HashMap<String, String>[] boardMapArray = new HashMap[2];
	// boardMapArray[0] = map;
	// boardMapArray[1] = map2;
	// pageContext.setAttribute("boardList", boardMapArray);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	성공 / 실패 ? ${boardList[0].title}<br> <!-- 성공 -->
	성공 / 실패 ? ${boardList[1].title}<br> <!-- 성공 -->
</body>
</html>