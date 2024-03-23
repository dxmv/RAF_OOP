package klase;

import java.util.ArrayList;
import java.util.List;


// pod 8.
public class Tim implements Registracija{

    private Polaznik vodja;
    private List<Polaznik> polaznici;

    private List<ObukaPolaznika> obuke;

    public Tim(Polaznik vodja) {
        this.vodja = vodja;
        this.polaznici = new ArrayList<>();
        this.obuke = new ArrayList<>();
    }

    public Polaznik getVodja() {
        return vodja;
    }

    public void setVodja(Polaznik vodja) {
        this.vodja = vodja;
    }

    public List<Polaznik> getPolaznici() {
        return polaznici;
    }

    public void setPolaznici(List<Polaznik> polaznici) {
        this.polaznici = polaznici;
    }

    public List<ObukaPolaznika> getObuke() {
        return obuke;
    }

    public void setObuke(List<ObukaPolaznika> obuke) {
        this.obuke = obuke;
    }

    @Override
    public boolean registruj(Obuka obuka) {
        if(this.registrovan(obuka) || !(obuka.getOblast().equals(Oblast.PROGRAMIRANJE))){
            return false;
        }
        ObukaPolaznika.setPoslednjiBroj(ObukaPolaznika.getPoslednjiBroj() + 1);
        for(Polaznik p:polaznici){
            if(!p.registrovan(obuka)){
                ObukaPolaznika op = new ObukaPolaznika(p);
                op.setObuka(obuka);
                op.setRegistracioniBroj(ObukaPolaznika.getPoslednjiBroj());

                p.getObuke().add(op);
                obuka.getObukePolaznika().add(op);
            }
        }
        if(!vodja.registrovan(obuka)){
            ObukaPolaznika op = new ObukaPolaznika(vodja);
            op.setObuka(obuka);
            op.setRegistracioniBroj(ObukaPolaznika.getPoslednjiBroj());

            vodja.getObuke().add(op);
            obuka.getObukePolaznika().add(op);
        }
        return true;
    }

    @Override
    public boolean registrovan(Obuka obuka) {
        for(ObukaPolaznika op:this.obuke){
            if(op.getObuka().equals(obuka)){
                return true;
            }
        }
        return false;
    }
}
