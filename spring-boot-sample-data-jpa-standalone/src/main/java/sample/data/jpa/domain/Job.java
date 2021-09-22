package sample.data.jpa.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Job implements Serializable {


    private Long id;

    private String name;

    private int salaires;

    public Job(){

    }
    public Job(String name){
        this.name = name;
    }
    public Job(String name, int salaires){
        this.name = name;
        this.salaires = salaires;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalaires() {
        return salaires;
    }

    public void setSalaires(int salaires) {
        this.salaires = salaires;
    }


    @Override
    public String toString(){
        return "Job [id=" + id + ", name=" + name + ", salaires="
                + salaires+ "]";
    }

}
