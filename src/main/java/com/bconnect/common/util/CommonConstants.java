package com.bconnect.common.util;

/**
 *
 * @author jorge.rodriguez
 */
public interface CommonConstants {
    
    public static final String PROJECT_NAME = "project.name";
    public static final String PROJECT_CODE = "project.code";
    public static final String PROJECT_ACCESS_LEVEL = "project.access.level";
    public static final String PROJECT_LOG_FILE_NAME = "project.log.fileName";
    public static final String PROJECT_WEB_MAIN_COLOR = "project.web.color";
    public static final String PROJECT_CATALOGO_DB_CONN_JNDI_NAME = "project.db.catalogo.conn.jndi";
    
    public static final String APPLICATION_DEFAULT_JSP_BUNDLE = "myApplication";
    public static final String APPLICATION_STRUTS_ERRORS_BUNDLE = "myStrutsErrorMessages";

    // Recursos de la aplicacion
    public static final String BASE_RESOURCE_FILE = "com.bconnect.common.resource.BaseResource";
    
    public static final String COMMON_SQL_RESOURCE_FILE = "com.bconnect.common.resource.commonSQLFile";
    public static final String APPLICATION_RESOURCE_FILE = "com.bconnect.common.resource.applicationFile";
    public static final String SQL_RESOURCE_FILE = "com.bconnect.common.resource.sqlFile";
    public static final String MAIL_RESOURCE_FILE = "com.bconnect.common.resource.mailFile";
    public static final String LOG4J_RESOURCE_FILE = "com.bconnect.common.resource.log4JFile";
    public static final String LOG4J_XML_DEFAULT_RESOURCE_FILE = "com/bconnect/common/resource/log4j.xml";
    public static final String LOG4J_XML_RESOURCE_FILE = "/com/bconnect/application/resource/log4j.xml";
    public static final String STRUTS_ERRORS_RESOURCE_FILE = "com.bconnect.common.resource.strutsFile";
    public static final String STRUTS_APPLICATION_ERRORS_RESOURCE_FILE = "com.bconnect.common.resource.applicationStrutsFile";

    public static final String CUSTOM_LOG4J_RESOURCE_FILE = "com.bconnect.common.resource.customLog4JFile";
    
    // Logueo
    public static final String LOGGING_RESOURCE = "com.bconnect.common.resource.Log4jResource";
    public static final String LOGGING_LEVEL = "log4j.loggingLevel";
    public static final String LOGGING_LAYOUT_PATTERN = "log4j.loggingPattern";
    public static final String LOGGING_DATE_PATTERN = "log4j.loggingDatePattern";
    public static final String LOGGING_FILE_NAME = "com.bconnect.common.logging.fileName";
    
    // EMAIL
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    
    // FORWARDS
    public static final String FORWARD_SUCCESS = "success";
    public static final String FORWARD_ERROR = "error";
    public static final String FORWARD_CHANGE_PASSWORD = "changePassword";
    public static final String FORWARD_PDF_SERVLET = "pdfServlet";
    
    // Conexiones a BD
    public static final String DB_INFORMIX_CATALOGO = "jdbc/catalogo";
    public static final String DB_INFORMIX_APPLICATION = "jdbc/applicationConnection";

    public static final String CONNECTION_POOL_TYPE_PARAMETER = "com.bconnect.common.db.pool.type";
    public static final int CONNECTION_POOL_TYPE_APPLICATION = 1;
    public static final int CONNECTION_POOL_TYPE_CONTAINER = 2;
    public static final String DB_DRIVER_NAME_INFORMIX = "com.informix.jdbc.IfxDriver";
    public static final int DB_DEFAULT_POOL_SIZE_MIN = 2;
    public static final int DB_DEFAULT_POOL_SIZE_MAX = 5;
    public static final int DB_DEFAULT_ACTIVE_MAX = 5;
    public static final int DB_DEFAULT_IDLE_MAX = 2;
    public static final int DB_DEFAULT_WAIT_MAX = 10000;
    
