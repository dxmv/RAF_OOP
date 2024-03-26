package klase;

// pod 5.
public class PromenaStudijskogPrograma extends AktivnostStudenta{
    private String noviStudProgramOznaka;
    private int godinaUpisa;
    private int brojIndexa;
    public PromenaStudijskogPrograma(int godina, int mesec, int dan) {
        super(godina, mesec, dan);
    }

    public String getNoviStudProgramOznaka() {
        return noviStudProgramOznaka;
    }

    public void setNoviStudProgramOznaka(String noviStudProgramOznaka) {
        this.noviStudProgramOznaka = noviStudProgramOznaka;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public int getBrojIndexa() {
        return brojIndexa;
    }

    public void setBrojIndexa(int brojIndexa) {
        this.brojIndexa = brojIndexa;
    }

    @Override
    public boolean proveriUslov(Student student) {
        // uslov da student ima osvojenih 48 espb
        return student.getOsvojenihEspb() >= 48;
    }

    @Override
    public int getGodinaStudija() {
        return godinaUpisa;
    }

    // pod 6.
    @Override
    public String toString() {
        return super.toString() + " - Promena Programa - " + this.noviStudProgramOznaka + " " + this.brojIndexa;

    }
}
