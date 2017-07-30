<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传图片</title>
</head>
<body>
	<h4>上传图片</h4>
	<hr/>
	<!-- enctype 属性规定在发送到服务器之前应该如何对表单数据进行编码。 -->
	<form name="form2" action="${pageContext.request.contextPath }/watermark_multi.action" method="post" enctype="multipart/form-data">
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="submit" value="上传图片" />
	</form>
</body>
</html>