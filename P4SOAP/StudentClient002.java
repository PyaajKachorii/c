package com.demo.student002;

import com.demo.ws002.StudentService002_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

public class StudentClient002 extends HttpServlet {
    
    @WebServiceRef(wsdlLocation="http://localhost:8080/JAX2/StudentService002?wsdl")
    private  StudentService002_Service service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String rollNo =  request.getParameter("rollNo");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        
        String result = registerStudent(name, rollNo, address, gender);
        
        out.println(result);
        out.println("<h3> Name: </h3>" + name);
        out.println("<br><br>");
        out.println("<h3> Roll No: </h3>" + rollNo);
        out.println("<br><br>");
        out.println("<h3> Address: </h3>" + address);
        out.println("<br><br>");
        out.println("<h3> Gender: </h3>" + gender);
    }
    
    private String registerStudent(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) {
        com.demo.ws002.StudentService002 port = service.getStudentService002Port();
        return port.registerStudent(arg0, arg1, arg2, arg3);
    }
}