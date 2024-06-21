package app.models;


public class TerminPolaganja {
    private Student student;
    private TerminUcionica terminUcionica;

    public TerminPolaganja(Student student, TerminUcionica terminUcionica) {
        this.student = student;
        this.terminUcionica = terminUcionica;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TerminUcionica getTerminUcionica() {
        return terminUcionica;
    }

    public void setTerminUcionica(TerminUcionica terminUcionica) {
        this.terminUcionica = terminUcionica;
    }
}