    // Fechas
    public static final String FECHA_FORMATO_YYYYMMDD = "yyyy-MM-dd";
    public static final String FECHA_FORMATO_YYYYMMDD_INT = "yyyyMMdd";
    public static final String FECHA_FORMATO_YYYYMMDD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FECHA_FORMATO_YYYYMMDD_HH_MM_SS_2 = "yyyy/MM/dd HH:mm:ss";
    public static final String FECHA_FORMATO_YYYYMMDD_HH_MM = "yyyy/MM/dd HH:mm";
    public static final String FECHA_FORMATO_YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String FECHA_FORMATO_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FECHA_FORMATO_MMDDYYYY = "MM/dd/yyyy";
    public static final String FECHA_FORMATO_DDMMYYYY = "dd/MM/yyyy";
    public static final String FECHA_FORMATO_YYMMDD = "yyMMdd";
    public static final String FECHA_FORMATO_DDMMYY = "ddMMyy";
    public static final String FECHA_FORMATO_MMDDYYYY_HHMM = "MM/dd/yyyy HH:mm";
    public static final String FECHA_FORMATO_DB = "yyyy-MM-dd HH:mm:ss.S";
    public static final String FECHA_FORMATO_WEB_DDMMYYYY_HHMM = "d MMM, yyyy HH:mm";
    public static final String FECHA_FORMATO_WEB_MMM_D_YYYY = "MMM d, yyyy";
    public static final String FECHA_FORMATO_WEB_D_MMM_YYYY = "d MMM, yyyy";
    public static final String FECHA_FORMATO_WEB_MMM_D_YYYY_HHMM = "MMM d, yyyy HH:mm";
    public static final String FECHA_FORMATO_DDMMYYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
    
    public static final String HORA_FORMATO_HHMM = "HHmm";
    public static final String HORA_FORMATO_HHMM_2= "HH:mm";
    
    // ATRIBUTOS
    public static final String ATRIBUTO_USUARIO = "usuario";
    public static final String ATRIBUTO_ID_USUARIO = "idUsuario";
    public static final String ATRIBUTO_ID_PARTICIPANTE = "idParticipante";
    public static final String ATRIBUTO_PARTICIPANTE = "participante";
    public static final String ATRIBUTO_ERROR_LOGIN = "errorMessage";
    public static final String ATRIBUTO_PROYECTO = "proyecto";
    public static final String ATRIBUTO_PDF_BUILDER = "pdfBuilder";
    public static final String ATRIBUTO_JASPER_REPORT = "jasperReportObject";
    public static final String ATRIBUTO_FECHA_ACTUAL = "fechaActual";

    public static final String ATRIBUTO_EMAIL_DIR_TECNOLOGIA = "emailDirTI";
    
    public static final String ATRIBUTO_CLIENTE_B_CONNECT = "clienteBConnect";
    
    public static final String ATRIBUTO_REDIRECT_URL = "redirectUrl";
    public static final String ATRIBUTO_CHART_PROPERTIES = "chartProperties";
    public static final String ATRIBUTO_LIST_CHART_PROPERTIES = "listChartProperties";
    public static final String PARAMETER_INDEX_CHART_PROPERTIES = "i";
    public static final String PARAMETER_CHART_IMAGE_EXTENSION = "ext";
    public static final String PARAMETER_CHART_IMAGE_WIDTH = "w";
    public static final String PARAMETER_CHART_IMAGE_HEIGHT = "h";
    
    // STORED PROCEDURES
    public static final String SP_LOGIN_USUARIO = "usuario.login";
    public static final String SP_GET_USUARIO_BY_ID = "usuario.findById";
    public static final String SP_GET_USUARIO_BY_LOGIN_PASSWORD = "usuario.findByLoginPassword";
    public static final String SP_GET_USUARIO_BY_LOGIN = "usuario.findByLogin";
    public static final String SP_GET_USUARIO_BY_LOGIN_TEL = "usuario.findByLoginTel";
    public static final String SP_CHANGE_PASSWORD = "usuario.changePassword";

    public static final String SP_ZONA_DISTRIBUCION_INSERT = "zonas.insert";
    public static final String SP_ZONA_DISTRIBUCION_UPDATE = "zonas.update";
    public static final String SP_ZONA_DISTRIBUCION_DELETE = "zonas.delete";
    public static final String SP_ZONA_DISTRIBUCION_VALIDATE = "zonas.validate";

    public static final String QUERY_FIND_ZONA_DIST_BY_ID = "zonas.findById";
    public static final String QUERY_FIND_ZONA_DIST_BY_ESTADO = "zonas.findByIdEstado";
    public static final String QUERY_FIND_ZONA_DIST_BY_CP = "zonas.findByCp";
    public static final String QUERY_FIND_ZONA_DIST_BY_ESTADO_MUNICIPIO = "zonas.findByIdEstadoMunicipio";
        
