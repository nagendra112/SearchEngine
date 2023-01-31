<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.SearchResult"%>
<html>
<head>
    <link rel = "stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <form action = "Search">
        <input type = "text" name = "keyword">
        <button type = "submit">Search</button>
    </form>
    <h1>Your results are ready</h1>
    <div class="resultsTable">
    <table border = 2>
        <tr>
            <td>Title</td>
            <td>Link</td>
        </tr>
        <%
            //get results from search servlet
            ArrayList<SearchResult> results = (ArrayList<SearchResult>) request.getAttribute("results");
            //iterate every data present in results array
            for(SearchResult result : results){

        %>
        <tr>
            <td><%out.println(result.getPageTitle());%></td>
            <td><a href ="<%out.println(result.getPageLink());%>"><%out.println(result.getPageLink());%></td>
        </tr>
        <%
            }
        %>
    </table>
    </div>
</body>
</html>
