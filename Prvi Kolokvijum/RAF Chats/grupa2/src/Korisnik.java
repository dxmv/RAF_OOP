public class Korisnik {
    private String prikazanoIme;
    private String korisnickoIme;
    private String lozinka;

    public Korisnik(String prikazanoIme, String korisnickoIme, String lozinka) {
        this.prikazanoIme = prikazanoIme;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getPrikazanoIme() {
        return prikazanoIme;
    }

    public void setPrikazanoIme(String prikazanoIme) {
        this.prikazanoIme = prikazanoIme;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Korisnik k){
            return this.korisnickoIme.equals(k.getKorisnickoIme()) && this.prikazanoIme.equals(k.getPrikazanoIme());
        }
        return false;
    }

    // pod 4.
    public void napisiPoruku(Chat chat, String poruka){
        // proverava da li korisnik može dodati poruku (drugi parametar) u prosleđeni chat (prvi parametar).
        // Ako je uslov ispunjen, poruka se dodaje na taj chat
        if(chat.mozeNapisatiPoruku(this)){
            chat.dodajPoruku(this,poruka);
        }
    }
}
