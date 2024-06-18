package app.models;

public class Paket {
    private String id;
    private String gradOd;
    private String gradZa;
    private StatusPosiljke status;

    public Paket(String id, String gradOd, String gradZa, StatusPosiljke status) {
        this.id = id;
        this.gradOd = gradOd;
        this.gradZa = gradZa;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradOd() {
        return gradOd;
    }

    public void setGradOd(String gradOd) {
        this.gradOd = gradOd;
    }

    public String getGradZa() {
        return gradZa;
    }

    public void setGradZa(String gradZa) {
        this.gradZa = gradZa;
    }

    public StatusPosiljke getStatus() {
        return status;
    }

    public void setStatus(StatusPosiljke status) {
        this.status = status;
    }
}
