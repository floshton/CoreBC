package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.bean.personal.PersonaBean;
import com.bconnect.common.dispatch.core.interfaces.RecordInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordObjectPoolInterface;

/**
 *
 * @author Jorge Rodriguez
 */
public class DefaultRecord extends PersonaBean implements RecordInterface {

    protected DefaultRecordObjectPool pool;
    protected boolean llamar;
    protected boolean agendado;
    protected boolean reservado;

    public DefaultRecord() {
        super();
    }

    public boolean equals(RecordInterface otherRecord) {
        boolean equals = false;
        return equals;
    }

    public void setPool(RecordObjectPoolInterface pool) {
        if (pool instanceof DefaultRecordObjectPool) {
            this.pool = (DefaultRecordObjectPool) pool;
        }
    }

    public RecordObjectPoolInterface getPool() {
        return this.pool;
    }

    public void returnToPool() throws Exception {
        if (this != null && this.pool != null) {
            this.pool.returnObject(this);
        }
    }

    public boolean isEmptyInstance() {
        return super.id == 0;
    }

    @Override
    public String toString() {
        return "Record_" + this.id;
    }

    public boolean isLlamar() {
        return llamar;
    }

    public void setLlamar(boolean llamar) {
        this.llamar = llamar;
    }

    public boolean isAgendado() {
        return agendado;
    }

    public void setAgendado(boolean agendado) {
        this.agendado = agendado;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}
