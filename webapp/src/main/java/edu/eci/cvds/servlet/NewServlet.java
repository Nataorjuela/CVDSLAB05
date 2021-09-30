package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.Writer;
@WebServlet(
        urlPatterns = "/NewServlet"
)
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Todo> todoList;

    public NewServlet() {
        super();
        todoList = new ArrayList<Todo>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        todoList= new ArrayList<Todo>();;
        Writer responseWriter = resp.getWriter();
        try{
            Optional<String> Id = Optional.ofNullable(req.getParameter("Id"));
            Todo todo = Service.getTodo(Integer.parseInt(Id.get()));
            todoList.add(todo);
            if(todoList==null){
                throw new Exception();
            }
            responseWriter.write(Service.todosToHTMLTable(todoList));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (MalformedURLException ME){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeInternalServerError(resp.getWriter());
        }
        catch (NumberFormatException NE){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeBadRequest(resp.getWriter());
        }
        catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writeNotFound(resp.getWriter());

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        todoList= new ArrayList<Todo>();;
        Writer responseWriter = resp.getWriter();
        try{
            Optional<String> Id = Optional.ofNullable(req.getParameter("Id"));
            Todo todo = Service.getTodo(Integer.parseInt(Id.get()));
            todoList.add(todo);
            if(todoList==null){
                throw new Exception();
            }
            responseWriter.write(Service.todosToHTMLTable(todoList));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (MalformedURLException ME){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeInternalServerError(resp.getWriter());
        }
        catch (NumberFormatException NE){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeBadRequest(resp.getWriter());
        }
        catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writeNotFound(resp.getWriter());

        }
    }

    private void writeInternalServerError(Writer w) {
        try {
            w.write(new StringBuilder("<h1>")
                    .append("500: Internal Server Error")
                    .append("</h1>")
                    .toString()
            );
        }catch (IOException ioe) {}
    }

    private void writeBadRequest(Writer w) {
        try {
            w.write(new StringBuilder("<h1>")
                    .append("400: Bad Request!")
                    .append("</h1>")
                    .toString()
            );
        }catch (IOException ioe) {}
    }

    private void writeNotFound(Writer w) {
        try {
            w.write(new StringBuilder("<h1>")
                    .append("404: Not Found")
                    .append("</h1>")
                    .toString()
            );
        }catch (IOException ioe) {}
    }
}