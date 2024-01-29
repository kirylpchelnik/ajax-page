package servlet;

import entity.Auto;
import entity.Taxi;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


public class ListTaxiServlet extends HttpServlet {
    private void viewAccount(Taxi list, PrintWriter out) {

        out.println("<table border \"1\" >");
        out.println("<tr  >" + "<th>Действия</th>" + "<th>Марка</th>" + "<th>Модель</th>" + "<th>Время прибытия</th>" + "</tr>");
        for (int i = 0; i < list.listAutos.size(); i++) {
            Auto auto = list.listAutos.get(i);
            out.println("<tr>" + "<td> "
                    + "<button id='select" + auto.Id + "' class='btnSelect' onclick='{selectTaxi(" + auto.Id + ");}' value='" + auto.Id + "'>Выбрать</button>"
                    + "<button id='delete" + auto.Id + "' class='btnDelete' onclick='{deleteTaxi(" + auto.Id + ");}' value='" + auto.Id + "'>Удалить</button>"
                    + "</td>" + "<td>" + auto.Marka + "</td>" + "<td>" + auto.Model + "</td>" + "<td>" + auto.Time + "</td>" + "</tr>");
        }
        out.println("</table>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String strButton = request.getParameter("button");
            HttpSession session = request.getSession();
            Taxi taxi = (Taxi) session.getAttribute("Taxi");
            if (taxi == null) {
                taxi = new Taxi();
                taxi.createList();
                session.setAttribute("Taxi", taxi);
            }
            if ((strButton != null) && (strButton.equals("delete"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                taxi.Remove(id);
                session.removeAttribute("Taxi");
                session.setAttribute("Taxi", taxi);
            }//*/

            viewAccount(taxi, out);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
