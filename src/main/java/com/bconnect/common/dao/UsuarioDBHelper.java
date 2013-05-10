package com.bconnect.common.dao;

import com.bconnect.common.bean.UsuariosBean;
import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.bean.personal.UsuarioEsp1Bean;
import com.bconnect.common.db.DBConnection;
import com.bconnect.common.db.util.CommonCallableStatement;
import com.bconnect.common.db.util.CommonPreparedStatement;
import com.bconnect.common.db.util.CommonResultSet;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Esta clase del tipo DBHelper es usada para realizar llamadas a queries y stored
 * procedures asociados con datos de Usuarios
 * @author Jorge Rodriguez
 */
public class UsuarioDBHelper extends BaseDBHelper {

    public UsuarioDBHelper(String dbInstance) {
        super(dbInstance);
    }

    /**
     * Valida el acceso de un usuario a una aplicación determinada
     * @param loginTel login de inicio de sesión
     * @param password password asociado a su cuenta
     * @param cveProyecto clave con la que el proyecto está dada de alta en la DB
     * @return el id del usuario, en DB
     * @throws com.sitel.common.exception.DBException
     */
    public long validateAccess(String login, String password, String cveProyecto)
            throws DBException {

        DBConnection dbConnection = this.getConnection();

        long access = 0;
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
            callStmt = dbConnection.getCallableStatement(
                    commonResource.getString(CommonConstants.SP_LOGIN_USUARIO));
            callStmt.setString(1, login);
            callStmt.setString(2, password);
            callStmt.setString(3, cveProyecto);

            rs = callStmt.executeQuery();

            if (rs.next()) {
                access = rs.getLong(1);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al intentar verificar accesos de usuario");
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }
        return access;
    }

    public UsuarioBean findByNumeroNomina(String numeroNomina) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            String sql = commonResource.getString(CommonConstants.QUERY_GET_USUARIO_BY_NUMERO_NOMINA);
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_GET_USUARIO_BY_NUMERO_NOMINA));

            stmt.setString(1, numeroNomina);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public List findByIdDepto(long idDepto) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        List usuarios = new ArrayList();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_GET_USUARIO_BY_ID_DEPTO));

            stmt.setLong(1, idDepto);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
                usuarios.add(usuario);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuarios;
    }

    /**
     * Busca a un usuario en la DB por medio de su id
     * @param idusuario el id del usuario
     * @return Una instancia de Usuario llena y lista para usarse
     * @throws com.sitel.common.exception.DBException
     */
    public UsuarioBean findById(long idusuario) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            String sql = commonResource.getString(CommonConstants.SP_GET_USUARIO_BY_ID);
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.SP_GET_USUARIO_BY_ID));

            stmt.setLong(1, idusuario);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public List findUsuariosByIdProyectoPerfil(int idProyecto, String clavePerfil) throws DBException {
        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        List usuarios = new ArrayList();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_GET_USUARIOS_BY_ID_PROYECTO_PERFIL));

            stmt.setLong(1, idProyecto);
            stmt.setString(2, clavePerfil);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
                usuarios.add(usuario);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer el listado de empleados con perfil " +
                    clavePerfil + " y asignados al proyecto " + idProyecto);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuarios;
    }
