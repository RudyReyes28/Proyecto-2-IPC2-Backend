
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import com.rudyreyes.proyecto2.ipc2.backend.util.CifrarContraseña;
import java.math.BigDecimal;

public class Usuario {
    
    int idUsuario;
    String tipoUsuario;
    String nombre;
    String usuario;
    String contraseña;
    String direccion;
    int CUI;
    int telefono;
    String correo;
    String fechaNacimiento;
    BigDecimal saldo;

    public Usuario(String tipoUsuario, String nombre, String usuario, String contraseña, String direccion, int CUI, int Telefono, String correo, String fechaNacimiento, BigDecimal saldo) {
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.CUI = CUI;
        this.telefono = Telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = CifrarContraseña.hash(contraseña);
    }

    public Usuario(int idUsuario, String tipoUsuario, String nombre, String usuario, BigDecimal saldo) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public Usuario(String usuario, BigDecimal saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public Usuario(String nombre, String usuario, String correo, String fechaNacimiento, BigDecimal saldo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }
    
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return CifrarContraseña.hash(contraseña);
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCUI() {
        return CUI;
    }

    public void setCUI(int CUI) {
        this.CUI = CUI;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int Telefono) {
        this.telefono = Telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
}
