<link rel="stylesheet" type="text/css" href="${requestScope['app.context']}/data/css/displayA.css" media="all">
<script language="JavaScript">
<!--
function urlEncoding(str){
	  document.getElementById('len').value = val;
	  val = encodeURI( document.getElementById("formInput").value );
	  document.getElementById("encOutput").innerHTML = val;
}
-->
</script>

<!-- 順位付与用の変数 -->
<c:set var="rank" value="1"/>
<!-- ポイント比較用の変数(前レコードと比較する) -->
<c:set var="point_biutiful" value="0"/>
<p class="subtitle">
<table border="1" width="1050" cellpadding="5">
	<tr><th align="right" width="150" height="15">タイトル</th><td>${fn:escapeXml(article.title)}</td></tr>
	<tr><th align="right" width="150" height="15">要約投稿数</th><td >${article.mcount}</td></tr>
	<tr><th nowrap align="right" width="150" height="15">記事提供元の名称</th><td  nowrap>${article.source}</td></tr>
	<tr><th align="right" width="150" height="15">記事提供元のURL</th><td stylestyle="width: 70px;border: 1px solid #000;word-break: break-all;">
	      <a href="
	      <c:url value="${article.url}"/>">${article.url}</a></td></tr>
</table>
</p>
<pre> 他の人の要約に良い印象を持ったら、[きれいだね!]ボタンを押しましょう！</pre>

<c:forEach var="row" items="${requestScope['articles']}">
<div class="all">
<div class="left">
<table 	border="1" width="500" cellpadding="5">
<tr>
	<th align="right" width="150" height="15">投稿者</th>
	<td>${row.nam}</td>
</tr>
<tr>
	<th align="right" width="150" height="15">順位</th>
	 <!-- 前レコードと同順位か判定 -->
	 <c:choose>
		<c:when test="${row.pointbiutiful<point_biutiful}">
    	 <c:set var="rank" value="${rank+1}"/>
    	 <c:set var="point_biutiful" value="${row.pointbiutiful}"/>
    	</c:when>
	    <c:otherwise>
	     <c:set var="point_biutiful" value="${row.pointbiutiful}"/>
	    </c:otherwise>
	</c:choose>
	<td>${rank}位</td>
</tr>
<tr>
	<th align="right" width="150" height="15">投稿日時</th>
	<td><fmt:formatDate value="${row.sdat}"
		pattern="yyyy年MM月dd日 HH時mm分ss秒" /></td>
</tr>
<tr>
<td colspan="2" height="333" valign="top">
<div style="height:333px; overflow-y:auto;">
	${row.marticle}</td>
</div>
</tr>
<tr>
	<th align="right" width="80">文字数</th>
	<td>${row.mcharalen}</td>
</tr>
</table>
</div>
<div class="right">
<table style="float:left;" border="1" width="500" cellpadding="5">
<tr>
<td colspan="2" height="426" valign="top">
<div style="height:426px; overflow-x:hidden;">
	${row.particle}
</div>
</td>
</tr>
<tr>
	<th align="right" width="150" height="15">文字数</th>
	<td>${row.pcharalen}</td>
</tr>
</table>
</div>
<p style="clear:both;">
 <form method="POST" action="${requestScope['app.base']}/BbsNewAction?flagB=${row.mid}" name=frm >
    <input type="hidden" name="mid" value=${row.mid}/>
    <input type="submit" name="res" value="きれいだね！" />
    <input type="text" id="len" value =${row.pointbiutiful} name="charalen" size="5" maxlength="3" readonly="readonly"/>
    <br>
    <input type="button" value="記事を削除" onclick="location.href='../BbsDisplayA/${row.mid}?flag=del'" />
 
 </form>
</p>
</div>
</c:forEach>

<p style="clear:both;">
  <form>
   <span class="subtitle">
	<input type="button" value="要約する" onclick="location.href='../BbsDisplayA/${requestScope['article'].pid}?flag=res'" />
   </span>
  </form>
</p>





