package com.coreman.zonaswifi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZonaWifiItem implements Parcelable {

    @SerializedName("c_digo_dane_del_departamento")
    @Expose
    private String cDigoDaneDelDepartamento;
    @SerializedName("c_digo_dane_del_municipio")
    @Expose
    private String cDigoDaneDelMunicipio;
    @SerializedName("cantidad")
    @Expose
    private String cantidad;
    @SerializedName("categoria_de_departamento")
    @Expose
    private String categoriaDeDepartamento;
    @SerializedName("categoria_de_municipio")
    @Expose
    private String categoriaDeMunicipio;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("estado_de_la_zona")
    @Expose
    private String estadoDeLaZona;
    @SerializedName("fecha_de_aprobacion")
    @Expose
    private String fechaDeAprobacion;
    @SerializedName("id_zona_wifi")
    @Expose
    private String idZonaWifi;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("localidad_troncal")
    @Expose
    private String localidadTroncal;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("municipio")
    @Expose
    private String municipio;
    @SerializedName("no")
    @Expose
    private String no;
    @SerializedName("nombre_zona_wifi")
    @Expose
    private String nombreZonaWifi;
    @SerializedName("proyecto")
    @Expose
    private String proyecto;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("zona_inagurada")
    @Expose
    private String zonaInagurada;

    public String getCDigoDaneDelDepartamento() {
        return cDigoDaneDelDepartamento;
    }

    public void setCDigoDaneDelDepartamento(String cDigoDaneDelDepartamento) {
        this.cDigoDaneDelDepartamento = cDigoDaneDelDepartamento;
    }

    public String getCDigoDaneDelMunicipio() {
        return cDigoDaneDelMunicipio;
    }

    public void setCDigoDaneDelMunicipio(String cDigoDaneDelMunicipio) {
        this.cDigoDaneDelMunicipio = cDigoDaneDelMunicipio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoriaDeDepartamento() {
        return categoriaDeDepartamento;
    }

    public void setCategoriaDeDepartamento(String categoriaDeDepartamento) {
        this.categoriaDeDepartamento = categoriaDeDepartamento;
    }

    public String getCategoriaDeMunicipio() {
        return categoriaDeMunicipio;
    }

    public void setCategoriaDeMunicipio(String categoriaDeMunicipio) {
        this.categoriaDeMunicipio = categoriaDeMunicipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoDeLaZona() {
        return estadoDeLaZona;
    }

    public void setEstadoDeLaZona(String estadoDeLaZona) {
        this.estadoDeLaZona = estadoDeLaZona;
    }

    public String getFechaDeAprobacion() {
        return fechaDeAprobacion;
    }

    public void setFechaDeAprobacion(String fechaDeAprobacion) {
        this.fechaDeAprobacion = fechaDeAprobacion;
    }

    public String getIdZonaWifi() {
        return idZonaWifi;
    }

    public void setIdZonaWifi(String idZonaWifi) {
        this.idZonaWifi = idZonaWifi;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLocalidadTroncal() {
        return localidadTroncal;
    }

    public void setLocalidadTroncal(String localidadTroncal) {
        this.localidadTroncal = localidadTroncal;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNombreZonaWifi() {
        return nombreZonaWifi;
    }

    public void setNombreZonaWifi(String nombreZonaWifi) {
        this.nombreZonaWifi = nombreZonaWifi;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZonaInagurada() {
        return zonaInagurada;
    }

    public void setZonaInagurada(String zonaInagurada) {
        this.zonaInagurada = zonaInagurada;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cDigoDaneDelDepartamento);
        dest.writeString(this.cDigoDaneDelMunicipio);
        dest.writeString(this.cantidad);
        dest.writeString(this.categoriaDeDepartamento);
        dest.writeString(this.categoriaDeMunicipio);
        dest.writeString(this.departamento);
        dest.writeString(this.direccion);
        dest.writeString(this.estadoDeLaZona);
        dest.writeString(this.fechaDeAprobacion);
        dest.writeString(this.idZonaWifi);
        dest.writeString(this.latitud);
        dest.writeString(this.localidadTroncal);
        dest.writeString(this.longitud);
        dest.writeString(this.municipio);
        dest.writeString(this.no);
        dest.writeString(this.nombreZonaWifi);
        dest.writeString(this.proyecto);
        dest.writeString(this.region);
        dest.writeString(this.zonaInagurada);
    }

    public ZonaWifiItem() {
    }

    protected ZonaWifiItem(Parcel in) {
        this.cDigoDaneDelDepartamento = in.readString();
        this.cDigoDaneDelMunicipio = in.readString();
        this.cantidad = in.readString();
        this.categoriaDeDepartamento = in.readString();
        this.categoriaDeMunicipio = in.readString();
        this.departamento = in.readString();
        this.direccion = in.readString();
        this.estadoDeLaZona = in.readString();
        this.fechaDeAprobacion = in.readString();
        this.idZonaWifi = in.readString();
        this.latitud = in.readString();
        this.localidadTroncal = in.readString();
        this.longitud = in.readString();
        this.municipio = in.readString();
        this.no = in.readString();
        this.nombreZonaWifi = in.readString();
        this.proyecto = in.readString();
        this.region = in.readString();
        this.zonaInagurada = in.readString();
    }

    public static final Parcelable.Creator<ZonaWifiItem> CREATOR = new Parcelable.Creator<ZonaWifiItem>() {
        @Override
        public ZonaWifiItem createFromParcel(Parcel source) {
            return new ZonaWifiItem(source);
        }

        @Override
        public ZonaWifiItem[] newArray(int size) {
            return new ZonaWifiItem[size];
        }
    };
}