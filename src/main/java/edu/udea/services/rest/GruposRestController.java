/**
 * Esta clase define la entidad para el objeto Registro de actividades
 * @author: Gonzalo Garcia gonchalo620@gmail.com
 * @version: 21/04/2017/
 */
package edu.udea.services.rest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.udea.dao.GruposDAO;
import edu.udea.dao.Registro_ActividadesDAO;
import edu.udea.dao.SemestresDAO;
import edu.udea.modelo.Grupos;


//crossorigin para que permita ser usado desde afuera, se pueden hacer más configuraciones. con crossorigin sin parámetros queda abierto para cualquier maquina
@CrossOrigin
@RestController
public class GruposRestController {
	@Autowired
	SemestresDAO semestresDAO;
	@Autowired
	GruposDAO gruposDAO;
	@Autowired
	Registro_ActividadesDAO registroActividadesDAO;



	 /**
		 * @author: Gonzalo Garcia gonchalo620@gmail.com
		 * @version: 03/05/2017/
		 */
		@RequestMapping("/grupos/findAll")
		@ResponseBody
		public List<Grupos> getAllGrupos() {
			List<Grupos> grupos = new ArrayList<Grupos>();
			grupos = (List<Grupos>) gruposDAO.findAll();
			//logger.info("todos las Actividades buscadas desde la funcion getAllActividades /actividades/findAll");
			return grupos;

		}
		
		/**
		 * @author: Gonzalo Garcia gonchalo620@gmail.com
		 * @version: 03/05/2017/
		 */
		@RequestMapping("/grupos/findBy")
		@ResponseBody
		public Grupos getgrupo(Integer id) {
			Grupos grupo = gruposDAO.findById(id);
			return grupo;
		}
	
}
