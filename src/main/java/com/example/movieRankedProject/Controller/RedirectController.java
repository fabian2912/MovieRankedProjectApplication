package com.example.movieRankedProject.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
//@RequestMapping("/redirect")
@WebServlet(name="RedirectController", urlPatterns = "/redirect")
public class RedirectController  extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/movie-list").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/movie-list").forward(request, response);

        // routing into /movie-list page; also taking the request and response of current request and sending it over - all the request data is transferred
        // note: the response will contain the actual data to display to the user (the actual HTML)
    }

}
