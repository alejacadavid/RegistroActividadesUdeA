/**
 * Esta clase define el DAO para el objeto  actividades
 * @author: Gonzalo Garcia gonchalo620@gmail.com
 * @version: 21/04/2017/
 */
package edu.udea.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import edu.udea.modelo.Actividades;



//con Spring Data JPA una entidad DAO que extienda de CrudRepository 
// obtiene por defecto los siguientes metodos save, delete, deleteAll, findOne y findAll.
@Transactional
public interface ActividadesDAO extends CrudRepository<Actividades, Integer> {

	// No es necesario implementar el metodo, spring jpa lo hace siempre y
	// cuando
	// el nombre del parametro coincida con un atributo de product
	// para mas detalles ver:
	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation


	public Actividades findById(Integer id);
}
