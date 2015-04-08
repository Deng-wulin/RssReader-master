package com.hero.rssreader.entity;

import java.io.Serializable;
/**标准的Rss xml文件
 * <?xml version="1.0" encoding="ISO-8859-1" ?>
 * <rss version="2.0">
 *<channel>
 *  <title>W3Schools Home Page</title>
 * <link>http://www.w3schools.com</link>
 * <description>Free web building tutorials</description>
 *<item>
 *  <title>RSS Tutorial</title>
 *  <link>http://www.w3schools.com/rss</link>
 *  <description>New RSS tutorial on W3Schools</description>
 *</item>
</channel>

</rss>
 * Rss Item 参考手册
 * <author>	 可选的。规定项目作者的电子邮件地址。
 * <category>	可选的。定义项目所属的一个或多个类别。
 * <comments>	可选的。允许项目连接到有关此项目的注释（文件）。
 * <description>	必需的。描述此项目。
 * <enclosure>	可选的。允许将一个媒体文件导入一个项中。
 * <guid>	可选的。为项目定义一个唯一的标识符。
 * <link>	必需的。定义指向此项目的超链接。
 * <pubDate> 可选的。定义此项目的最后发布日期。
 * <source>	可选的。为此项目指定一个第三方来源。
 * <title>	必需的。定义此项目的标题。
 * @author wulin
 *
 */
public class RssEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <title>	必需的。定义此项目的标题。
	 */
	public String title;
	/**
	 * <link>   必需的。定义指向此项目的超链接。
	 */
	public String link;
	/**
	 * <description>	必需的。描述此项目。
	 */
	public String description;
	/**
	 * 可选的。规定项目作者的电子邮件地址。
	 */
	public String author;
	/**
	 * <pubDate> 可选的。定义此项目的最后发布日期。
	 */
	public String pubDate;
	/**
	 * <guid>	可选的。为项目定义一个唯一的标识符。
	 */
	public String guid;
}