<link rel="stylesheet" type="text/css" href="${requestScope['app.context']}/data/css/search.css" media="all">
<h2>キーワード「${fn:escapeXml(param['keywd'])}」による検索結果   ${fn:length(requestScope['articles'])}件</h2>
<c:forEach var="row" items="${requestScope['articles']}">
	<table border="1" width="500" cellpadding="5">
    <tr>
	    <th align="right" width="150" height="15">タイトル</th>
	    <td>${row.title}</td>
    </tr>
    <tr>
    	<th nowrap align="right" width="150" height="15">記事提供元の名称</th>
   		<td  nowrap>${row.source}</td>
    </tr>
	<tr>
	    <th align="right" width="150" height="15">投稿者</th>
	    <td>${row.nam}</td>
    </tr>
	<tr>
		<td colspan="2" height="300" valign="top">
		<div style="height:333px; overflow-y:auto;">${row.searcharticle}</div>
		</td>
	</tr>
	</table>
		<c:choose>
		  <c:when test="${row.mcount!=0}">
			<pre>                     [<a href="BbsDisplayA/${row.pid}">親記事へ移動</a>]</pre>
		  </c:when>
		  <c:otherwise>
		    <pre>                     [<a href="BbsDisplayB/${row.pid}">親記事へ移動</a>]</pre>
		  </c:otherwise>
	    </c:choose>

	<br />
	<hr />
</c:forEach>
