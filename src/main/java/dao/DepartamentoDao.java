package main.java.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import main.java.entities.Departamento;
import main.java.entities.Empleado;

public class DepartamentoDao {
	
	static Logger logger = LogManager.getLogger(DepartamentoDao.class);
	
	public static List<Departamento> listarDepartamentos(Session s){
		String hql = "from Departamento d";
		List lista = s.createQuery(hql, Departamento.class).list();		
		
		if(lista != null) {
			logger.info("La lista no esta vacia");
		}else {
			logger.warn("La lista esta vacia");
		}
		
		return lista;
	}
}
