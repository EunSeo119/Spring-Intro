package hello.hellospring.domain;

import javax.persistence.*;

@Entity     //jpa가 관리하는 엔티티!
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //db에 값을 넣으면 db가 id 자동 생성해주는 것 -> IDENTITY 전략!
    private Long id;

//    @Column(name = "username")  //db column명이 username 일때는 이렇게 지정!
    private String name;

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
