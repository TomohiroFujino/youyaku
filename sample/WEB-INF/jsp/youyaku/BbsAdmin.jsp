<script language="JavaScript"
	src="${requestScope['app.context']}/youyaku/CheckUtil.js"></script>
<script language="JavaScript">
<!--
function chk(){
	var c = new CheckUtil();
	c.dateTypeCheck(document.fm.old.value,"削除範囲");
	c.rangeCheck(document.fm.num.value,999,1,"記事番号");
	return c.getErrors();
}
//-->
</script>
<form method="POST" action="BbsAdminAction" name="fm" onsubmit="return chk()">
<table border="1" width="600" bordercolordark="#FFffFF">
<tr>
	<th>処理</th>
	<th>概要</th>
</tr>
<tr height="50">
	<th><input type="submit" name="sbm" value="古い記事の整理" /></th>
	<td height="50"><input type="text" name="old"
			size="12" maxlength="10" style="ime-mode:disabled;" />
		（YYYY/MM/DD）より前の記事を一括削除します</td>
</tr>
<tr height="50">
	<th><input type="submit" name="sbm" value="特定記事の削除" /></th>
	<td>No.<input type="text" name="num" size="3" maxlength="3"
		style="ime-mode:disabled;" />の記事を強制的に削除します</td>
</tr>
</table>
</form>
