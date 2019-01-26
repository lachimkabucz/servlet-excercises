package pl.sda;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet(name = "CourseSelectionServlet", value = "/courseSelection")
public class CourseSelectionServlet extends HttpServlet {


    Map<String, Collection<String>> course = new HashMap<>();

    public CourseSelectionServlet() {
        course.put("PROGRAMMER", Arrays.asList("Java", "C#", "C++"));
        course.put("TESTER", Arrays.asList("Selenium", "Ranorex"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseType = request.getParameter("courseType");

        PrintWriter writer = response.getWriter();
        writer.println(
                "<html>" +
                        "<body style=\"background-color: darkslategray; font-family: Garuda; color: azure;\">" +
                        "<h1>Selected course type: " + courseType + "</h1>" +
                        "<p>Select category:</p>"+
                        "<select>" +
                         course.get(courseType).stream()
                         .map(course -> "<option>" + course + "</option>")
                         .collect(Collectors.joining()) +
                        "</select>" +
                        "</body>" +
                        "</html>");
    }
}
