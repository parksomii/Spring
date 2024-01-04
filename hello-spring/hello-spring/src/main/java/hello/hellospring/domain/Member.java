package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member { // JPA가 관리하는 Entity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //시스템이 그냥 정해주는 역할
    private String name;  //고객이 회원가입을 할 때 적는 이름

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
}
