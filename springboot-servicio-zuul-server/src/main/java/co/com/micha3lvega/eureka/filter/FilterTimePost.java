package co.com.micha3lvega.eureka.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class FilterTimePost extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(FilterTimePost.class);

	/**
	 * Metodo que determina si se ejecuta o no el filtro
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * Metodo que dice que va ha hacer el filtro
	 */
	@Override
	public Object run() throws ZuulException {

		log.info("(run) start");

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("(run) {} request enrutado {}", request.getMethod(), request.getRequestURL());

		Long start = (Long) request.getAttribute("startTime");
		log.info("(run) Start: {}", start);
		
		Long end = System.currentTimeMillis();
		log.info("(run) End: {}", end);
		
		Long total = end - start;
		log.info("(run) tiempo transcurrido: {}", total);
		
		if (total > 0) {
			log.info("(run) tiempo transcurrido: {} (segundos) ", (total/1000));
		}

		request.setAttribute("total", total);

		return null;
	}

	/**
	 * Metodo que determina el tipo del filtro puede ser pre|post|root;
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
