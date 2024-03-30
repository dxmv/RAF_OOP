import java.util.ArrayList;
import java.util.List;

public class StudentIzbor {
    private Student student;
    private List<IzborStudenata> izboriStudenta;

    public StudentIzbor() {
        this.izboriStudenta = new ArrayList<>();
    }
    public StudentIzbor(Student student) {
        this(); // napravimo listu
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<IzborStudenata> getIzboriStudenta() {
        return izboriStudenta;
    }

    public void setIzboriStudenta(List<IzborStudenata> izboriStudenta) {
        this.izboriStudenta = izboriStudenta;
    }

    public boolean dodajUListu(Student student, IzborStudenata izbor){ // vraca true ako je uspesno dodavanje
        if(student.jePonovac()){
            return this.dodajPonovac(student,izbor);
        }
        else{
            return this.dodajObican(student,izbor);
        }
    }

    private boolean dodajPonovac(Student student,IzborStudenata izborStudenata){
        // studenti ponovci biraju samo predmete i mogu imati više izbora
        if(izborStudenata instanceof IzborniPredmet){
            IzborniPredmet ip = (IzborniPredmet) izborStudenata;
            if(ip.mozeDaIzabere(student)){
                this.student = student;
                this.izboriStudenta.add(izborStudenata);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean dodajObican(Student student,IzborStudenata izborStudenata){
        // studenti koji nisu ponovci biraju tačno jednu grupu ili jedan modul, ne mogu oba
        if(!this.izboriStudenta.isEmpty()){
            return false;
        }
        if(izborStudenata instanceof IzborGrupa){
            if(izborStudenata.mozeDaIzabere(student)){
                this.student = student;
                this.izboriStudenta.add(izborStudenata);
                return true;
            }
            return false;
        }
        else if(izborStudenata instanceof IzborModul){
            if(izborStudenata.mozeDaIzabere(student)){
                this.student = student;
                this.izboriStudenta.add(izborStudenata);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(IzborStudenata i:izboriStudenta){
            sb.append(student.getBrojIndeksa() + " ");
            if(i instanceof IzborniPredmet){
                sb.append(((IzborniPredmet)i).getNaziv());
            }
            else if(i instanceof IzborModul){
                sb.append(((IzborModul)i).getNaziv());
            }
            else if(i instanceof IzborGrupa){
                sb.append(((IzborGrupa)i).getOznaka());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
