package epn.edu.ec.servicios;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(epn.edu.ec.servicios.ActividadesInstrumentosFacadeREST.class);
        resources.add(epn.edu.ec.servicios.AdolescenteInfractorCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.AdolescenteInfractorFacadeREST.class);
        resources.add(epn.edu.ec.servicios.AdolescenteInfractorUDIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.AsistenciaAdolescenteFacadeREST.class);
        resources.add(epn.edu.ec.servicios.CAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.CumplimientoMedidaCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.DatosProvinciaCantonFacadeREST.class);
        resources.add(epn.edu.ec.servicios.DatosTipoPenalCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.DetalleInfraccionCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.EjeEducativoFacadeREST.class);
        resources.add(epn.edu.ec.servicios.EjeLaboralFacadeREST.class);
        resources.add(epn.edu.ec.servicios.EjeSaludFacadeREST.class);
        resources.add(epn.edu.ec.servicios.EjecucionMedidaCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.EstadoCumplimientoMedidaFacadeREST.class);
        resources.add(epn.edu.ec.servicios.IdentificacionGeograficaFacadeREST.class);
        resources.add(epn.edu.ec.servicios.InformacionCambioMedidaCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.InformacionInfraccionFacadeREST.class);
        resources.add(epn.edu.ec.servicios.InformacionJudicialFacadeREST.class);
        resources.add(epn.edu.ec.servicios.InformeFacadeREST.class);
        resources.add(epn.edu.ec.servicios.ItemTallerFacadeREST.class);
        resources.add(epn.edu.ec.servicios.MedidaSocioeducativaFacadeREST.class);
        resources.add(epn.edu.ec.servicios.MotivoEgresoCAIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.RegistroAsistenciaFacadeREST.class);
        resources.add(epn.edu.ec.servicios.RegistroFotograficoFacadeREST.class);
        resources.add(epn.edu.ec.servicios.RepresentanteFacadeREST.class);
        resources.add(epn.edu.ec.servicios.RolFacadeREST.class);
        resources.add(epn.edu.ec.servicios.TallerFacadeREST.class);
        resources.add(epn.edu.ec.servicios.UDIFacadeREST.class);
        resources.add(epn.edu.ec.servicios.UnidadZonalFacadeREST.class);
        resources.add(epn.edu.ec.servicios.UsuarioFacadeREST.class);
    }
    
}
