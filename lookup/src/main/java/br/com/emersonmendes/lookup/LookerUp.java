package br.com.emersonmendes.lookup;

import java.util.Properties;
import javax.enterprise.inject.spi.SessionBeanType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
public class LookerUp {
 
	private Properties prop = new Properties();

	public LookerUp(){
		String address = "localhost";
		int httpPort = 8080;
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, String.format("http-remoting://%s:%s/", address, httpPort));
		prop.put("jboss.naming.client.ejb.context", true);
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	}

	@SuppressWarnings("unchecked")
	public <S> S getBean(SessionBeanType beanType, String beanName, Class<S> clazz) {

		String earName = "jmslab-ear";
		String moduleName = "jmslab-web";
		String deploymentDistinctName = "";
		String suffix = "";

		S object;

		try {

			if(beanType.equals(SessionBeanType.STATEFUL)){
				suffix = "?stateful";
			}

			final Context context = new InitialContext(prop);
			object = (S) context.lookup(String.format("ejb:%s/%s/%s/%s!%s%s", earName, moduleName, deploymentDistinctName, beanName, clazz.getName(), suffix));
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
 