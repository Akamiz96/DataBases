/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conecciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author santi
 */
public class ConeccionDatos implements ConeccionBases {
     String username = "is102317";
    String password = "bQmLIqN6HV";
String thinConn = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR";

    public void CrearUsuario(String user_name,String nombre,int telefono,String email,String paypal,String apellido,Date fecha, String genero,String contrasena) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR", "is102317", "bQmLIqN6HV");
            CallableStatement cSmt = conn.prepareCall("{call RegistrarUsuario (?,?,?,?,?,?,?,?,?,?)");
            cSmt.setString(1, user_name);
            // Revisar si el username nuevo que ingresan ya existe en la base de datos
            cSmt.setString(2, nombre);
            cSmt.setInt(3, telefono);
            cSmt.setString(4, email);
            cSmt.setString(5, paypal);
            cSmt.setString(6, apellido);
           // int anio = 1997, mes = 05, dia = 23;
           // Date fecha = new Date(anio, mes, dia);
            cSmt.setDate(7, fecha);
            cSmt.setString(8, genero);
            cSmt.setString(9, contrasena);
            cSmt.setString(10, "N");
            cSmt.execute();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void CrearGrupo(String nombre,int duenoID) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR", "is102317", "bQmLIqN6HV");
            CallableStatement cs = conn.prepareCall("{call CrearGrupo (?,?,?)");
            cs.setString(1, nombre);
            // Revisar si el nombre del grupo nuevo que ingresan ya existe en la base de datos
            cs.setInt(2, duenoID);
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            Date fechaDate = new Date(anio, mes, dia);
            cs.setDate(3, fechaDate);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
     public void agregarRecibo(String ubi,String nombre,String comentarios,int costo,int idUser,int IdGrupo) throws SQLException, FileNotFoundException{

    	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR", "is102317", "bQmLIqN6HV");
    	File ubicacion = new File(ubi);
    	FileInputStream archivo = new FileInputStream(ubicacion);
    	System.out.println(nombre);
    	System.out.println(costo);
    	System.out.println(IdGrupo);
    	PreparedStatement ps = conn.prepareStatement("insert into CUENTA values(CU_id_SEQ.NEXTVAL,?,?,?,sysdate,?,?,?)");

    	ps.setString(1, nombre);
    	ps.setInt(2, costo);
    	ps.setBinaryStream(3, archivo,ubicacion.length());
    	ps.setInt(4, IdGrupo);
    	ps.setInt(5, idUser);
    	ps.setString(6, comentarios);
    	ps.execute();
}
    public void CrearLiderGrupo(int idGrupo, int idnuevoLider,Date dateIngreso){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@orion.javeriana.edu.co:1521:PUJDISOR", "is102317", "bQmLIqN6HV");
            CallableStatement cs = conn.prepareCall("{call CrearLiderGrupo (?,?,?)");
            cs.setDate(1,dateIngreso) ;
            cs.setInt(2, idGrupo);
            cs.setInt(3, idnuevoLider);
            cs.execute();
            System.out.println("Cambio el dato");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

}
