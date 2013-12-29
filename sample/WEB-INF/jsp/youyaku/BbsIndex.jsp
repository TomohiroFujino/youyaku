<script language="JavaScript"
	src="${requestScope['app.context']}/youyaku/CheckUtil.js"></script>
<script language="JavaScript">
<!--
function chk(){
	var c = new CheckUtil();
	c.requiredCheck(document.fm.keywd.value,"検索文字");
	return c.getErrors();
}
-->
</script>
<%@ taglib prefix="win" tagdir="/WEB-INF/tags/youyaku" %>

<table border="0">
<tr>
<td bgcolor="#ccFFDD">

	<form method="POST" action="${requestScope['app.base']}/BbsSearch"  name="fm" onsubmit="return chk()">
		<span style="font-weight:bold;">記事検索：</span>
		<input type="text" name="keywd" size="20" />
		<input type="submit" value="検索" /><br>
		<input type="radio" name="search" value="parent" checked><span style="font-size:80%;">要約する文章</span>
		<input type="radio" name="search" value="master"><span style="font-size:80%;">要約した文章</span>
	</form>
    <div align="right">
	［<a href="${requestScope['app.base']}/BbsAdmin">管理者ページへ</a>］
</td>
</tr>
</table>
<win:showThread />
<br />
<div align="center">
	<win:showPager increment="${increment}" />
</div>

