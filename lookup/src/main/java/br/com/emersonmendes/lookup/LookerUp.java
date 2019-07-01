package br.com.emersonmendes.lookup;

import java.util.Properties;
import javax.enterprise.inject.spi.SessionBeanType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
public class LookerUp {
 
	private Properties prop = new Properties();

	public LookerUp(String address, int httpPort){
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, String.format("http-remoting://%s:%s/", address, httpPort));
		prop.put("jboss.naming.client.ejb.context", true);
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	}

	public Object findRemoteSessionBean(SessionBeanType beanType, String earName, String moduleName, String deploymentDistinctName, String beanName, String interfaceFullQualifiedName) throws NamingException{

		String suffix = "";

		if(beanType.equals(SessionBeanType.STATEFUL)){
			suffix = "?stateful";
		}

		final Context context = new InitialContext(prop);
		Object object = context.lookup(String.format("ejb:%s/%s/%s/%s!%s%s", earName, moduleName, deploymentDistinctName, beanName, interfaceFullQualifiedName, suffix));
		context.close();

		return object;
	}

	public enum SessionBeanType {
		STATEFUL,
		STATELESS,
		SINGLETON
	}
 
}
 