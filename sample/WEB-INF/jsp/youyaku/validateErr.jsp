<script language="JavaScript">
<!--
errWin=window.open("","Error","width=350, height=150");
errWin.document.open("text/html");
errWin.document.write("<b><u>エラーメッセージ</u></b>");
errWin.document.write("<div style='color:Red'><pre>");
<c:forEach var="item" items="${requestScope['sample.err.msg']}">
	errWin.document.write("${item}<br />");
</c:forEach>
errWin.document.write("</pre></div>");
errWin.document.close();
history.back();
-->
</script>
