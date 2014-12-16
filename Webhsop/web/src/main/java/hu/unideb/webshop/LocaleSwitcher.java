package hu.unideb.webshop;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * Class that manages the switch between languages.
 */
@ManagedBean(name = "localeSwitcher")
@SessionScoped
public class LocaleSwitcher implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of LocaleSwitcher.
	 */
	public LocaleSwitcher() {
	}

	@PostConstruct
	public void init() {
		if (getLocale()==null){
		FacesContext.getCurrentInstance().getViewRoot()
		.setLocale(Locale.ENGLISH);
		setLocale(Locale.ENGLISH);
		}
	}
	
	public static String getMessage(String key) {
		try {
			Locale l = FacesContext.getCurrentInstance().getViewRoot()
					.getLocale();
			ResourceBundle bundle1 = ResourceBundle.getBundle(
					"hu.unideb.webshop.messages.Messages", l);
			return bundle1.getString(key);
		} catch (Exception e) {
			return "undefined key: " + key;
		}
	}

	// default language - english(en)
	private String language;
	// get the current locale from facescontext
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot()
			.getLocale();

	public Locale getLocale() {
		// System.out.println(" Locale " + locale);
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getLanguage() {
		// System.out.println(" Language " + language + " Locale " + locale);
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void changeLocal(String l) {
		
		if (l != null) {
			setLanguage(l);
			locale = new Locale(l);
			setLocale(locale);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, locale.getDisplayLanguage(),  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(Locale.ENGLISH);
		}
	}

	// when dropdown value gets changed - so the language and locale
	public void onLanguageChange(ValueChangeEvent vce) {
		if (vce != null) {
			String l = vce.getNewValue().toString();
			setLanguage(l);
			locale = new Locale(l);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} else {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(Locale.ENGLISH);
		}
	}
}
