package com.kennedy.java.lib.general.converter;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.java.ee.working.beans.Mapped;

public class I18n implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapped
    private Long id;
    
    @Mapped
    private Locale locale;

    @Mapped
    private String description;

    public I18n() {
		// no arguments
	}
    public I18n(String locale, String description) {
    	this.locale = new Locale(locale);
    	this.description = description;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(locale)
                .append(description)
                .toHashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        I18n other = (I18n) obj;
        return new EqualsBuilder()
                .append(locale, other.locale)
                .append(description, other.description)
                .isEquals();
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    			.append("locale", locale)
    			.append("description", description)
    			.toString();
	}
    
    public static class I18nBuilder {
    	private I18n i18n;
    	private I18nBuilder() {
    		i18n = new I18n();
    	}
    	private I18nBuilder(String locale, String description) {
    		i18n = new I18n(locale, description);
    	}
    	public static I18nBuilder createNew() {
			return new I18nBuilder();
		}
    	public static I18nBuilder createNew(String locale, String description) {
			return new I18nBuilder(locale, description);
		}
    	public I18nBuilder withLocale(String locale) {
    		i18n.setLocale(new Locale(locale));
    		return this;
    	}
    	public I18nBuilder withDescription(String description) {
    		i18n.setDescription(description);
    		return this;
    	}
    	public I18n build() {
    		return i18n;
    	}
    }
    
}
