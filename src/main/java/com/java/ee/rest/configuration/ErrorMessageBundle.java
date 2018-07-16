package com.java.ee.rest.configuration;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorMessageBundle {

	public static class Builder {
		private String bundlePath;
		private Optional<ClassLoader> classLoader = Optional.empty();

		public Builder(String bundlePath) {
			this.bundlePath = bundlePath;
		}

		public Builder inClassPath(ClassLoader classLoader) {
			this.classLoader = Optional.of(classLoader);
			return this;
		}

		public ErrorMessageBundle build() {
			return new ErrorMessageBundle(this.bundlePath, classLoader.orElse(Thread.currentThread().getContextClassLoader()));
		}
	}
	
	private String resouceBundlePath;
	private ClassLoader classLoader;

	public static Builder byPath(String bundlePath) {
        return new Builder(bundlePath);
    }
	
	private ErrorMessageBundle(String resouceBundlePath, ClassLoader classLoader) {
		this.resouceBundlePath = resouceBundlePath;
		this.classLoader = classLoader;
	}

	public String get(String key, Locale preferredLanguage, Object... params) {
		try {
			Locale languageToUse = Optional.ofNullable(preferredLanguage).orElse(Locale.ENGLISH);
			ResourceBundle bundle = ResourceBundle.getBundle(this.resouceBundlePath, languageToUse, this.classLoader);
			if(bundle.containsKey(key)) {
				MessageFormat message = new MessageFormat(bundle.getString(key));
				String result = message.format(params);
				return result.replaceAll("\\{[0-9]+\\}", "");
			} 
		} catch (Exception slientAllErrors) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, slientAllErrors.getMessage(),
					slientAllErrors);
		}
		return key;
	}

}
