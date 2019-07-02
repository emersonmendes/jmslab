package br.com.emersonmendes.lookup;

import java.util.Properties;
import javax.enterprise.inject.spi.SessionBeanType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
public class LookerUp {
 
	private Properties prop = new Properties();

	public LookerUp(){
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);
	}

	@SuppressWarnings("unchecked")
	public <S> S getBean(SessionBeanType beanType, String beanName, Class<S> clazz) {

		String moduleName = "jmslab-server-impl";
		String suffix = "";

		S object;

		try {

			if(beanType.equals(SessionBeanType.STATEFUL)){
				suffix = "?stateful";
			}

			final Context context = new InitialContext(prop);
			String name = String.format("%s/%s!%s%s", moduleName, beanName, clazz.getName(), suffix);

			System.out.println("############################################");
			System.out.println("JNDI name: " + name);
			System.out.println("############################################");

			object = (S) context.lookup(name);
			context.close();

		} catch (Exception e) {
			throw new RuntimeException("Error :(", e);
		}

		return object;

	}

	public enum SessionBeanType {
		STATEFUL,
		STATELESS,
		SINGLETON
	}
 
}
 