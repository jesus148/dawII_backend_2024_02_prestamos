package com.prestamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.entity.MontoPrestamo;
import com.prestamo.entity.Pais;
import com.prestamo.entity.Ubigeo;
import com.prestamo.entity.Usuario;
import com.prestamo.service.DataCatalogoService;
import com.prestamo.service.MontoPrestamoService;
import com.prestamo.service.PaisService;
import com.prestamo.service.UbigeoService;
import com.prestamo.service.UsuarioService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/util")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UtilController {

	@Autowired
	private PaisService paisService;

	@Autowired
	private DataCatalogoService dataCatalogoService;

	@Autowired
	private UbigeoService ubigeoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MontoPrestamoService montoPrestamoService;
	

	@GetMapping("/listaPais")
	@ResponseBody
	public List<Pais> listaPais() {
		return paisService.listaTodos();
	}
	
	@GetMapping("/listaTipoEntidadBancaria")
	@ResponseBody
	public List<DataCatalogo> listaTipoEntidadBancaria() {
		return dataCatalogoService.listaDataCatalogo(AppSettings.CATALOGO_01_TIPO_DE_ENDIDAD_BANCARIA);
	}
	
	@GetMapping("/listaTipoMoneda")
	@ResponseBody
	public List<DataCatalogo> listaTipoMoneda() {
		return dataCatalogoService.listaDataCatalogo(AppSettings.CATALOGO_02_TIPO_DE_MONEDA);
	}
	
	
	
////	monto prestamo 
//	@GetMapping("/listaCapitalMonto")
//	@ResponseBody
//	public List<MontoPrestamo> montoCapital() {
//		return montoPrestamoService.listaTodos(AppSettings.ACTIVO);
//	}
//	
//	
//	@GetMapping("/listaMontoPrestamo/{paramMonto}")
//	@ResponseBody
//	public List<String> verMonto(@PathVariable("paramMonto") int id) {
//		return montoPrestamoService.listaMonto(id);
//	}
	
	
	
	
	@GetMapping("/listaCapitalesPorDias/{paramDias}")
    @ResponseBody
    public List<MontoPrestamo> listaCapitalesPorDias(@PathVariable("paramDias") int dias) {
        return montoPrestamoService.findCapitalesByDias(dias);
    }
	
	@GetMapping("/listaMontosPagarPorCapital/{paramCapital}")
    @ResponseBody
    public List<MontoPrestamo> listaMontosPagarPorCapital(@PathVariable("paramCapital") int capital) {
        return montoPrestamoService.findMontosByCapital(capital);
    }
	

	
	
	
	
	
	
	
	
	
//	DIAS COMBO 
	@GetMapping("/listaDiasPrestamo")
	@ResponseBody
	public List<DataCatalogo> listaDiasPrestamo() {
		return dataCatalogoService.listaDataCatalogo(AppSettings.CATALOGO_03_DIAS_DE_PRESTAMO);
	}
	
	@GetMapping("/listaEstadoSolicitud")
	@ResponseBody
	public List<DataCatalogo> listaGradoAutor() {
		return dataCatalogoService.listaDataCatalogo(AppSettings.CATALOGO_04_ESTADO_SOLICTUD_DE_PRESTAMO);
	}	
	
	@GetMapping("/listaDepartamentos")
	@ResponseBody
	public List<String> verDepartamentos() {
		return ubigeoService.listaDepartamentos();
	}

	@GetMapping("/listaProvincias/{paramDepar}")
	@ResponseBody
	public List<String> verProvincias(@PathVariable("paramDepar") String departamento) {
		return ubigeoService.listaProvincias(departamento);
	}

	@GetMapping("/listaDistritos/{paramDepar}/{paramProv}")
	@ResponseBody
	public List<Ubigeo> verDistritos(@PathVariable("paramDepar")String departamento, @PathVariable("paramProv")String provincia) {
		return ubigeoService.listaDistritos(departamento, provincia);
	}
	
	@GetMapping("/listaJefePrestamistaTotales")
	@ResponseBody
	public List<Usuario> listaJefePrestamistaTotales() {
		return usuarioService.listaJefePrestamistaTotales();
	}

	@GetMapping("/listaPrestamistaDeUnJefe/{param}")
	@ResponseBody
	public List<Usuario> listaPrestamistaDeUnJefe(@PathVariable("param") int idJefePrestamista) {
		return usuarioService.listaPrestamistaDeUnJefe(idJefePrestamista);
	}

	@GetMapping("/listaPrestamistariosDeUnPrestamista/{param}")
	@ResponseBody
	public List<Usuario> listaPrestamistariosDeUnPrestamista(@PathVariable("param") int idPrestamistarios) {
		return usuarioService.listaPrestamistariosDeUnPrestamista(idPrestamistarios);
	}

	
}
