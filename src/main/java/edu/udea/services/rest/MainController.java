/**
 * Esta clase define la entidad para el objeto Registro de actividades
 * @author: Gonzalo Garcia gonchalo620@gmail.com
 * @version: 21/04/2017/
 */
package edu.udea.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.udea.dao.GruposDAO;
import edu.udea.dao.Registro_ActividadesDAO;
import edu.udea.dao.SemestresDAO;


//crossorigin para que permita ser usado desde afuera, se pueden hacer más configuraciones. con crossorigin sin parámetros queda abierto para cualquier maquina
@CrossOrigin
@RestController
public class MainController {
	@Autowired
	SemestresDAO semestresDAO;
	@Autowired
	GruposDAO gruposDAO;
	@Autowired
	Registro_ActividadesDAO registroActividadesDAO;


  @RequestMapping("/index")
  @ResponseBody
  public String index() {
    return "Microservicios de Registro Actividades ";
  }
  
}
