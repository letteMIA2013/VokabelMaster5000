package gui;

import java.util.ArrayList;

/**
 * Created by Ka Yan Lam
 * on 14 Jun 2014
 * VokabelMaster5000
 */
public class SpeicherVokabelnLernen {

    int zwSpDeEng;
    int zwSpEngDe;
    int punkte;

    boolean istAngemeldet;
    String name;
    String passwort;
    ArrayList<String> fragenListeDeEng;
    ArrayList<String> fragenListeEngDe;
    ArrayList<String> antwortenListeDeEng;
    ArrayList<String> antwortenListeEngDe;

    public int getZwSpDeEng() {
        return zwSpDeEng;
    }

    public void setZwSpDeEng(int zwSpDeEng) {
        this.zwSpDeEng = zwSpDeEng;
    }

    public int getZwSpEngDe() {
        return zwSpEngDe;
    }

    public void setZwSpEngDe(int zwSpEngDe) {
        this.zwSpEngDe = zwSpEngDe;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public void setIstAngemeldet(boolean istAngemeldet) {
        this.istAngemeldet = istAngemeldet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public ArrayList<String> getFragenListeDeEng() {
        return fragenListeDeEng;
    }

    public void setFragenListeDeEng(ArrayList<String> fragenListeDeEng) {
        this.fragenListeDeEng = fragenListeDeEng;
    }

    public ArrayList<String> getFragenListeEngDe() {
        return fragenListeEngDe;
    }

    public void setFragenListeEngDe(ArrayList<String> fragenListeEngDe) {
        this.fragenListeEngDe = fragenListeEngDe;
    }

    public ArrayList<String> getAntwortenListeDeEng() {
        return antwortenListeDeEng;
    }

    public void setAntwortenListeDeEng(ArrayList<String> antwortenListeDeEng) {
        this.antwortenListeDeEng = antwortenListeDeEng;
    }

    public ArrayList<String> getAntwortenListeEngDe() {
        return antwortenListeEngDe;
    }

    public void setAntwortenListeEngDe(ArrayList<String> antwortenListeEngDe) {
        this.antwortenListeEngDe = antwortenListeEngDe;
    }
}
