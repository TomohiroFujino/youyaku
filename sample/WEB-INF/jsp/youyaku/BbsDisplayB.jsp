<link rel="stylesheet" type="text/css" href="${requestScope['app.context']}/data/css/displayB.css" media="all">
<p class="subtitle">
<table border="1" width="1050" cellpadding="5">
	<tr><th align="right" width="150" height="15"">タイトル</th><td>${fn:escapeXml(article.title)}</td></tr>
	<tr><th align="right" width="150" height="15">要約投稿数</th><td >${article.mcount}</td></tr>
	<tr><th align="right" width="150" height="15">記事提供元の名称</th><td  nowrap>${article.source}</td></tr>
	<tr><th align="right" width="150" height="15">記事提供元のURL</th><td ><a href=${article.url}> ${article.url}</a></td></tr>
</table>
</p>

<div class="all">
<div class="left">
<table  border="1" width="500" cellpadding="5">
<tr>
	<th align="right" width="80" height="15">投稿者</th>
	<td>未投稿</td>
</tr>
<tr>
	<th align="right" width="80" height="15">順位</th>
	<td>未投稿</td>
</tr>
<tr>
	<th align="right" width="80" height="15">投稿日時</th>
	<td>未投稿</td>
</tr>
<tr>
<td colspan="2" height="332" valign="top">
<div style="height:332px; overflow-y:auto;">
	未投稿</td>
</div>
</tr>
<tr>
		<th align="right" >文字数</th>
		<td>未投稿</td>
</tr>
</table>
</div>
<div class="right">
<table  border="1" width="500" cellpadding="5">
<tr>
<td colspan="2" height="426" valign="top">
<div style="height:426px; overflow-y:auto;">
	${article.particle}</td>
</div>
</tr>
<tr>
		<th align="right" width="80">文字数</th>
		<td>${article.pcharalen}</td>
</tr>
</table>
</form>
</div>
</div>
<p style="clear:both;">
  <form method="POST" action="${requestScope['app.base']}/BbsNewAction?flagB=${row.mid}" name=frm >
	<span class="subtitle">
		<input type="button" value="要約する" onclick="location.href='../BbsDisplayB/${requestScope['article'].pid}?flag=res'" />
    </span>
  </form>
</p>






