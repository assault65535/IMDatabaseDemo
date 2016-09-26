import Util.MessageRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tnecesoc on 2016/9/7.
 */
@WebServlet(name = "GetTopicMessageServlet", urlPatterns = "/get-message")
public class GetTopicMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic = request.getParameter("topic");
        String rawDate = request.getParameter("later-than");

        Date date = null;

        try {
            if (rawDate != null) {
                date = new SimpleDateFormat("y-M-d-H-m-s").parse(rawDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        if (rawDate != null) {
            if (date != null) {
                out.print(MessageRepository.findMessageJSONListLaterThan(topic, date));
            }
        } else {
            out.print(MessageRepository.findMessageJSON(topic));
        }

        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
