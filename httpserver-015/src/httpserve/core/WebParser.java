package httpserve.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 解析服务器中的web.xml配置文件
 * 
 * @author DCG19
 *
 */
public class WebParser {

	public static Map<String, Map<String, String>> servletMaps = new HashMap<String, Map<String, String>>();

	/**
	 * 解析服务器中所有应用的web.xml
	 * 
	 * @param webAppNames
	 * @throws DocumentException 
	 */
	public static void parser(String[] webAppNames) throws DocumentException {
		for(String webAppName:webAppNames) {
			Map<String, String> servletMap=parser(webAppName);
			servletMaps.put(webAppName,servletMap);
		}

	}

	/**
	 * 解析单个应用的web.xml配置文件
	 * 
	 * @param webAppName
	 * @return
	 * @throws DocumentException
	 */
	private static Map<String, String> parser(String webAppName) throws DocumentException {
		// 获取web.xml路径
		String webPath = webAppName + "/WEB-INF/web.xml";
		// 创建解析器
		SAXReader saxReader = new SAXReader();
		// 通过解析器的read方法将配置文件读取到内存中，生成一个Document[org.dom4j]对象树
		Document document = saxReader.read(new File(webPath));
		// 获取servlet节点元素
		List<Element> servletNodes = document.selectNodes("/web-app/servlet");
		// 创建一个servletInfoMap集合，将servlet-name和servlet-class的值分别当作key和value存放到该集合中
		Map<String, String> servletInfoMap = new HashMap<String, String>();
		// 开始遍历serveltNodes
		for (Element servletNode : servletNodes) {
			// 获取servelt-name节点元素对象
			Element servletNameElt = (Element) servletNode.selectSingleNode("servlet-name");
			// 获取servletNameElt节点元素对象的值
			String servletName = servletNameElt.getStringValue();

			// 获取servlet-class节点元素对象
			Element servletClassElt = (Element) servletNode.selectSingleNode("servlet-class");
			// 获取servletClassElt节点元素对象的值
			String servletClassName = servletClassElt.getStringValue();
			// 将servletName和servletClassName的值分别当作key和value存放到servletInfoMap集合中
			servletInfoMap.put(servletName, servletClassName);

		}
		// 获取servelt-mapping节点元素对象:web-app->servelt-mapping
		List<Element> servletMappingNodes = document.selectNodes("/web-app/servlet-mapping");
		// 创建一个servletMappingInfoMap集合，将servlet-name和url-pattern的值分别当作key和value存放到该集合中
		Map<String, String> servletMappingInfoMap = new HashMap<String, String>();
		// 开始遍历serveltNodes
		for (Element servletMappingNode : servletMappingNodes) {
			// 获取servelt-name节点元素对象
			Element servletNameElt = (Element) servletMappingNode.selectSingleNode("servlet-name");
			// 获取servletNameElt节点元素对象的值
			String servletName = servletNameElt.getStringValue();

			// 获取url-pattern节点元素对象
			Element urlPatternElt = (Element) servletMappingNode.selectSingleNode("url-pattern");
			// 获取urlPatternElt节点元素对象的值
			String urlPattern = urlPatternElt.getStringValue();

			// 将servletName和urlPattern的值分别当作key和value存放到servletInfoMap集合中
			servletMappingInfoMap.put(servletName, urlPattern);
		}

		// 获取servletInfoMap或者servletMappingINfoMap任何一个key值的集合
		Set<String> servletNames = servletInfoMap.keySet();
		// 创建一个servletMap集合,将servletMappingInfoMap的value和servletInfoMap的value分别当作key和value存放到该map集合中
		Map<String, String> servletMap = new HashMap<String, String>();
		// 开始遍历serveltNames
		for (String servletName : servletNames) {
			// 获取servletMappingInfoMap集合中的value:ur1Pattern
			String urlPattern = servletMappingInfoMap.get(servletName);
			// 获取servletInfoMap集合中的value: servletclass
			String servletClassName = servletInfoMap.get(servletName);
			// 将urlPattern和servletclassName分别当作key和value存放到servletMap集合中
			servletMap.put(urlPattern, servletClassName);
		}

		return servletMap;
	}
}
