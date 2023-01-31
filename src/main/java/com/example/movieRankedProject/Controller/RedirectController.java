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

//    @WebServlet("/redirect")
//    public class ServletRedirect extends HttpServlet {
//
//        @Override
//        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            Logger logger = LoggerFactory.getLogger(MovieController.class);
//            logger.info("in /redirect, sending redirect to movie-list");
//
//            RequestDispatcher dispatcher = getServletContext()
//                    .getRequestDispatcher("/movie-list");
//            dispatcher.forward(req, resp);
////            resp.sendRedirect("http://localhost:8080/movie-list");
//        }
//
//    }
}
