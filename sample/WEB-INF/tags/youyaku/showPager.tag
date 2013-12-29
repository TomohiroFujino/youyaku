<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="win" tagdir="/WEB-INF/tags/youyaku" %>
<%@ attribute name="increment" required="true" %>
<c:choose>
	<c:when test="${!empty param['cnt'] and !empty param['page']}">
		<c:set var="cnt"  value="${param['cnt']}" />
		<c:set var="page" value="${param['page']}" />
	</c:when>
	<c:otherwise>
		<c:set var="cnt"  value="0" />
		<c:set var="page" value="1" />
	</c:otherwise>
</c:choose>
<c:if test="${page > 1}">
	<a href="${requestScope['app.base']}/BbsIndex?cnt=${cnt-5}&page=${page-1}">
		<img src="${requestScope['app.context']}/youyaku/img/prev.gif"
			alt="前ページへ" border="0" /></a>&nbsp;
</c:if>
&nbsp;
<img src="${requestScope['app.context']}/youyaku/img/note.gif"
		width="20" height="20"  border="0" />
<a href="${requestScope['app.base']}/BbsNew">
要約する文章の作成		
</a>
<img src="${requestScope['app.context']}/youyaku/img/note.gif"
		width="20" height="20"  border="0" />
&nbsp;
<c:if test="${increment%5 ==0 and increment!=0}">
	&nbsp;
	<a href="${requestScope['app.base']}/BbsIndex?cnt=${cnt+increment}&page=${page+1}">
		<img src="${requestScope['app.context']}/youyaku/img/next.gif"
			alt="次ページへ" border="0" /></a>
</c:if>
