package com.hibernate.introduction.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.introduction.model.Persona;
import com.hibernate.introduction.services.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    //ATRIBUTOS
    PersonaService service;

    // CONSTRUCTOR
    public PersonaController(){     
        service = new PersonaService();
    }

    /* @GetMapping
    public String holaMundo(){
        return "Hola mundo utilizando Spring Boot";
    } */

    

    // ACCIONES
    //LISTAR PERSONAS
    @GetMapping
    public List<Persona> obtenerPersonas(){
        return service.obtenerPersonas();
    }

    @GetMapping("/{id}")
    //OBTENER PERSONAS POR ID
    public Persona obtenerPersonaXId (@PathVariable (name = "id") int id){
        return service.obtenerPersonaXId(id);
    }

    @GetMapping("/commons")
    public List<Persona> obtenerPersonasXnombreApellido(@RequestParam String nombre, @RequestParam String apellido) {
        return service.obtenerPersonasXnombreApellido(nombre, apellido);
    }

    @PostMapping
    //public String crearPersona (@RequestBody String nombre, String apellido, String email, Calendar fecha_nacimiento, String foto){
    public String crearPersona (@RequestBody Persona persona){
        return service.crearPersona(persona);
    }

    @PutMapping
    @CrossOrigin("*")
    // ACTUALIZAR PERSONA
    // Necesitamos capturar  los datos de la persona
    //public String actualizarPersona(@RequestParam int id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam Calendar fecha_naci, @RequestParam String foto){
    public String actualizarPersona(@RequestBody Persona persona){
        return service.actualizarPersona(persona);
    }

     // ELIMINAR PERSONA
    @DeleteMapping("/{id}")
    @CrossOrigin("*")
    public String eliminarPersona (@PathVariable(name = "id") int id) {
        return service.eliminarPersona(id); 
    }
}

