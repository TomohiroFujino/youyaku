<link rel="stylesheet" type="text/css" href="${requestScope['app.context']}/data/css/res.css" media="all">
<script language="JavaScript"
	src="${requestScope['app.context']}/youyaku/CheckUtil.js"></script>
<script language="JavaScript">
<!--
function chk(){
	var c = new CheckUtil();
	c.requiredCheck(document.fm.nam.value,"投稿者名");
	c.lengthCheck(document.fm.nam.value,10,"投稿者名");
	c.requiredCheck(document.fm.article.value,"本文");
	c.requiredCheck(document.fm.passwd.value,"削除用パスワード");
	c.lengthCheck(document.fm.passwd.value,15,"削除用パスワード");
	return c.getErrors();
}
function charaLen(str){
	  val=str.replace(/\s|　/g,"");
	  val=val.length;
	   document.getElementById('len').value = val;
}
//-->
</script>
<p class="subtitle">
<table border="1" width="1050" cellpadding="5">
	<tr><th align="right" width="150" height="15"">タイトル</th><td>${fn:escapeXml(article.title)}</td></tr>
	<tr><th align="right" width="150" height="15">要約投稿数</th><td >${article.mcount}</td></tr>
	<tr><th align="right" width="150" height="15">記事提供元の名称</th><td  nowrap>${article.source}</td></tr>
	<tr><th align="right" width="150" height="15">記事提供元のURL</th><td ><a href=${article.url}> ${article.url}</a></td></tr>
</table>
</p>

<form method="POST" action="${requestScope['app.base']}/BbsNewAction?flag=res" name="fm" onsubmit="return chk()">
<div class="all">
<div class="left">
<table style="float:left;" border="1" width="500" cellpadding="5">
<input type="hidden" name="parent" value="${fn:escapeXml(requestScope['article'].pid)}">
<tr>
	<th align="right" nowrap>投稿者</th>
	<td><input type="text" name="nam" size="55" maxlength="10"
		style="ime-mode:active; font-size:100%;" /></td>
</tr>
<tr>
	<th align="right" nowrap>投稿日時</th>
	<td>
		<jsp:useBean id="dat" class="java.util.Date" />
		<fmt:formatDate value="${dat}"
			pattern="yyyy年MM月dd日 HH時mm分ss秒" /></td>
</tr>
<tr>
	<td colspan="2" valign="top">
		<textarea cols="69" rows="17" name="article" onKeyup="charaLen(value);" style="width:100%; font-size:100%;"></textarea>
	</td>
</tr>
<tr>
		<th align="right" width="80">文字数</th>
		<td><input type="text" id="len" value ="0" name="charalen" size="5" maxlength="3" readonly="readonly"/></td>
</tr>
<tr>
	<th align="right" nowrap>削除用パスワード</th>
	<td><input type="password" name="passwd" size="15" maxlength="15"
		style="ime-mode:disabled;" /></td>
</tr>
</table>
</div>
<div class="right">
<table style="float:left;margin-left:10px;" border="1" width="500" cellpadding="5">
<tr>
<td colspan="2" height="328" width="580"　valign="top">
<div style="height:420px; width:580px; overflow-y:auto;">
	${requestScope['article'].particle}</td>
</div>
</tr>
<tr>
		<th align="right" width="80">文字数</th>
		<td>${requestScope['article'].pcharalen}</td>
</tr>

</table>
</div>
</div>

<p style="clear:both;">
<span class="subtitle">
	<input type="hidden" name="pid" value="${requestScope['article'].pid}" />
	<input type="submit" name="res" value="投稿" />
	<input type="reset" value="リセット" />
</span>
</form>


