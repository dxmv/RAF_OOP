import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Baza {
    private List<StudentIzbor> studentIzbori;

    public Baza() {
        this.studentIzbori = new ArrayList<>();
    }

    public List<StudentIzbor> getStudentIzbori() {
        return studentIzbori;
    }

    public void setStudentIzbori(List<StudentIzbor> studentIzbori) {
        this.studentIzbori = studentIzbori;
    }

    public void dodajIzbor(Student student,IzborStudenata izbor){
        StudentIzbor si = new StudentIzbor();
        if(si.dodajUListu(student,izbor)){
            studentIzbori.add(si);
        }
    }

    // pod 7.
    public void ispis(String imeFajla){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(imeFajla));
            for(StudentIzbor si:studentIzbori){
                bw.write(si.toString());
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Greska pri otvaranju fajla");
        }
    }
}
