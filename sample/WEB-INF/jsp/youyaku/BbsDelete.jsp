<script language="JavaScript" src="${requestScope['app.context']}/youyaku/CheckUtil.js"></script>
<script language="JavaScript">
<!--
function chk(){
	var c = new CheckUtil();
	c.requiredCheck(document.fm.passwd.value,"削除パスワード");
	c.lengthCheck(document.fm.passwd.value,15,"削除パスワード");
	return c.getErrors();
}
//-->
</script>
<table border="1" width="500" cellpadding="5">
<tr>
	<th align="right" width="150" height="15">投稿者</th>
	<td>${requestScope['article'].nam}</td>
</tr><tr>
	<th align="right" width="80" height="15">投稿日時</th>
	<td><fmt:formatDate value="${requestScope['article'].pdat}"
		pattern="yyyy年MM月dd日 HH時mm分ss秒" /></td>
</tr><tr>
	<td colspan="2" height="300" valign="top">
		${requestScope['article'].marticle}</td>
</tr><tr>
	<th align="right" nowrap>文字数</th>
	<td>${requestScope['article'].mcharalen}</td>
</tr>
</table>
<form method="POST" action="../BbsDeleteAction/${requestScope['article'].pid}"
	name="fm" onsubmit="return chk()">
	削除用パスワード：
	<input type="password" name="passwd" size="10" maxlength="15" />
	<input type="submit" name="del" value="削除" />
</form>
