package com.bconnect.common.db;

import java.io.InputStream;
import java.util.ArrayList;
import java .util.List;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * Esta clase se encarga exclusivamente de llevar a cabo la carga de los DataSources
 * definidos en el archivo context.xml para crear instancias de CommonDataSource
 * @author Jorge Rodriguez
 */
public class ConfigurationLoader {

    private static final String CONFIG_FILENAME = "/META-INF/context.xml";
    private String name;
    private String dbDriverName;
    private String dbUser;
    private String dbPassword;
    private String dbURI;
    private int dbPoolMinSize;
    private int dbPoolMaxSize;
    private int dbMaxActive;
    private int dbMaxIdle;
    private int dbMaxWait;
    private String nombreProyecto;
    private static List dataSources;
    private static ConfigurationLoader instance;

    /**
     * Constructor privado de la clase
     * @param context
     */
    private ConfigurationLoader(ServletContext context) {
        this.initialize(context);
    }

    /**
     * En caso de que instance sea nulo, le asigna una nueva instancia, 
     * la cual, en su proceso de incializacion (construtor) hace una llamada al 
     * metodo initialize
     * @param context
     * @return una lista de las configuraciones de bases de datos definidas
     * en el archivo context.xml
     */
    public static List getDataSources(ServletContext context) {
        if (instance == null) {
            instance = new ConfigurationLoader(context);
        }
        return dataSources;
    }

    public static List getDataSources() {
        return dataSources;
    }

    /**
     * Lee los parametros del archivo context.xml y los asigna como propiedades
     * a objetos del tipo CommonDataSource
     * @param context una referencia al contexto del Servlet, en donde buscara
     * el archivo context.xml
     */
    private void initialize(ServletContext context) {
        SAXBuilder builder = new SAXBuilder();

        try {
            InputStream is = context.getResourceAsStream(CONFIG_FILENAME);

            Document doc = builder.build(is);
            Element root = doc.getRootElement();
            List<Element> children = root.getChildren();
            CommonDataSource ds = null;

            dataSources = new ArrayList();

            for (Element element : children) {
                name = element.getAttribute("name").getValue();
                dbDriverName = element.getAttribute("driverClassName").getValue();
                dbUser = element.getAttribute("username").getValue();
                dbPassword = element.getAttribute("password").getValue();
                dbURI = element.getAttribute("url").getValue();
                dbMaxActive = Integer.parseInt(element.getAttribute("maxActive").getValue());
                dbMaxIdle = Integer.parseInt(element.getAttribute("maxIdle").getValue());
                dbMaxWait = Integer.parseInt(element.getAttribute("maxWait").getValue());
                dbPoolMinSize = 2;
                dbPoolMaxSize = 100;

                nombreProyecto = element.getAttributeValue("project");

                ds = new CommonDataSource(name, dbDriverName, dbUser, dbPassword,
                        dbURI, dbPoolMinSize, dbPoolMaxSize, dbMaxActive,
                        dbMaxIdle, dbMaxWait);

                if(nombreProyecto != null){
                    ds.setProject(nombreProyecto);
                }

                dataSources.add(ds);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
