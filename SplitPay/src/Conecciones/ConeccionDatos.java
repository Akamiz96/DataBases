/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conecciones;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author santi
 */
public class ConeccionDatos implements ConeccionBases {

    public void CrearUsuario() {
        String username = "is102317";
        String password = "bQmLIqN6HV";
        String thinConn = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR";
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR", "is102311", "DAbTstfDxY");
            CallableStatement cSmt = conn.prepareCall("{call RegistrarUsuario (?,?,?,?,?,?,?,?,?,?)");
            cSmt.setString(1, "santiash");
            // Revisar si el username nuevo que ingresan ya existe en la base de datos
            cSmt.setString(2, "Santiago");
            cSmt.setInt(3, 32065426);
            cSmt.setString(4, "snjsksam@gmail.com");
            cSmt.setString(5, "Cuenta_paypal");
            cSmt.setString(6, "salamanca");
            int anio = 1997, mes = 05, dia = 23;
            Date fecha = new Date(anio, mes, dia);
            cSmt.setDate(7, fecha);
            cSmt.setString(8, "M");
            cSmt.setString(9, "xdabcd");
            cSmt.setString(10, "N");
            cSmt.execute();
            System.out.println("Cambio el dato");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void CrearGrupo() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR", "is102311", "DAbTstfDxY");
            CallableStatement cSmt = conn.prepareCall("{call CrearGrupo (?,?,?)");
            cSmt.setString(1, "grupo_10");
            // Revisar si el nombre del grupo nuevo que ingresan ya existe en la base de datos
            cSmt.setInt(2, 21);
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            Date fechaDate = new Date(anio, mes, dia);
            cSmt.setDate(3, fechaDate);
            cSmt.execute();
            System.out.println("Cambio el dato");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
