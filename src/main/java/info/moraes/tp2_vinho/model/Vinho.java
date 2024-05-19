package info.moraes.tp2_vinho.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String ano;
    private String pais_origem;

    public Vinho(int id, String nome, String ano, String pais_origem) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.pais_origem = pais_origem;
    }

    public Vinho() { // mandatório do jpa

    }

    public Vinho(String nome, String ano, String pais_origem) {
        this.nome = nome;
        this.ano = ano;
        this.pais_origem = pais_origem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPais_origem() {
        return pais_origem;
    }

    public void setPais_origem(String pais_origem) {
        this.pais_origem = pais_origem;
    }
}
