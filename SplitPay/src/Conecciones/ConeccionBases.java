/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conecciones;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public interface ConeccionBases {
    void CrearUsuario();
    void CrearGrupo();
    void agregarRecibo(String ubi,String nombre,String comentarios,int costo,int idUser,int IdGrupo) throws SQLException, FileNotFoundException;
}
