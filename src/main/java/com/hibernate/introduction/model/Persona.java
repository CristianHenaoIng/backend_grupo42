package com.hibernate.introduction.model;

import java.util.Calendar;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Indicar que la clase perosna representa la clase persona
@Entity
@Table (name="users")

public class Persona {
    //ATRIBUTOS
    @Id
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    // @Column(name="fecha_nacimiento")
    // Si los atributos se llaman diferente a la columna se usa @column referenciando el nombre d ela columna que esta referenciando ese atributo
    private Calendar fecha_nacimiento;
    private String foto;

    //CONSTRUCTORES
        //metodo constructor por defecto el vacio
        public Persona() {

        }

        //el que permite crear una entidad con parametros - datos
        public Persona(String nombre, String apellido, String email, Calendar fecha_nacimiento, String foto) {
            // asignacion a los atributos, los parametros se llaman igual que los atributos
            //por medio del this se diferencian los atributos de los parametros
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.fecha_nacimiento = fecha_nacimiento;
            this.foto = foto;
        }

        @Override
        public String toString() {
            String info = "---------------------------------\n";
            info += "Id: "+id;
            info += "\nNombre: "+nombre;
            info += "\nApellido: "+apellido;
            info += "\nEmail: "+email;
            info += "\nFecha nacimiento: "+fecha_nacimiento;
            info += "\nFoto: "+foto;
            info += "\n---------------------------------\n";
            return info;
        }

    //CONSULTORES - GETTERS
        //permiten consultar los atributos de una clase
        public int getId(){
            return id;
        }

        public String getNombre(){
            return nombre;
        }

        public String getApellido(){
            return apellido;
        }

        public String getEmail(){
            return email;
        }

        public Calendar getFecha_nacimiento(){
            return fecha_nacimiento;
        }

        public String getFoto (){
            return foto;
        }
        
    //MODIFICADORES
        // no se modificia id porque no se puede modificar
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFecha_nacimiento(Calendar fecha_nacimiento) {
            this.fecha_nacimiento = fecha_nacimiento;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
}
