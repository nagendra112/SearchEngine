<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.HistoryResult"%>

<html>
<head>
    <link rel = "stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Your recent history</h1>
    <div class="resultsTable">
    <table border = 2>
        <tr>
            <td>Keyword</td>
            <td>Link</td>
        </tr>
        <%
            //get results from search servlet
            ArrayList<HistoryResult> results = (ArrayList<HistoryResult>) request.getAttribute("results");
            //iterate every data present in results array
            for(HistoryResult result : results){

        %>
        <tr>
            <td><%out.println(result.getKeyword());%></td>
            <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%></td>
        </tr>
        <%
            }
        %>
    </table>
    </div>
</body>
</html>

















