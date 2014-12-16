package hu.unideb.webshop.recipe;

import hu.unideb.webshop.service.ManageRecipeFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "recipeBean")
public class RecipeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyRecipeModel recipeModel;	

	@ManagedProperty(value = "#{manageRecipeFacadeService}")
	private ManageRecipeFacadeService manageRecipeFacadeService;

	@PostConstruct
	public void init() {
		recipeModel = new LazyRecipeModel(manageRecipeFacadeService);
	}

	public LazyRecipeModel getRecipeModel() {
		return recipeModel;
	}

	public ManageRecipeFacadeService getmanageRecipeFacadeService() {
		return manageRecipeFacadeService;
	}

	public void setmanageRecipeFacadeService(
			ManageRecipeFacadeService manageRecipeFacadeService) {
		this.manageRecipeFacadeService = manageRecipeFacadeService;
	}

	public void setRecipeModel(LazyRecipeModel recipeModel) {
		this.recipeModel = recipeModel;
	}

}
