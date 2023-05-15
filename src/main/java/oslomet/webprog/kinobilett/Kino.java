package oslomet.webprog.kinobilett;

public class Kino {
    private String film;
    private int antall;
    private String fornavn;
    private String etternavn;
    private int tlfNmr;
    private String epost;

    public Kino(String film, int antall, String fornavn, String etternavn, int tlfNmr, String epost) {
        this.film = film;
        this.antall = antall;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.tlfNmr = tlfNmr;
        this.epost = epost;
    }

    public Kino(){
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public int getTlfNmr() {
        return tlfNmr;
    }

    public void setTlfNmr(int tlfNmr) {
        this.tlfNmr = tlfNmr;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
}
