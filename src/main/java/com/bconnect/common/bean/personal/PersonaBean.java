package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import com.bconnect.common.util.CommonUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class PersonaBean implements Serializable{

    protected int id;
    protected String idString;
    protected String nombre1;
    protected String nombre2;
    protected String apellido1;
    protected String apellido2;
    protected String nombreCompleto;
    protected String nacionalidad;
    protected String estadoCivil;
    protected List<DomicilioBean> domicilios;
    protected Date fechaNacimiento;
    protected String rfc;
    protected String curp;
    protected List<TelefonoBean> telefonos;
    protected List<TelefonoBean> faxes;
    protected String escolaridad;
    protected String ocupacion;
    protected String sexo;
    protected String email;
    protected String creadoPor;
    protected int idCreadoPor;
    protected Date fechaCreacion;
    protected boolean activo;
    protected static final int TEL_CASA = 0;
    protected static final int TEL_OFICINA = 1;
    protected static final int TEL_CELULAR = 2;
    protected static final int TEL_ALTERNO = 3;
    protected static final int FAX_1 = 0;
    protected static final int FAX_2 = 1;
    public static final int DOMICILIO_CASA = 0;
    public static final int DOMICILIO_OFICINA = 1;
    public static final int DOMICILIO_ALTERNO = 2;
    public static final String SEXO_MASCULINO = "MASCULINO";
    public static final String SEXO_FEMENINO = "FEMENINO";
    protected List<IdiomaBean> idiomas;
    protected List<EmpleoBean> empleos;
    protected List<EstanciaExtranjeroBean> estanciasExtranjero;
    protected int edad;

    public PersonaBean() {
        domicilios = new ArrayList<DomicilioBean>();
        domicilios.add(DOMICILIO_CASA, new DomicilioBean());
        domicilios.add(DOMICILIO_OFICINA, new DomicilioBean());
        domicilios.add(DOMICILIO_ALTERNO, new DomicilioBean());

        faxes = new ArrayList<TelefonoBean>();
        faxes.add(FAX_1, new TelefonoBean());
        faxes.add(FAX_2, new TelefonoBean());

        telefonos = new ArrayList<TelefonoBean>();
        telefonos.add(TEL_CASA, new TelefonoBean());
        telefonos.add(TEL_OFICINA, new TelefonoBean());
        telefonos.add(TEL_CELULAR, new TelefonoBean());
        telefonos.add(TEL_ALTERNO, new TelefonoBean());

        empleos = new ArrayList<EmpleoBean>();
        idiomas = new ArrayList<IdiomaBean>();
        estanciasExtranjero = new ArrayList<EstanciaExtranjeroBean>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public List<DomicilioBean> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<DomicilioBean> domicilios) {
        this.domicilios = domicilios;
    }

    public DomicilioBean getDomicilioCasa() {
        return domicilios.get(DOMICILIO_CASA);
    }

    public void setDomicilioCasa(DomicilioBean domicilio) {
        this.getDomicilios().set(DOMICILIO_CASA, domicilio);
    }

    public DomicilioBean getDomicilioOficina() {
        return domicilios.get(DOMICILIO_OFICINA);
    }

    public void setDomicilioOficina(DomicilioBean dommicilio) {
        this.getDomicilios().set(DOMICILIO_OFICINA, dommicilio);
    }

    public DomicilioBean getDomicilioAlterna() {
        return domicilios.get(DOMICILIO_ALTERNO);
    }

    public void setDomicilioAlterna(DomicilioBean domicilio) {
        this.getDomicilios().set(DOMICILIO_ALTERNO, domicilio);
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimientoString() {
        return DateUtil.formatDate(fechaNacimiento,
                CommonConstants.FECHA_FORMATO_DDMMYYYY);
    }

    public void setFechaNacimientoString(String fecha) {
        this.fechaNacimiento = DateUtil.getDate(fecha);
    }

    public String getFechaNacimientoFormat() {
        return DateUtil.formatDate(fechaNacimiento, CommonConstants.FECHA_FORMATO_WEB_MMM_D_YYYY);
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public List<TelefonoBean> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoBean> telefonos) {
        this.telefonos = telefonos;
    }

    public List<TelefonoBean> getFaxes() {
        return faxes;
    }

    public void setFaxes(List<TelefonoBean> faxes) {
        this.faxes = faxes;
    }

    public TelefonoBean getTelefonoCasa() {
        return telefonos.get(TEL_CASA);
    }

    public void setTelefonoCasa(TelefonoBean telefono) {
        this.getTelefonos().set(TEL_CASA, telefono);
    }

    public void setTelefonoCasa(String numeroTelefono) {
        this.getTelefonoCasa().setNumTelefono(numeroTelefono);
    }

    public TelefonoBean getTelefonoOficina() {
        return telefonos.get(TEL_OFICINA);
    }

    public void setTelefonoOficina(String numeroTelefono) {
        this.getTelefonoOficina().setNumTelefono(numeroTelefono);
    }

    public void setTelefonoOficina(TelefonoBean telefono) {
        this.getTelefonos().set(TEL_OFICINA, telefono);
    }

    public TelefonoBean getTelefonoCelular() {
        return telefonos.get(TEL_CELULAR);
    }

    public void setTelefonoCelular(TelefonoBean telefono) {
        this.getTelefonos().set(TEL_CELULAR, telefono);
    }

    public void setTelefonoCelular(String numeroTelefono) {
        this.getTelefonoCelular().setNumTelefono(numeroTelefono);
    }

    public TelefonoBean getTelefonoAlterno() {
        return telefonos.get(TEL_ALTERNO);
    }

    public void setTelefonoAlterno(TelefonoBean telefono) {
        this.getTelefonos().set(TEL_ALTERNO, telefono);
    }

    public void setTelefonoAlterno(String numeroTelefono) {
        this.getTelefonoAlterno().setNumTelefono(numeroTelefono);
    }

    public TelefonoBean getFax1() {
        return faxes.get(FAX_1);
    }

    public void setFax1(TelefonoBean telefono) {
        this.getFaxes().set(FAX_1, telefono);
    }

    public void setFax1(String numeroFax) {
        this.getFax1().setNumTelefono(numeroFax);
    }

    public TelefonoBean getFax2() {
        return faxes.get(FAX_2);
    }

    public void setFax2(TelefonoBean telefono) {
        this.getFaxes().set(FAX_2, telefono);
    }

    public void setFax2(String numeroFax) {
        this.getFax2().setNumTelefono(numeroFax);
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        int edad = 0;
        if (fechaNacimiento != null) {
            edad = DateUtil.findDifferenceYears(DateUtil.getSystemDate(), fechaNacimiento);
        }
        return edad;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Construye y regresa el nombre completo de un usuario
     * @return una cadena compuesta por los siguientes valores, si existen:
     * primer nombre, segundo nombre, apellido paterno, apellido materno.
     */
    public String getNombreCompleto() {
        if (CommonUtils.hasValue(this.nombreCompleto)) {
            return this.nombreCompleto;
        } else {
            StringBuffer nombre = new StringBuffer();
            if (CommonUtils.hasValue(nombre1)) {
                nombre.append(nombre1);
            }
            if (CommonUtils.hasValue(nombre2)) {
                nombre.append(" " + nombre2);
            }
            if (CommonUtils.hasValue(apellido1)) {
                nombre.append(" " + apellido1);
            }
            if (CommonUtils.hasValue(apellido2)) {
                nombre.append(" " + apellido2);
            }

            return nombre.toString();
        }
    }

    public String getNombreCompletoInverso() {
        StringBuffer nombre = new StringBuffer();
        nombre.append(apellido1);
        nombre.append(" ");
        if (CommonUtils.hasValue(apellido2)) {
            nombre.append(apellido2);
            nombre.append(" ");
        }
        nombre.append(nombre1);
        if (CommonUtils.hasValue(nombre2)) {
            nombre.append(" ");
            nombre.append(nombre2);
        }
        return nombre.toString();
    }

    public String getNombreApellido() {
        StringBuffer nombre = new StringBuffer();
        nombre.append(nombre1);
        nombre.append(" ");
        nombre.append(apellido1);

        return nombre.toString();
    }

    public String getNombres() {
        StringBuffer nombres = new StringBuffer();

        if (CommonUtils.hasValue(nombre1)) {
            nombres.append(nombre1);
        }
        if (CommonUtils.hasValue(nombre2)) {
            nombres.append(" ");
            nombres.append(nombre2);
        }
        return nombres.toString();
    }

    public String getIniciales() {
        StringBuilder iniciales = new StringBuilder();
        String[] nombreCompleto = getNombreCompleto().split(" ");
        for (int i = 0; i < nombreCompleto.length; i++) {
            iniciales.append(nombreCompleto[i].substring(0, 1));
        }
        return iniciales.toString();
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public List<IdiomaBean> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<IdiomaBean> idiomas) {
        this.idiomas = idiomas;
    }

    public List<EmpleoBean> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(List<EmpleoBean> empleos) {
        this.empleos = empleos;
    }

    public List<EstanciaExtranjeroBean> getEstanciasExtranjero() {
        return estanciasExtranjero;
    }

    public void setEstanciasExtranjero(List<EstanciaExtranjeroBean> estanciasExtranjero) {
        this.estanciasExtranjero = estanciasExtranjero;
    }

    public int getIdCreadoPor() {
        return idCreadoPor;
    }

    public void setIdCreadoPor(int idCreadoPor) {
        this.idCreadoPor = idCreadoPor;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }
}