/**
 * Método que consulta la tabla ST_EMPLEADONG y trae los datos de los usuarios
 * que tienen acceso a cierta aplicación segun su idProyecto
 * @param idProyecto Identificador de la aplicación
 * @return Retorna un List con los datos obtenidos de la consulta objetos de tipo
 * UsuarioBean
 * @throws DBException Excepción de base de datos
 */
    public List findUsuariosByIdProyecto(int idProyecto) throws DBException {
        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        List usuarios = new ArrayList();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_GET_USUARIOS_BY_ID_PROYECTO));

            stmt.setLong(1, idProyecto);
            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
                usuarios.add(usuario);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer el listado de empleados " +
                    "asignados al proyecto " + idProyecto);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuarios;
    }

    public UsuarioBean findByLogin(String login) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.SP_GET_USUARIO_BY_LOGIN));

            stmt.setString(1, login);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return usuario;
    }

    public UsuarioBean findByLoginTel(String loginTel) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.SP_GET_USUARIO_BY_LOGIN_TEL));

            stmt.setString(1, loginTel);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return usuario;
    }

    public UsuarioBean findByIdOld(long idusuario) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            String sql = commonResource.getString(CommonConstants.QUERY_GET_USUARIO_BY_ID_OLD);
            stmt = dbConnection.getCommonPreparedStatement(sql);

            stmt.setLong(1, idusuario);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public UsuarioBean findByLoginOld(String login) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            String sql = commonResource.getString(CommonConstants.QUERY_GET_USUARIO_BY_LOGIN_OLD);
            stmt = dbConnection.getCommonPreparedStatement(sql);

            stmt.setString(1, login);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public UsuarioBean findByLoginOldTable(String login) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            String sql = commonResource.getString(CommonConstants.QUERY_GET_USUARIO_BY_LOGIN_OLD_TABLE);
            stmt = dbConnection.getCommonPreparedStatement(sql);

            stmt.setString(1, login);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    /**
     * Busca a un usuario por medio de sus credenciales de inicio de sesión
     * @param loginTel login de inicio de sesión
     * @param password password asociado a su cuenta
     * @return Una instancia de Usuario llena y lista para usarse
     * @throws com.sitel.common.exception.DBException
     */
    public UsuarioBean findByLogin(String login, String password) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioBean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.SP_GET_USUARIO_BY_LOGIN_PASSWORD));

            stmt.setString(1, login);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = this.parseResultSetUsuario(rs);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    /**
     * Invoca un stored procedure que efectúa la tarea de cambio de password
     * @param idEmpleado el id de usuario
     * @param password el nuevo password a ser asociado con su cuenta
     * @return true si el update en la DB se efectuó exitosamente
     * @throws com.sitel.common.exception.DBException
     */
    public boolean changePassword(double idEmpleado, String password)
            throws DBException {

        boolean success = false;
        CommonCallableStatement cstmt = null;
        CommonResultSet rs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            cstmt = dbConnection.getCommonCallableStatement(
                    commonResource.getString(CommonConstants.SP_CHANGE_PASSWORD));

            cstmt.setDouble(1, idEmpleado);
            cstmt.setString(2, password);

            rs = cstmt.executeCommonQuery();

            if (rs.next()) {
                success = true;
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al intentar cambiar el password del usuario");
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }
        logger.info("El usuario con id: " + idEmpleado + " ha actualizado su password.");

        return success;
    }

    public UsuarioBean parseResultSetUsuario(CommonResultSet rs) throws SQLException {
        UsuarioBean usuario = new UsuarioBean();

        String nombre1 = rs.getString("strnombre1");
        String nombre2 = rs.getString("strnombre2");
        String apellido1 = rs.getString("strappaterno");
        String apellido2 = rs.getString("strapmaterno");

        int idempleado = rs.getInt("idEmpleado");
        String numeroEmpleado = rs.getString("strNumNomina");
        String strlogin = rs.getString("strlogin");
        String strpassword = rs.getString("strpassword");
        String dtfecvencpass = rs.getString("dtfecvencpass");
        int idDepto = rs.getInt("iddepto");
        int idEmpresa = rs.getInt("idempresa");
        int idarea = rs.getInt("idarea");
        int idperfil = rs.getInt("idperfil");
        String strcveperfil = rs.getString("strcveperfil");
        Date dtfechacreacion = rs.getDate("dtfechacreacion");
        int nivel = rs.getInt("intnivel");
        String strcreadopor = rs.getString("strcreadopor");
        int idJefe = rs.getInt("idJefe");
        String email = rs.getString("stremail");
        String loginTel = rs.getString("strLoginTel");
        String sexo = rs.getString("strSexo");
        String puesto = rs.getString("strpuesto");

        usuario.setIdUsuario(idempleado);
        usuario.setNumeroEmpleado(numeroEmpleado);
        usuario.setNombre1(nombre1);
        usuario.setNombre2(nombre2);
        usuario.setApellido1(apellido1);
        usuario.setApellido2(apellido2);
        usuario.setLogin(strlogin);
        usuario.setPassword(strpassword);
        usuario.setFechaExpiraPass(dtfecvencpass);
        usuario.setIdDepto(idDepto);
        usuario.setIdJefe(idJefe);
        usuario.setIdEmpresa(idEmpresa);
        usuario.setIdArea(idarea);
        usuario.setIdPerfil(idperfil);
        usuario.setCvePerfil(strcveperfil);
        usuario.setFechaCreacion(dtfechacreacion);
        usuario.setNivel(nivel);
        usuario.setCreadoPor(strcreadopor);
        usuario.setEmail(email);
        usuario.setLoginTel(loginTel);
        usuario.setSexo(sexo);
        usuario.setPuesto(puesto);

        return usuario;
    }

    public UsuarioEsp1Bean findNominaNombreHorarioJefeByLogin(String login) throws DBException {

        DBConnection dbConnection = this.getConnection();

        UsuarioEsp1Bean usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.FIND_USUARIO_NOMINA_NOMBRE_HORARIO_JEFE_BY_LOGIN));

            stmt.setString(1, login);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = new UsuarioEsp1Bean(login);
                usuario.setNomina(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setIdHorario(rs.getInt(3));
                usuario.setHorario(rs.getString(4));
                usuario.setIdSupervisor(rs.getInt(5));
                usuario.setSupervisor(this.findNombreById(usuario.getIdSupervisor()));
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public UsuarioEsp1Bean findNominaNombreHorarioJefeValByLoginValidador(String login, String validador) throws DBException {
        UsuarioEsp1Bean usuario = this.findNominaNombreHorarioJefeByLogin(login);
        usuario.setValidador(this.findNombreByLogin(validador));
        return usuario;
    }

    public String findNombreByLogin(String login) throws DBException {

        DBConnection dbConnection = this.getConnection();

        String usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.FIND_USUARIO_NOMBRE_BY_LOGIN));

            stmt.setString(1, login);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = rs.getString(1);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public String findNombreById(long idUsuario) throws DBException {

        DBConnection dbConnection = this.getConnection();

        String usuario = null;
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.FIND_USUARIO_NOMBRE_BY_ID));

            stmt.setLong(1, idUsuario);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = rs.getString(1);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuario;
    }

    public List<UsuariosBean> fidnUsuariosByProyectoActivoPerfil(String claveProyecto, int perfil) throws DBException {

        DBConnection dbConnection = this.getConnection();

        List<UsuariosBean> usuarios = new ArrayList<UsuariosBean>();
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.FIND_USUARIOS_BY_PROYECTO_ACTIVO_Y_PERFIL));

            stmt.setString(1,claveProyecto);
            stmt.setInt(2, perfil);

            rs = stmt.executeQuery();

            while (rs.next()) {
               UsuariosBean usuario = new UsuariosBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
               usuarios.add(usuario);
            }

        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al regresar los datos del usuario: " + sqle);
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }

        }

        return usuarios;
    }


}
