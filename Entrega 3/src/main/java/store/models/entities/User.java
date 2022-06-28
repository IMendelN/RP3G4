package store.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "password", length = 60, nullable = false)
	private String password;

    @Column(name = "address", length = 255, nullable = false)
	private String address;

    @Column(name = "email", length = 255, nullable = false)
	private String email;

    @Column(name = "birth_date")
	private String birthDate;

    @Column(name = "role", nullable = false)
	private Integer role;

    public User() {}

    public User(Long id, String name, String password, String address, String email, String birthDate, Integer role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
