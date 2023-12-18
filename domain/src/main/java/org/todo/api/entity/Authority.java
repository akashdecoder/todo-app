//package org.todo.api.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.io.Serializable;
//import java.util.Objects;
//import java.util.Set;
//
//@Entity(name = "authority")
//public class Authority implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private @Getter @Setter Integer id;
//
//    private @Getter @Setter String name;
//
//    @ManyToMany(mappedBy = "authorities")
//    private @Getter @Setter Set<Role> roles;
//
//    public Authority() {
//        super();
//    }
//
//    public Authority(String name) {
//        super();
//        this.name = name;
//    }
//
//    public Authority(String name, Set<Role> roles) {
//        super();
//        this.name = name;
//        this.roles = roles;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if(this == o) {
//            return true;
//        }
//        if(o == null) {
//            return false;
//        }
//        if(getClass() != o.getClass()) {
//            return false;
//        }
//        Authority other = (Authority) o;
//        if(id == null) {
//            if(other.id != null) {
//                return false;
//            }
//        } else if(!id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }
//}
