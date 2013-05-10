package com.bconnect.common.dao;

import com.bconnect.common.bean.UsuariosBean;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.bean.personal.UsuarioEsp1Bean;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.ResourcesManager;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class UsuarioDao extends BaseDao {

    private UsuarioDBHelper dbHelper;

    public UsuarioDao(String dbInstance) {
        super(dbInstance);
    }

//    public UsuarioDao() {
//        super(ResourcesManager.getApplicationResource().getString(CommonConstants.PROJECT_CATALOGO_DB_CONN_JNDI_NAME) != null ?
//                ResourcesManager.getApplicationResource().getString(CommonConstants.PROJECT_CATALOGO_DB_CONN_JNDI_NAME) : CommonConstants.DB_INFORMIX_CATALOGO);
//    }
    
    public UsuarioDao() {
        logger = CommonLogger.getLogger(this.getClass());
        String jndiConnectionName = null;
        try {
            jndiConnectionName = ResourcesManager.getApplicationResource().getString(CommonConstants.PROJECT_CATALOGO_DB_CONN_JNDI_NAME);
        } catch (Exception e) {
            jndiConnectionName = CommonConstants.DB_INFORMIX_CATALOGO;
        }
        this.dbInstance = jndiConnectionName;
        this.initializeDBHelper();
    }

    protected void initializeDBHelper() {
        dbHelper = new UsuarioDBHelper(dbInstance);
    }

    /**
     * Valida si el usuario tiene acceso a un determinado proyecto
     * @param loginTel el nombre de usuario para iniciar sesion
     * @param password el password asociado al login
     * @param cveProyecto la clave que identifica al proyecto que se intenta
     * accesar
     * @return true si el usuario tiene privilegios de acceso
     */
    public long validateAccess(String login, String password, String cveProyecto) {
        long access = 0;
        try {
            access = dbHelper.validateAccess(login, password, cveProyecto);
        } catch (DBException ex) {
            logger.error("Ocurrio un error al verificar el acceso para el usuario");
            ex.printStackTrace();
        }
        return access;
    }

    public UsuarioBean findByNumeroNomina(String numeroNomina) {
        ResultSet rs = null;
        UsuarioBean usuario = null;

        try {
            usuario = dbHelper.findByNumeroNomina(numeroNomina);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con número de nómina: "
                    + numeroNomina + dBException);
        }
        return usuario;
    }

    public List findByIdDepto(long idDepto) {
        ResultSet rs = null;
        List usuarios = null;

        try {
            usuarios = dbHelper.findByIdDepto(idDepto);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar la lista de usuarios del Depto con id: "
                    + idDepto + dBException);
        }
        return usuarios;
    }

    public List findUsuariosByIdProyectoPerfil(int idProyecto, String clavePerfil) {
        List usuarios = null;
        try {
            usuarios = dbHelper.findUsuariosByIdProyectoPerfil(idProyecto, clavePerfil);
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        return usuarios;
    }
    /**
     * Método que devuelve una lista con los usuarios que tienen acceso a determinada aplicación
     * @param idProyecto Identificador del aplicativo
     * @return Retorna un List con los datos de los usuarios que tienen acceso a esa aplicación
     */
    public List findUsuariosByIdProyecto(int idProyecto) {
        List usuarios = null;
        try {
            usuarios = dbHelper.findUsuariosByIdProyecto(idProyecto);
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Basado en el id, va a la BD y regresa la instancia de Usuario
     * correspondiente
     * @param idUsuario el id del usuario
     * @return una instancia de Usuario con valores del usuario
     */
    public UsuarioBean findById(long idUsuario) {
        UsuarioBean usuario = null;

        try {
            usuario = dbHelper.findById(idUsuario);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con id: "
                    + idUsuario + dBException);
        }
        return usuario;
    }

    public UsuarioBean findByLogin(String login) {
        ResultSet rs = null;
        UsuarioBean usuario = null;

        try {
            usuario = dbHelper.findByLogin(login);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login: "
                    + login + dBException);
        }
        return usuario;
    }

    public UsuarioBean findByLoginTel(String loginTel) {
        UsuarioBean usuario = null;
        try {
            usuario = dbHelper.findByLoginTel(loginTel);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login tel: "
                    + loginTel + dBException);
        }
        return usuario;
    }

    public UsuarioBean findByIdOld(long idUsuario) {
        ResultSet rs = null;
        UsuarioBean usuario = null;

        try {
            usuario = dbHelper.findByIdOld(idUsuario);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con id: "
                    + idUsuario + dBException);
        }
        return usuario;
    }

    public UsuarioBean findByLoginOld(String login) {
        ResultSet rs = null;
        UsuarioBean usuario = null;

        try {
            usuario = dbHelper.findByLoginOld(login);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login: "
                    + login + dBException);
        }
        return usuario;
    }

    public UsuarioBean findByLoginOldTable(String login) {
        ResultSet rs = null;
        UsuarioBean usuario = null;

        try {
            usuario = dbHelper.findByLoginOldTable(login);

        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login: "
                    + login + dBException);
        }
        return usuario;
    }

    /**
     * Basado en el id, va a la BD y regresa la instancia de Usuario
     * correspondiente
     * @param loginTel el nombre de usuario para iniciar sesion
     * @param password el password asociado al login
     * @return una instancia de Usuario con valores del operador
     */
    public UsuarioBean findByLogin(String login, String password) {
        ResultSet rs = null;
        UsuarioBean usuario = null;
        try {
            usuario = dbHelper.findByLogin(login, password);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login: "
                    + login + dBException);
        }
        return usuario;
    }

    /**
     * Invoca un stored procedure que efectúa la tarea de cambio de password
     * @param idEmpleado el id de usuario
     * @param password el nuevo password a ser asociado con su cuenta
     * @return true si el update en la DB se efectuó exitosamente
     */
    public boolean changePassword(long idEmpleado, String password) {
        boolean success = false;

        double idEmpleadoDouble = Double.parseDouble(String.valueOf(idEmpleado));

        try {
            success = dbHelper.changePassword(idEmpleadoDouble, password);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al intentar cambiar el password" + dBException);
        }

        return success;
    }

    public UsuarioEsp1Bean findNominaNombreHorarioJefeByLogin(String login) {
        UsuarioEsp1Bean usuario = null;
        try {
            usuario = dbHelper.findNominaNombreHorarioJefeByLogin(login);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login: " +
                    login + dBException);
        }
        return usuario;
    }

    public UsuarioEsp1Bean findNominaNombreHorarioJefeValByLoginValidador(String login, String validador) {
        UsuarioEsp1Bean usuario = null;
        try {
            usuario = dbHelper.findNominaNombreHorarioJefeValByLoginValidador(login, validador);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el usuario con login: " +
                    login + dBException);
        }
        return usuario;
    }

    public String findNombreByLogin(String login) {
        String usuario = null;
        try {
            usuario = dbHelper.findNombreByLogin(login);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el nombre del usuario con login: " +
                    login + dBException);
        }
        return usuario;
    }

    public String findNombreById(long idUsuario) {
        String usuario = null;
        try {
            usuario = dbHelper.findNombreById(idUsuario);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al regresar el nombre del usuario con id: " +
                    idUsuario + dBException);
        }
        return usuario;
    }

    public List<UsuariosBean> fidnUsuariosByProyectoActivoPerfil(String claveProyecto, int perfil){
        List<UsuariosBean> usuarios = null;
        try {
            usuarios = dbHelper.fidnUsuariosByProyectoActivoPerfil(claveProyecto, perfil);
        } catch (DBException dBException) {
            logger.error("Ocurrio un error al obtener los usario del proyecto " + claveProyecto + " y perfil " + perfil + " "
                    + dBException);
        }
        return usuarios;
    }
}
