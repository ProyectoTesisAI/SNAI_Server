package epn.edu.ec.servicios;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

//clase abstracta que definirá los métodos CRUD
//para la persistenica de objetos en la base de datos
public abstract class AbstractFacade<T> {

    // objeto de una clase genérica que se usará para persistir en la base de datos
    private Class<T> entidadClase;

    //constructor en el que asigno una clase de cualquier tipo al objeto entidadClase
    public AbstractFacade(Class<T> entidadClase) {
        this.entidadClase = entidadClase;   
    }

    //firma del método abstracto para crear un administrador de entidades, 
    //el cual se usa para acceder a la base de datos a través de métodos propios
    // de la clase EntityManager
    protected abstract EntityManager getEntityManager();

    
    public T crear(T entidad) {
        //almacena o inserta nuevos objetos de una entidad o clase específica
        getEntityManager().persist(entidad);
        return entidad;
    }

    public T editar(T entidad) {
        //actualiza un objeto de una clase o entidad específica 
        // si el objeto no existe, lo crea
        return getEntityManager().merge(entidad);
    }

    public void eliminar(T entidad) {
        //elimina el objeto de una clase o entidad específica
        getEntityManager().remove(getEntityManager().merge(entidad));
    }

    
    public T buscarPorId(Object id) {
        //encuentro un objeto de una clase o entidad genérica a través de su clave primaria
        return getEntityManager().find(entidadClase,id);
    }

    public List<T> listarTodo() {
        
        //Creo un objeto para realizar consultas de acuerdo a un criterio
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        //obtengo todos los objetos de una clase o entidad específica
        cq.select(cq.from(entidadClase));
        //retorno una lista de todos los objetos obtenidos de acuerdo a la consulta
        return getEntityManager().createQuery(cq).getResultList();
    }

}
