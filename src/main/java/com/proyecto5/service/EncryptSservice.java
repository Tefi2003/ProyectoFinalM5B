package com.proyecto5.service;


public interface EncryptSservice {

    //RECIBE LA CONTRASEÑA QUE SE ENCRIPTARÁ
    String encryPassword(String password);

    //VVERIFICA SI LA CONTRASEÑA ORIGINAL Y EL HASH SON LO MISMO
    boolean verifyPassword(String originalPassowrd, String hasPassword);

    //public Usuarios findUsuariosByCorreo(String correo);
}
