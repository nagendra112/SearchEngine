package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //get parameter called keyword from request
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        try {
            //establish/get connection to database
            Connection connection = DatabaseConnection.getConnection();
            //save keyword for history purpose and link associated into history table
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?, ?)");
            preparedStatement.setString(1, keyword);;
            preparedStatement.setString(2, "http://localhost:8081/SearchEngine/Search?keyword="+keyword);
            preparedStatement.executeUpdate();
            //executing a executeQuery related to keyword and get the results
            ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle, pagelink, (length(lower(pagetext))-length(replace(lower(pagetext), '" + keyword + "', '')))/length('" + keyword + "') as countoccurence from pages order by countoccurence desc limit 30;");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            //iterate through resultSet and save all elements in result arr
            while (resultSet.next()){
                SearchResult searchResult = new SearchResult();
                //getting pagetitle
                searchResult.setPageTitle(resultSet.getString("pageTitle"));
                //getting pagelink
                searchResult.setPageLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }
            //display results in console
            for (SearchResult searchResult : results){
                System.out.println(searchResult.getPageTitle()+" "+searchResult.getPageLink()+"\n");
            }
            //set attribute of request with results arraylist
            request.setAttribute("results", results);
            //forward request to search.jsp file
            request.getRequestDispatcher("/search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        catch (ServletException servletException){
            servletException.printStackTrace();
        }
    }
}





















