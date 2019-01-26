package pl.sda;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", value = "/helloWorld")
public class HelloWorldServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        writer.println(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\" dir=\"ltr\">\n" +
                        "<head>\n" +
                        "<meta charset=\"utf-8\">\n" +
                        "<title>Statyczna strona</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<p>Dzisiaj jest 26 stycznia 2019</p>\n" +
                        "</body>\n" +
                        "</html>\n"
        );


    }
}
