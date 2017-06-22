package br.com.pranuncio.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pranuncio.entity.Cidade;
import br.com.pranuncio.service.CidadeService; 
 

@Named
@ApplicationScoped
@FacesConverter(value = "cidadeConverter")
public class CidadeConverter implements Converter {

	@Inject
	private CidadeService cidadeService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Object obj = cidadeService.pesquisarPorId(Integer.valueOf(value));
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof Cidade)) {
			return null;
		}
		String s = String.valueOf(((Cidade) value).getIdcidade());
		return s;
	}

}