    public static final String QUERY_GET_USUARIO_BY_NUMERO_NOMINA = "usuario.findByNumeroNomina";
    public static final String QUERY_GET_USUARIO_BY_ID_OLD = "usuario.findByIdOld";
    public static final String QUERY_GET_USUARIO_BY_LOGIN_OLD = "usuario.findByLoginOld";
    public static final String QUERY_GET_USUARIO_BY_LOGIN_OLD_TABLE = "usuario.findByLoginOldTable";
    public static final String QUERY_GET_USUARIO_BY_ID_DEPTO = "usuario.findByIdDepto";
    public static final String QUERY_GET_USUARIOS_BY_ID_PROYECTO_PERFIL = "usuarios.findByIdProyectoPerfil";
    public static final String QUERY_GET_USUARIOS_BY_ID_PROYECTO = "usuarios.findByIdProyecto";
    
    public static final String QUERY_FIND_ESTADOS = "direccion.estados.find";
    public static final String QUERY_FIND_MUNICIPIOS_BY_EDO = "direccion.municipios.findByEstado";
    public static final String QUERY_FIND_MUNICIPIOS = "direccion.municipios.find";
    public static final String QUERY_FIND_COLONIAS_BY_MUNICIPIO = "direccion.colonias.findByMunicipio";
    public static final String QUERY_FIND_CP_BY_COLONIA = "direccion.cp.findByColonia";
    public static final String QUERY_FIND_INFO_BY_CP = "direccion.cp.findInfo";
    public static final String QUERY_FIND_BY_ESTADO_MUNICIPIO = "direccion.findByEstadoMunicipio";
    public final String QUERY_FIND_MUNICIPIOS_BY_STREDO = "direccion.municipios.findByStrEstado";

    public static final String QUERY_FIND_EMPRESAS = "empresas.find";
    public static final String QUERY_FIND_EMPRESAS_BY_GIRO = "empresas.findByGiro";
    public static final String QUERY_FIND_EMPRESA_BY_ID = "empresa.findById";

    public final String QUERY_DIA_FERIADO_FIND = "diaFeriado.find";
    public static final String QUERY_CATALOGO_FIND_BY_ID = "catalogos.findById";
    
    public static final String QUERY_FIND_CLIENTE_IN_DESPACHO_BY_NUM_CLIENTE = "clienteBConnect.findInDespachoByNumCliente";
    
    // Recursos del pool de conexiones
    public static final String DB_POOL_VALIDATION_QUERY = "SELECT count(*) FROM systables WHERE tabid=99";

    public static final String LLAVE_VALIDA_ZONA_CONTADOR = "contador";
    public static final String LLAVE_VALIDA_ZONA_DIA = "dia";
    public static final String LLAVE_VALIDA_ZONA_MENSAJERIA = "mensajeria";

    public static final String ATRIBUTO_ZONA_DISTRIBUCION = "zonaDistribucion";
    public static final String ATRIBUTO_FORMA_ZONA_DISTRIBUCION = "ZonaDistribucionForm";
    public static final String ATRIBUTO_ZONA_DISTRIBUCION_MODIFY_SUCCESS = "zonaDistribucionModifySuccess";
    public static final String PARAMETRO_ID_ZONA_DISTRIBUCION = "idZonaDistribucion";

    public static final String ATRIBUTO_LISTA_OCUPACIONES = "listaOcupaciones";
    
    public static final String EMPTY_STRING = "";
    public static final String SPACE_STRING = " ";
    public static final String DB_BIT_TRUE = "1";
    public static final String DB_BIT_FALSE = "0";
    public static final String SI = "SI";
    public static final String NO = "NO";
    public static final String SYMBOL_PERIOD = ".";
    public static final String SYMBOL_LOW_DASH = "_";
    public static final String SYMBOL_DASH = "-";
    public static final String SYMBOL_SLASH = "/";
    public static final String SYMBOL_BACKSLASH = "\\";
    public static final String SYMBOL_PARENTHESIS_LEFT = "(";
    public static final String SYMBOL_PARENTHESIS_RIGHT = ")";
    public static final String SYMBOL_QUESTION_MARK = "?";
    public static final String SYMBOL_COMMA = ",";
    
    public static final String STRING_TRUE = "true";
    public static final String STRING_FALSE = "false";

    public static final String STRING_DB_CLAUSE_WHERE = "WHERE";
    public static final String STRING_DB_CLAUSE_AND = "AND";
    public static final String STRING_DB_CLAUSE_HAVING = "HAVING";
    public static final String STRING_DB_CLAUSE_ORDER_BY = "ORDER BY";
    
