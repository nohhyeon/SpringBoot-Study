package su.boot.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UnicodeNormalizationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		UnicodeNormalizationRequestWrapper requestWrapper = new UnicodeNormalizationRequestWrapper(httpServletRequest);
		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {
	}

	private class UnicodeNormalizationRequestWrapper extends HttpServletRequestWrapper {
		public UnicodeNormalizationRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			return normalize(value);
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] values = super.getParameterValues(name);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					values[i] = normalize(values[i]);
				}
			}
			return values;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			return super.getParameterMap().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
				String[] values = entry.getValue();
				for (int i = 0; i < values.length; i++) {
					values[i] = normalize(values[i]);
				}
				return values;
			}));
		}

		private String normalize(String value) {
			if (value == null) {
				return null;
			}
			return Normalizer.normalize(value, Normalizer.Form.NFC);
		}
	}
}
