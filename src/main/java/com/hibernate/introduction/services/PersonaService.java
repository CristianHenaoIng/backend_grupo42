package com.hibernate.introduction.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.introduction.model.Persona;

public class PersonaService {
    // ATRIBUTOS
    private SessionFactory factory;

    public PersonaService() {
        // Crear objeto que permita fabricar sesiones
        factory = new Configuration().configure("cfg.xml").addAnnotatedClass(Persona.class).buildSessionFactory();
    }

    // METODO PARA ABRIR Y CERRAR SESION
    private Session crearSession (){
        Session session = factory.openSession();
        session.beginTransaction();
        return session;
    }

    public List<Persona> obtenerPersonas(){
        List<Persona> personas = new ArrayList<>(); 
        Session session = crearSession();
        try {
            personas = session.createQuery("from Persona", Persona.class).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personas;
    }

    //OBTENER PERSONAS POR ID
    public Persona obtenerPersonaXId (int id){
        Session session = crearSession();
        Persona persona = new Persona();

        try {
            persona = session.find(Persona.class, id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persona;
    }

    //http://localhost:8080/personas/commons?nombre=juanito&apellido=alimaña&edad=20
    public List<Persona> obtenerPersonasXnombreApellido (String nombre, String apellido){
        List<Persona> personas = new ArrayList<>();
        Session session = crearSession();
        try {
            personas = session.createQuery("from Persona where nombre = :n and apellido = :a ", Persona.class).setParameter("n", nombre).setParameter("a", apellido).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personas;
    }


    //public String crearPersona (@RequestBody String nombre, String apellido, String email, Calendar fecha_nacimiento, String foto){
    public String crearPersona (Persona persona){
        String message = "";
        Session session = crearSession();
        try {
            //Persona persona = new Persona(nombre, apellido, email, fecha_nacimiento, foto);
            session.persist(persona);
            session.getTransaction().commit();
            message= "Persona creada con éxito";
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    // ACTUALIZAR PERSONA
    // Necesitamos capturar  los datos de la persona
    //public String actualizarPersona(@RequestParam int id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam Calendar fecha_naci, @RequestParam String foto){
    public String actualizarPersona(Persona persona){
        Session session = crearSession();
        String message = "";
        try {
            session.merge(persona);
            session.getTransaction().commit();
            session.close();
            message = "Persona actualizada con éxito";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String eliminarPersona (int id){
        String message = "";
        Session session = crearSession();
        try {
            session.remove(obtenerPersonaXId(id));
            session.getTransaction().commit();
            session.close();
            message = "Persona eliminada con éxito";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
    
    public List<String> objtoString (List<Persona> personas){
        List<String> personasStr = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++){
            personasStr.add(personas.get(i).toString());
        }
        return personasStr;
    }

    public Calendar stringtoCalendar(String fecha){
        String[] dateString = fecha.split("/");
        int year = Integer.parseInt(dateString[2]);
        int month = Integer.parseInt(dateString[1]) - 1;
        int date = Integer.parseInt(dateString[0]);
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.set(year, month, date);
        return fechaCalendar;
    }
}
