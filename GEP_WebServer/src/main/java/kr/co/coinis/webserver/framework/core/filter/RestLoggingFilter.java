package kr.co.coinis.webserver.framework.core.filter;
//package kr.co.coinis.webserver.framework.core.filter;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.util.Enumeration;
//import java.util.UUID;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
///**
// * 
//* <pre>
//* 1.  기능	:  
//* 2.  처리개요	:
//* -  View/Restful In/Out Logging Filter
//* - [참조]
//*    ==>https://angelborroy.wordpress.com/2009/03/04/dump-request-and-response-using-javaxservletfilter/    
//* 3.  주의사항	:  
//* 4.  작성자/작성일	:  김경주/2018. 2. 20.
//* ====================================
//* 5.  수정사항
//* 5.1 요구사항 ID	:
//* - 수정자/수정일 	: 
//* - 수정사유/내역 	:
//* ====================================
//* <p/>
//* @author		: 김경주
//* @since		: J2EE 1.8
//* </pre>
// */
//public class RestLoggingFilter implements Filter {
//	private static final Logger logger = LoggerFactory.getLogger(RestLoggingFilter.class);
//	private boolean dumpRequest;
//	private boolean dumpResponse;
//
//	private static class ByteArrayServletStream extends ServletOutputStream {
//		ByteArrayOutputStream baos;
//
//		ByteArrayServletStream(ByteArrayOutputStream baos) {
//			this.baos = baos;
//		} 
//
//		public void write(int param) throws IOException {
//			baos.write(param); 
//		}
//	}
//
//	private static class ByteArrayPrintWriter {
//		private ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		private PrintWriter pw = new PrintWriter(baos);
//		private ServletOutputStream sos = new ByteArrayServletStream(baos);
//
//		public PrintWriter getWriter() {
//			return pw;
//		}
//
//		public ServletOutputStream getStream() {
//			return sos;
//		}
//
//		byte[] toByteArray() {
//			return baos.toByteArray();
//		}
//	}
//
//	private class BufferedServletInputStream extends ServletInputStream {
//		ByteArrayInputStream bais;
//
//		public BufferedServletInputStream(ByteArrayInputStream bais) {
//			this.bais = bais;
//		}
//
//		public int available() {
//			return bais.available();
//		}
//
//		public int read() {
//			return bais.read();
//		}
//
//		public int read(byte[] buf, int off, int len) {
//			return bais.read(buf, off, len);
//		}
//
//	}
//
//	private class BufferedRequestWrapper extends HttpServletRequestWrapper {
//		ByteArrayInputStream bais;
//		ByteArrayOutputStream baos;
//		BufferedServletInputStream bsis;
//		byte[] buffer;
//
//		public BufferedRequestWrapper(HttpServletRequest req)
//				throws IOException {
//			super(req);
//			InputStream is = req.getInputStream();
//			baos = new ByteArrayOutputStream();
//			byte buf[] = new byte[1024];
//			int letti;
//			while ((letti = is.read(buf)) > 0) {
//				baos.write(buf, 0, letti);
//			}
//			buffer = baos.toByteArray();
//		}
//
//		public ServletInputStream getInputStream() {
//			try {
//				bais = new ByteArrayInputStream(buffer);
//				bsis = new BufferedServletInputStream(bais);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//			return bsis;
//		}
//
//		public byte[] getBuffer() {
//			return buffer;
//		}
//
//	}
//
//	public void init(FilterConfig filterConfig) throws ServletException {
//		dumpRequest = Boolean.valueOf(filterConfig.getInitParameter("dumpRequest"));
//		dumpResponse = Boolean.valueOf(filterConfig.getInitParameter("dumpResponse"));
//	}
//
//	public void doFilter(ServletRequest servletRequest,
//			ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//
//		final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//		BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(httpRequest);
//		/** 요청 Payload 정보를 받음 **/ 
//		String uniqueID = UUID.randomUUID().toString();
//		if (dumpRequest) {
//			/** uniqueID Session Setter **/
//			RequestContextHolder.currentRequestAttributes().setAttribute("txid", uniqueID, RequestAttributes.SCOPE_SESSION);
//			logger.debug("");
//			logger.debug("\n\n\n---------------------------TXID:"+uniqueID+"---------------------------");
////			logger.debug("REQUEST -> "+ new String(bufferedRequest.getBuffer()));
//			Enumeration headerNames=bufferedRequest.getHeaderNames();
//			 while(headerNames.hasMoreElements()) {
//	            String headerName = (String)headerNames.nextElement();
//	            logger.debug(headerName + " = " + httpRequest.getHeader(headerName));
//		    }
//		
//	        logger.debug("\n---------------------------Parameters---------------------------");
//	        Enumeration params = httpRequest.getParameterNames();
//	        while(params.hasMoreElements()){
//	            String paramName = (String)params.nextElement();
//	            logger.debug(paramName + " = " + httpRequest.getParameter(paramName));
//	        }
//	        
//	        logger.debug("\n---------------------------Payload---------------------------");
//	        logger.debug(new String(bufferedRequest.getBuffer()));
//	        logger.debug("\n---------------------------Payload---------------------------");
//		}
//
//		final HttpServletResponse response = (HttpServletResponse) servletResponse;
//		final ByteArrayPrintWriter pw = new ByteArrayPrintWriter();
//		
//		HttpServletResponse wrappedResp = new HttpServletResponseWrapper(response) {
//			public PrintWriter getWriter() {
//				return pw.getWriter();
//			}
//
//			public ServletOutputStream getOutputStream() {
//				return pw.getStream();
//			}
//
//		};
//
//		filterChain.doFilter(bufferedRequest, wrappedResp);
//
//		byte[] bytes = pw.toByteArray();
//		response.getOutputStream().write(bytes);
//		if (dumpResponse){
//			
////			logger.debug("RESPONSE -> " + new String(bytes));
//			/*Iterator<String> headerNames= response.getHeaderNames().iterator();
//			 while(headerNames.hasNext()) {
//	            String headerName = (String)headerNames.next(); 
//	            logger.debug(headerName + " = " + response.getHeader(headerName));
//		    }*/
//		    logger.debug("\n---------------------------Parameters---------------------------");
//		    /**
//		     * TODO ResponseParameter 구현 
//		     */
//		    logger.debug("\n---------------------------Payload---------------------------");
//	        logger.debug(new String(bytes));
//	        logger.debug("\n---------------------------Payload---------------------------");
//	        
//	        logger.debug("\n---------------------------TXID:"+uniqueID+"---------------------------");
//		}
//	} 
//	
//	public void destroy() {
//	}
//}
