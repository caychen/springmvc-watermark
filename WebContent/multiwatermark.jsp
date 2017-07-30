<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示处理图片</title>
</head>
<body>
	<table width="99%" align="center">
	<s:iterator value="picInfos">
		<tr>
			<td width="50%"><img src="${pageContext.request.contextPath }<s:property value="ImageURL"/>" width="550"/></td>
			<td width="50%"><img src="${pageContext.request.contextPath }<s:property value="logoImageURL"/>" width="550"/></td>
		</tr>
	</s:iterator>		
	</table>
</body>
</html>