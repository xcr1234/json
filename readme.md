#Java 开源JSON库 - XSON

目前有很多可靠的json库，例如gson、org.json、fastjson；但是还是自己实现了一下一套完整的json流程。（之前也实现过，现在属于重构）

##主要原理：
基于antlr框架提供的强大的词法分析和语法分析功能，书写json的文法、Listener监听器；通过antlr执行。
整个流程为
String/InputStream -> Lexer -> Parser -> Tree
Walker- >Tree -> JsonObject/JsonArray

##主要方法：

###整个框架的核心是Json类，它主要提供了这几个方法

其中的feature都可以省略例如下面代码都是可以的
```java
Json.toJsonString(object)
Json.toJsonString(object,null)
```


```java
public abstract class Json{
   
    
    //将Java对象转换为Json字符串。
    public static String toJsonString(Object obj, SerializeFeature feature);
    
    //将json字符串解析为JsonObject，解析失败将抛出JsonParseException
    public static JsonObject parseObject(String jsonString, DeserializeFeature feature);
    
    //跟上面的parseObject方法功能一样，不过是提供了流的api
    public static JsonObject parseObject(InputStream inputStream,DeserializeFeature feature) 
            throws IOException;
    
    //将json字符串解析为JsonArray，解析失败抛出JsonParseException
    public static JsonArray parseArray(String jsonString, DeserializeFeature feature);
    
    public static JsonArray parseArray(InputStream inputStream, DeserializeFeature feature) 
            throws IOException;
    
    //将json字符串转换为java Bean对象，要求bean对象有一个public的无参构造函数
    public static <T extends JsonBeanAware> T parseBean(String jsonString,
            Class<T> type,DeserializeFeature feature);
            
    public static <T extends JsonBeanAware> T parseBean(InputStream inputStream,
            Class<T> type,DeserializeFeature feature) throws IOException ;
    
    //将json字符串转换为java Bean对象的集合，要求bean对象有一个public的无参构造函数；返回的是一个ArrayList
    public static <T extends JsonBeanAware> List<T> parseBeanList(String jsonString,
            Class<T> type,DeserializeFeature feature);
    public static <T extends JsonBeanAware> List<T> parseBeanList(InputStream inputStream,
            Class<T> type ,DeserializeFeature feature) throws IOException;
    
}
```


自己的java Bean对象如果要实现自动转换为json，可以选择实现JsonAware接口或者JsonObjectAware接口；
如果要能从json字符串中解析，则必须实现JsonBeanAware接口。

更多例子请参考：[例子](src/test/java/com/xson)


---
**参考文档**

[**The Definitive ANTLR 4 Reference, 2nd Edition**](http://www.java1234.com/a/javabook/javabase/2015/0923/4973.html)  

[**使用 Antlr 开发领域语言**](http://www.ibm.com/developerworks/cn/java/j-lo-antlr/)