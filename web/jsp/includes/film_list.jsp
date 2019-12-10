<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- ... --%>
<c:forEach var="element" items="${films}" varStatus="status">
    <tr>
        <td>
            <img src="data:image/jpg;base64,${element.getPoster().getBase64Image()}" alt="Poster" width="130" height="180" align="left">
        </td>
        <td>
            Title: ${element.title}<br>
            Genre: ${element.genre}<br>
            Director: ${element.director}<br>
            Writer: ${element.writer}<br>
            Actors: ${element.actors}<br>
            Country: ${element.country}<br>
            Release date: ${element.release_date}<br>
            Duration: ${element.duration_minutes}min<br>
            Age limit: ${element.age_limit}+<br>
            Description: ${element.description}<br>
        </td>
        <br>
    </tr>
</c:forEach>
<%-- ... --%>