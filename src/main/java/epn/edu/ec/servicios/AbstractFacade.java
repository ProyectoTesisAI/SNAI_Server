package epn.edu.ec.servicios;

import java.util.List;
import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {

    private Class<T> entidadClase;

    public AbstractFacade(Class<T> entidadClase) {
        this.entidadClase = entidadClase;
    }

    protected abstract EntityManager getEntityManager();

    public T crear(T entidad) {
        getEntityManager().persist(entidad);
        return entidad;
    }

    public T editar(T entidad) {
        return getEntityManager().merge(entidad);
    }

    public void eliminar(T entidad) {
        getEntityManager().remove(getEntityManager().merge(entidad));
    }

    public T buscarPorId(Object id) {
        return getEntityManager().find(entidadClase, id);
    }

    public List<T> listarTodo() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entidadClase));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
