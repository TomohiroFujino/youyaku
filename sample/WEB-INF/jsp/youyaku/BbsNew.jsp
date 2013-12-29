<script language="JavaScript"
	src="${requestScope['app.context']}/youyaku/CheckUtil.js"></script>
<script language="JavaScript">
<!--
function chk(){
	var c = new CheckUtil();
	c.requiredCheck(document.fm.title.value,"タイトル");
	c.lengthCheck(document.fm.title.value,50,"タイトル");
	c.requiredCheck(document.fm.source.value,"記事提供元の名称");
	c.lengthCheck(document.fm.source.value,50,"記事提供元の名称");
	c.requiredCheck(document.fm.url.value,"URL");
	return c.getErrors();
}
function charaLen(str){
	  val=str.replace(/\s|　/g,"");
	  val=val.length;
	   document.getElementById('len').value = val;
}
-->
</script>
<form method="POST" action="BbsNewAction" name="fm" onsubmit="return chk()">
<table border="1" width="500" cellpadding="5" >
	<tr>
		<th align="right" nowrap>タイトル</th>
		<td><input type="text" name="title" size="97" maxlength="50"
					style="ime-mode:active;" /></td>
	</tr><tr>
		<th align="right">要約する文章</th>
		<td colspan="2">
			<textarea id="s" name="article"  cols="73" rows="15" onKeyup="charaLen(value);"></textarea>
		</td>
	</tr><tr>
		<th align="right" nowrap>文字数</th>
		<td><input type="text" id="len" value ="0" name="charalen" size="5" maxlength="3" /></td>
	</tr><tr>
		<th align="right" nowrap>記事提供元の名称</th>
		<td><input type="text" name="source" size="97" maxlength="50" /></td>
	</tr><tr>
		<th align="right" nowrap>記事提供元のURL</th>
		<td><input type="text" name="url" size="97"  /></td>
	</tr>
	</table>
	<input type="submit" name="sbm" value="投稿" />
	<input type="reset" value="リセット" />
</form>
