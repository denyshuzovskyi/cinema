<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/includes/header.jsp" />
<jsp:include page="/jsp/includes/column_left.jsp" />


  <div id="body">

    <c:if test="${empty films}">
      <jsp:include page="/controller?command=get_film_list" />
    </c:if>

    <%@ include file="/jsp/includes/film_list.jsp" %>

  </div>
  

<jsp:include page="/jsp/includes/footer.jsp" />