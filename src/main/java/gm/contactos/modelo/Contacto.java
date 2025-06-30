package gm.contactos.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idContacto;
    String nombre;
    String celular;
    String email;
    String compania;

    public Contacto() {
    }

    public Contacto(Integer idContacto, String nombre, String celular, String email, String compania) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.celular = celular;
        this.email = email;
        this.compania = compania;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "idContacto=" + idContacto +
                ", nombre='" + nombre + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", compania='" + compania + '\'' +
                '}';
    }
}