    // PERFILES DE USUARIOS
    public static final String PERFIL_IT = "IT";
    public static final String PERFIL_SA = "SA";
    public static final String PERFIL_DIR = "DIR";
    
    // NIVELES DE USUARIOS
    public static final int NIVEL_OPERADOR = 60;
    public static final int NIVEL_DISTRIBUCION = 70;
    public static final int NIVEL_MENSAJERO = 70;
    public static final int NIVEL_VERIFICADOR = 75;
    public static final int NIVEL_SUPERVISOR = 75;
    public static final int NIVEL_ANALISIS = 75;
    public static final int NIVEL_COORDINADOR = 80;
    public static final int NIVEL_EJECUTIVO = 85;
    public static final int NIVEL_COMERCIAL = 85;
    public static final int NIVEL_GERENTE = 90;
    public static final int NIVEL_DIRECTOR = 95;
    public static final int NIVEL_ADMINISTRADOR = 100;
    public static final int NIVEL_TECNOLOGIA = 100;
    public static final int NIVEL_SUPER_USUARIO = 110;
    
    public static final String CLAVE_OPERADOR = "OPE";
    public static final String CLAVE_SUPERVISOR = "SUP";
    public static final String CLAVE_EJECUTIVO = "EJE";
    public static final String CLAVE_GERENTE = "GTE";
    public static final String CLAVE_VERIFICACION = "VER";
    public static final String CLAVE_TECNOLOGIA = "IT";
    public static final String CLAVE_ANALISIS = "IT";
    public static final String CLAVE_SUPERADMON = "SA";

    public static final int ID_PERFIL_SUPERVISOR = 6;
    public static final int ID_PERFIL_VERIFICADOR = 8;
    public static final int ID_EMPRESA_BCONNECT = 1;

    public static final int MAYORIA_DE_EDAD = 18;
    
    public static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String CONTENT_TYPE_DOCUMENT_PDF = "application/pdf";
    
    public static final String PARAMETER_OUTBOUND_ID_CLIENTE = "idCliente";
    public static final String PARAMETER_OUTBOUND_USUARIO = "strUser";
    public static final String PARAMETER_OUTBOUND_TIPO_REGISTRO = "strTpoReg";
    public static final String PARAMETER_OUTBOUND_CLAVE_PROYECTO = "claveProy";
    
    public static final String OUTBOUND_TIPO_REGISTRO_BASE = "B";
    public static final String OUTBOUND_TIPO_REGISTRO_ESPECIAL = "E";
    public static final String OUTBOUND_TIPO_REGISTRO_REFERIDO = "R";

    public static final String FILE_EXTENSION_JPG = "jpg";
    public static final String FILE_EXTENSION_PNG = "png";

    public static final int ID_CATALOGO_OCUPACIONES = 12;
    public static final int ID_CATALOGO_PAISES = 238;

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String HEADER_DIRECTIVA_COMPACTA = "P3P";
    public static final String HEADER_DIRECTIVA_COMPACTA_VALOR = "CP=\"CAO PSA OUR\"";

    public static final String PARAMETER_LOGGER_LEVEL = "loggerLevel";
    public static final String LOGGER_LEVEL_TRACE = "TRACE";
    public static final String LOGGER_LEVEL_DEBUG = "DEBUG";
    public static final String LOGGER_LEVEL_INFO = "INFO";
    public static final String LOGGER_LEVEL_WARN = "WARN";
    public static final String LOGGER_LEVEL_ERROR = "ERROR";
    public static final String LOGGER_LEVEL_FATAL = "FATAL";
    public static final String LOGGER_LEVEL_OFF = "OFF";
    
    public static final String PARAMETER_REQUEST_LOG_DETAIL_LEVEL = "requestDetailLog";

    //AGREGUE ISA
    public static final String FIND_USUARIO_NOMINA_NOMBRE_HORARIO_JEFE_BY_LOGIN = "usuario.findNominaNombreHorarioJefeByLogin";
    public static final String FIND_USUARIO_NOMBRE_BY_LOGIN = "usuario.findNombreByLogin";
    public static final String FIND_USUARIO_NOMBRE_BY_ID = "usuario.findNombreById";
    public static final String FIND_USUARIOS_BY_PROYECTO_ACTIVO_Y_PERFIL = "find.usuarios.byProyectoActivoPerfil";

}
