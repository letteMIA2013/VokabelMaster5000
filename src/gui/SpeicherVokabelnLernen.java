package gui;

import java.util.ArrayList;

/**
 * Ka Yan
 */

/**
 * In dieser Klasse werden Daten abgespeichert.
 * Sie dient als Zwischenspeicher für das Vokabeltraining.
 */
public class SpeicherVokabelnLernen {

    int zwischenStandDeEng;
    int zwischenStandEngDe;
    int punkte;
    int richtigeAntwortenDeEng;
    int richtigeAntwortenEngDe;
    int timeDeEng;
    int timeEngDe;
    boolean istAngemeldet;
    String name;
    String passwort;
    ArrayList<String> fragenListeDeEng;
    ArrayList<String> fragenListeEngDe;
    ArrayList<String> antwortenListeDeEng;
    ArrayList<String> antwortenListeEngDe;
    ArrayList<String> alleFragenListeDeEng;
    ArrayList<String> alleFragenListeEngDe;
    ArrayList<String> alleAntwortenListeDeEng;
    ArrayList<String> alleAntwortenListeEngDe;

    public int getZwischenStandDeEng() {
        return zwischenStandDeEng;
    }

    public void setZwischenStandDeEng(int zwischenStandDeEng) {
        this.zwischenStandDeEng = zwischenStandDeEng;
    }

    public int getZwischenStandEngDe() {
        return zwischenStandEngDe;
    }

    public void setZwischenStandEngDe(int zwischenStandEngDe) {
        this.zwischenStandEngDe = zwischenStandEngDe;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public int getRichtigeAntwortenDeEng() {
        return richtigeAntwortenDeEng;
    }

    public void setRichtigeAntwortenDeEng(int richtigeAntwortenDeEng) {
        this.richtigeAntwortenDeEng = richtigeAntwortenDeEng;
    }

    public int getRichtigeAntwortenEngDe() {
        return richtigeAntwortenEngDe;
    }

    public void setRichtigeAntwortenEngDe(int richtigeAntwortenEngDe) {
        this.richtigeAntwortenEngDe = richtigeAntwortenEngDe;
    }

    public int getTimeDeEng() {
        return timeDeEng;
    }

    public void setTimeDeEng(int timeDeEng) {
        this.timeDeEng = timeDeEng;
    }

    public int getTimeEngDe() {
        return timeEngDe;
    }

    public void setTimeEngDe(int timeEngDe) {
        this.timeEngDe = timeEngDe;
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

    public ArrayList<String> getAlleFragenListeDeEng() {
        return alleFragenListeDeEng;
    }

    public void setAlleFragenListeDeEng(ArrayList<String> alleFragenListeDeEng) {
        this.alleFragenListeDeEng = alleFragenListeDeEng;
    }

    public ArrayList<String> getAlleFragenListeEngDe() {
        return alleFragenListeEngDe;
    }

    public void setAlleFragenListeEngDe(ArrayList<String> alleFragenListeEngDe) {
        this.alleFragenListeEngDe = alleFragenListeEngDe;
    }

    public ArrayList<String> getAlleAntwortenListeDeEng() {
        return alleAntwortenListeDeEng;
    }

    public void setAlleAntwortenListeDeEng(ArrayList<String> alleAntwortenListeDeEng) {
        this.alleAntwortenListeDeEng = alleAntwortenListeDeEng;
    }

    public ArrayList<String> getAlleAntwortenListeEngDe() {
        return alleAntwortenListeEngDe;
    }

    public void setAlleAntwortenListeEngDe(ArrayList<String> alleAntwortenListeEngDe) {
        this.alleAntwortenListeEngDe = alleAntwortenListeEngDe;
    }

}
