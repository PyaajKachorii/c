/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.ws002;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author KEVIN
 */
@WebService(serviceName = "StudentService002")
public class StudentService002 {

    /**
     * This is a sample web service operation
     */
    @WebMethod
    public String registerStudent(String name, String rollNo, String address, String gender) {
        return "<h2>Stundet Registered Succesfullly.</h2>";
    }
}
