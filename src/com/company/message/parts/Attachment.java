package com.company.message.parts;

import java.util.Base64;

public class Attachment {
    private String name;
    private String extension;

    public Attachment(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public static String codificarBase64(String cadenaCodificar) {
        String cadenaCodificada = Base64.getEncoder().encodeToString(cadenaCodificar.getBytes());
        return cadenaCodificada;
    }

    public static String decodificarBase64(String cadenaDecodificar) {
        byte[] bytesDecodificados = Base64.getDecoder().decode(cadenaDecodificar);
        String cadenaDecodificada = new String(bytesDecodificados);
        return cadenaDecodificada;
    }
}
