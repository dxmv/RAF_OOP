
public class IzborniPredmet extends Predmet implements IzborStudenata {
    public IzborniPredmet(String naziv, int semestar, OznakaPlana oznakaPlana, int espb) {
        super(naziv, semestar, oznakaPlana, espb);
    }

    // pod 3.
    @Override
    public boolean mozeDaIzabere(Student student) {
        // treba da vrati true:
        // ako je student ponovac
        // ako je položio sve preduslove
        // ako nije položio predmet koji bira
        // ako predmet pripada istom studijskom programu
        // ako se nalazi u semestrima koji odgovaraju studentovoj godini studija (1. godina - 1. i 2. semestar, 2. godina 3. i 4. )
        System.out.println(student.jePonovac());
        System.out.println(studentPolozioSvePreduslove(student));
        System.out.println(!student.polozioPredmet(this));
        System.out.println(student.getPlan().equals(this.getOznakaPlana()));
        System.out.println(student.predmetUTrenutnojGodini(this));
        System.out.println("\n");
        return student.jePonovac() &&
                studentPolozioSvePreduslove(student) &&
                !student.polozioPredmet(this) &&
                student.getPlan().equals(this.getOznakaPlana()) &&
                student.predmetUTrenutnojGodini(this);
    }


}
