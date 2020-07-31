package com.cursos_online;

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
