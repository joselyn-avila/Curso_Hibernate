package com.cursos_online;
import java.util.*;

import javax.persistence.Query;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;

public class Main {
	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

	public static void main(String[] args) {
		
			Curso cur1= new Curso("Fundamentos de java");
			Curso cur2= new Curso("Hibernate principiantes");
			
			ingresarCurso(cur1);
			ingresarCurso(cur2);
			
			Estudiante es1 = new Estudiante(0, "cesar", "alcivar");
			Estudiante es2 = new Estudiante(0, "lorena", "vera");	
		
			ingresarEstudiante(es1);
			ingresarEstudiante(es2);
			
			List<Curso> cursos = getCurso();
			for (Curso temp:cursos) {
				System.out.println(temp);
			}
			
			List<Estudiante> estudiante = getEstudiantes();
			for (Estudiante temp:estudiante) {
				System.out.println(temp);
			}
			List<Estudiante> estudiantes=getEstudiantesPorNombre("lorena");
			for(Estudiante e: estudiantes) {
				System.out.println(e);
	}
	}
			
	//ESTUDIANTES-POR-NOMBRE
		static List<Estudiante>getEstudiantesPorNombre(String nombre){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Estudiante where nombre=: nombre");
		query.setParameter("nombre", nombre);
		
			List<Estudiante> estudiantes =(List<Estudiante>)query.getResultList();
			return estudiantes;
			}

			 
	static List<Curso>getCurso(){
		Session session = sessionFactory.openSession();
		List<Curso> cursos = session.createQuery("from Curso",Curso.class).list();
		return cursos;
	}	
	
	static List<Estudiante>getEstudiantes(){
		Session session = sessionFactory.openSession();
		List<Estudiante> estudiante = session.createQuery("from Estudiante",Estudiante.class).list();
		return estudiante;
	}	
	
	
	static void ingresarEstudiante(Estudiante estudiante) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(estudiante);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(estudiante.getId());
		
	}
static void ingresarCurso(Curso curso) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
		
		session.getTransaction().commit();
		session.close();
		System.out.println(curso.getId());
	}
	
	
	
}
