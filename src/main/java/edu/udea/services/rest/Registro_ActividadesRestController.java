/**
 * Esta clase define Los servicios REST para el objeto Registro de actividades
 * @author: Gonzalo Garcia gonchalo620@gmail.com
 * @version: 21/04/2017/
 */
package edu.udea.services.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.udea.dao.GruposDAO;
import edu.udea.dao.Registro_ActividadesDAO;
import edu.udea.dao.SemestresDAO;
import edu.udea.modelo.Asignaciones;
import edu.udea.modelo.Grupos;
import edu.udea.modelo.Registro_Actividades;
import edu.udea.modelo.Semestres;



//crossorigin para que permita ser usado desde afuera, se pueden hacer más configuraciones. con crossorigin sin parámetros queda abierto para cualquier maquina
@CrossOrigin
@Controller
public class Registro_ActividadesRestController {

	final static Logger logger = Logger.getLogger(Registro_ActividadesRestController.class);

	@Autowired
	SemestresDAO semestresDAO;
	@Autowired
	GruposDAO gruposDAO;
	@Autowired
	Registro_ActividadesDAO registroActividadesDAO;
	

	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @author: Gonzalo Garcia gonchalo620@gmail.com
	 * @version: 03/05/2017/
	 */
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "servicios para registro actividades: /findAll, /findBy?id=, /save, /update ";
	}

	/**
	 * @author: Gonzalo Garcia gonchalo620@gmail.com
	 * @version: 21/04/2017/
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public ResponseEntity<List<Registro_Actividades>> getAllRegistroActividades() {
		List<Registro_Actividades> registroActividades = new ArrayList<Registro_Actividades>();
		registroActividades = (List<Registro_Actividades>) registroActividadesDAO.findAll();
		if(registroActividades.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info("todos los Registro Actividades buscados desde la funcion getAllRegistroActividades /findAll");
		return new ResponseEntity<List<Registro_Actividades>>(registroActividades, HttpStatus.OK);

	}

	/**
	 * @author sigialzate
	 * @param id
	 * @return
	 */
	@RequestMapping("/findBy")
	@ResponseBody
	public ResponseEntity<?> getRegistroId(Integer id) {
		Registro_Actividades registroActividades = registroActividadesDAO.findById(id);
		if(registroActividades==null){
			return new ResponseEntity(new Exception("Registro con id " + id 
					+ " no existe"), HttpStatus.NOT_FOUND);
		}
		logger.info("Registro de Actividad buscado desde la funcion getRegistroId /findBy");
		return new ResponseEntity<Registro_Actividades>(registroActividades, HttpStatus.OK); 
	}

	
	/**
	 * @author: Gonzalo Garcia gonchalo620@gmail.com
	 * @version: 03/05/2017/
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRegistro(Integer id) {

		Registro_Actividades registroActividades = registroActividadesDAO.findById(id);
		try {

			if (registroActividades == null) {
				// throw new Exception("el registro de actividad que quiere
				// eliminar no existe en la base de datos");
				return new ResponseEntity(new Exception("Unable to delete. User with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			registroActividadesDAO.delete(registroActividades);
			return new ResponseEntity<Registro_Actividades>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("ocurrió una excepción, no se pudo actualizar el registro de actividad, el ERROR ES: "
					+ ex.getMessage());
			return new ResponseEntity(registroActividades, HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * @author sigialzate
	 * @param entityReg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	@ResponseBody
	public ResponseEntity<?> setRegistroActividades(@RequestBody Registro_Actividades entityReg) {

		try {
			registroActividadesDAO.save(entityReg);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			logger.error(
					"ocurrió una excepción ejecutando registroActividadesDAO.save(entityReg) en /save, el registro enviado no se pudo grabar en la base de datos "
							+ ex.getMessage());
			return new ResponseEntity(new Exception("ocurrió una excepción ejecutando registroActividadesDAO.save(entityReg) en /save, el registro enviado no se pudo grabar en la base de datos"),HttpStatus.CONFLICT);
			
		}
		logger.info("registro de actividad guardado");
		return new ResponseEntity(entityReg, HttpStatus.CREATED);
	}
	
	
	//FALTA PROBAR SU CORRECTO FUNCIONAMIENTO
	/**
	 * @author: Gonzalo Garcia gonchalo620@gmail.com
	 * @version: 05/05/2017/
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser( @RequestBody Registro_Actividades registroActividades) {
		

		Registro_Actividades registro = registroActividadesDAO.findById(registroActividades.getPk_reg_id());

		if (registro == null) {
			return new ResponseEntity(new Exception("No hay ningún registro de Actividad con id: "+registroActividades.getPk_reg_id()), HttpStatus.NOT_FOUND);
		}

		registro.setReg_descripcion(registroActividades.getReg_descripcion());
		registro.setReg_fecha(registroActividades.getReg_fecha());
		registro.setReg_horasUtilizadas(registroActividades.getReg_horasUtilizadas());
		registro.setActividades(registroActividades.getActividades());
		registro.setAsignaciones(registroActividades.getAsignaciones());

		registroActividadesDAO.save(registro);
		//logger.info("se actualizó el registro de Actividad con id: ", registroActividades.getPk_reg_id());
		return new ResponseEntity<Registro_Actividades>(registro, HttpStatus.OK);
	}
	
	
	

	/**
	 * @author sigialzate
	 * @param entityReg
	 * @return
	 */
	/*@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public String update(@RequestBody Registro_Actividades entityReg) {
		try {
			Registro_Actividades registroActividades = registroActividadesDAO.findById(entityReg.getPk_reg_id());
			if (registroActividades == null) {
				return ("No existe el registro de la actividad");
			} else {
				if (entityReg.getReg_horasUtilizadas() != null) {
					registroActividades.setReg_horasUtilizadas(entityReg.getReg_horasUtilizadas());
				} else {
					System.out.println("No esta registrando horas al momento de actualizar");
				}
				if (entityReg.getReg_descripcion() != null) {
					registroActividades.setReg_descripcion(entityReg.getReg_descripcion());
				} else {
					System.out.println("No esta registrando descripcion al momento de actualizar");
				}

				if (entityReg.getReg_fecha() != null) {
					registroActividades.setReg_fecha(entityReg.getReg_fecha());
				} else {
					System.out.println("No esta registrando fecha al momento de actualizar");
				}

				registroActividadesDAO.save(registroActividades);
				return "Done!";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "error";
		}
	}*/
	
	
	
	
	
}
