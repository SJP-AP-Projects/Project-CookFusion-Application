package com.example.cooktest.modele;

public class Adherent {
    private String idAdherent, nomAdherent, prenomAdherent, loginAdherent, mdpAdherent, mailAdherent, roleAdherent;

    public Adherent(String idAdherent, String nomAdherent, String prenomAdherent, String loginAdherent, String mdpAdherent, String mailAdherent, String roleAdherent) {
        this.idAdherent = idAdherent;
        this.nomAdherent = nomAdherent;
        this.prenomAdherent = prenomAdherent;
        this.loginAdherent = loginAdherent;
        this.mdpAdherent = mdpAdherent;
        this.mailAdherent = mailAdherent;
        this.roleAdherent = roleAdherent;
    }

    public String getLoginAdherent() {
        return loginAdherent;
    }

    public String getMdpAdherent() {
        return mdpAdherent;
    }
}
