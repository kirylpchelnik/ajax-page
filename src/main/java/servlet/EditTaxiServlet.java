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

public class EditTaxiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String stID = request.getParameter("id");
            int id;
            if (stID == null) {
                id = -1;
            } else {
                id = Integer.parseInt(stID);
            }


            HttpSession session = request.getSession();
            Taxi taxi = (Taxi) session.getAttribute("Taxi");
            String button = "";
            String Marka = "";
            String Model = "";
            String Time = "";
            int ID;
            int index = taxi.findId(id);

            if (index >= 0) {
                button = request.getParameter("button");
                if (button == null) {
                    Auto auto = taxi.listAutos.get(index);
                    button = "update";
                    Marka = auto.Marka;
                    Model = auto.Model;
                    Time = auto.Time;
                    ID = auto.Id;
                } else {

                    if (button.equals("update")) {
                        Marka = request.getParameter("Marka");
                        Model = request.getParameter("Model");
                        taxi.Save(id, Marka, Model, Time);
                        session.removeAttribute("Taxi");
                        session.setAttribute("Taxi", taxi);
                        String path = "index.jsp";
                        response.sendRedirect(path);
                    }

                }
            } else {
                button = request.getParameter("button");
                if (button == null) {
                    button = "add";
                } else {

                    if (button.equals("add")) {
                        Marka = request.getParameter("Marka");
                        Model = request.getParameter("Model");
                        Time = request.getParameter("Time");
                        taxi.Add(Marka, Model, Time);
                        session.removeAttribute("Taxi");
                        session.setAttribute("Taxi", taxi);
                        String path = "index.jsp";
                        response.sendRedirect(path);
                    }

                }
            }//*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Редактирование</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form action=\"EditTaxiServlet\" method=\"post\">");
            out.println("<center><input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
            out.println("<input type=\"hidden\" name=\"button\" value=\"" + button + "\">");
            out.println("<label>Марка</label><br>");
            out.println("<input type=\"text\" name=\"Marka\" value=\"" + Marka + "\" >");
            out.println("<br>");
            out.println("<label>Модель</label><br>");
            out.println("<input type=\"text\" name=\"Model\" value=\"" + Model + "\" >");
            out.println("<br>");
            out.println("<label>Время прибытия</label><br>");
            out.println("<input type=\"text\" name=\"Time\" value=\"" + Time + "\" >");
            out.println("<br>");
            out.println("<a href=\"index.jsp\">Вернуться назад</a>");
            out.println("<input type=\"submit\" value=\"Сохранить\"></center>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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

