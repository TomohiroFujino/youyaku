<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="win" tagdir="/WEB-INF/tags/chap5" %>
<%@ variable name-given="increment" scope="AT_END"
	variable-class="java.lang.Integer" %>
<c:choose>
	<c:when test="${!empty param['cnt']}">
		<c:set var="cnt"  value="${param['cnt']}" />
	</c:when>
	<c:otherwise>
		<c:set var="cnt"  value="0" />
	</c:otherwise>
</c:choose>
<sql:setDataSource var="db" dataSource="jdbc/Sample" />
<c:set var="sql" value="SELECT p_id,title,pdat,m_count FROM v_bbs_display GROUP BY p_id ORDER BY pdat DESC,m_count DESC LIMIT ${cnt},5" />
<sql:query var="rs" dataSource="${db}">${sql}</sql:query>
<table width="95%" cellspacing="10">
<c:forEach var="row" items="${rs.rows}">
	<tr>
	<td bgcolor="#F5FFFF">
				<c:choose>
					<c:when test="${row['m_count']=='0'}">
					        ${row['p_id']}：
							<a href="${requestScope['app.base']}/BbsDisplayB/${row['p_id']}">
							${fn:escapeXml(row['title'])}</a>&nbsp;
					</c:when>
					<c:otherwise>
					        ${row['p_id']}：
						    <a href="${requestScope['app.base']}/BbsDisplayA/${row['p_id']}">
							${fn:escapeXml(row['title'])}</a>&nbsp;	
					</c:otherwise>
		         </c:choose>			
		(<fmt:formatDate value="${row['pdat']}" 
		pattern="yyyy年MM月dd日 HH時mm分ss秒" />) 
	</td>
	</tr>
</c:forEach>
</table>
<c:set var="increment" value="${rs.rowCount}" />
