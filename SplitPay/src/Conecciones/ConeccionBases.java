/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conecciones;

import java.sql.Date;

/**
 *
 * @author santi
 */
public interface ConeccionBases {
    void CrearUsuario();
    void CrearGrupo();
    void CrearLiderGrupo(int idGrupo, int idnuevoLider,Date date);
}
