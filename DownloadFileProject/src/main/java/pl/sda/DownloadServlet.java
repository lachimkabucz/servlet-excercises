package pl.sda;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


@WebServlet(name = "DownloadServer", value = "/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>SDA - Download servlet</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>File name to download:</p>\n" +
                "<form method=\"post\" action=\"/download\">\n" +
                "    <label>\n" +
                "        <input name=\"file\" type=\"text\">\n" +
                "    </label>\n" +
                "    <input type=\"submit\" value=\"Download\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileDownload = request.getParameter("file");

        if (StringUtils.isBlank(fileDownload)) {
            response.getWriter().println("" +
                    "<html>" +
                    "<p>Brak nazwy pliku</p>" +
                    "</html>");
        }


        ServletContext servletContext = getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream("/" + fileDownload);
        if (inputStream == null) {
            response.getWriter().println("File not available for download");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileDownload + "\"");

        ServletOutputStream responseOutputStream = response.getOutputStream();

        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            responseOutputStream.write(buffer, 0, bytesRead);


            responseOutputStream.flush();
            responseOutputStream.close();
        }
    }
}