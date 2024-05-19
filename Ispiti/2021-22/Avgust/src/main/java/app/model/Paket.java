package app.model;

public class Paket {
    public static final String IME_FAJLA = "paketi2.txt";
    private String gradOd;
    private String gradZa;
    private String id;


    public Paket(String gradOd, String gradZa, String id) {
        this.gradOd = gradOd;
        this.gradZa = gradZa;
        this.id = id;
    }

    public void promeniGrad(String grad){
        if(gradOd.equals("X")){
            if(id.charAt(0) == grad.charAt(0)){
                setGradOd(grad);
            }
            // Ako nedodeljeni grad ne počinje odgovarajućim slovom iz ID-a, prikazati poruku: "Izabrani grad ne odgovara vrednosti Id-a."
            else{
                throw new RuntimeException("Izabrani grad ne odgovara vrednosti Id-a.");
            }
        }
        else if(gradZa.equals("X")){
            if(id.charAt(1) == grad.charAt(0)){
                setGradZa(grad);
            }
            // Ako nedodeljeni grad ne počinje odgovarajućim slovom iz ID-a, prikazati poruku: "Izabrani grad ne odgovara vrednosti Id-a."
            else{
                throw new RuntimeException("Izabrani grad ne odgovara vrednosti Id-a.");
            }
        }

        // Ako su paketu oba grada već dodeljena, prikazati poruku: "Nije moguce dodeliti grad paketu kome su oba grada poznata."
        else{
            throw new RuntimeException("Nije moguce dodeliti grad paketu kome su oba grada poznata.");
        }
    }

    // Pancevo-Sombor,PS14329
    @Override
    public String toString() {
        return this.gradOd + "-" + this.gradZa + ", " + this.id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
