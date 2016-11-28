#Java 开源JSON库 - XSON

目前有很多可靠的json库，例如gson、org.json、fastjson；但是还是自己实现了一下一套完整的json流程。（之前也实现过，现在属于重构）  

[下载jar包使用](https://raw.githubusercontent.com/xcr1234/json/master/lib/json.zip)

在Maven中构建
```xml
<dependency>
  <groupId>io.github.xcr1234</groupId>
  <artifactId>xson</artifactId>
  <version>1.2</version>
</dependency>
```


##主要原理：
基于antlr框架提供的强大的词法分析和语法分析功能，书写json的文法、Listener监听器；通过antlr执行。  
整个流程为  
String/InputStream -> Lexer -> Parser -> Tree  
Walker- >Tree -> JsonObject/JsonArray  

`JsonObject`的底层其实就是一个`Map<String,Object>`（默认实现为`LinkedHashMap`）；  
而`JsonArray`的底层就是一个`List<Object>`（默认为`ArrayList<Object>`）。  

##主要方法：

**xson框架的入口类是com.xson.Json，并提供了两个非常实用的类JsonObject、JsonArray。**

序列化：

`String json = Json.toJsonString(object);`

如果一个java bean对象要被json序列化，它必须实现JsonAware、JsonObjectAware、JsonArrayAware接口任意之一，
JsonObjectAware、JsonArrayAware接口相当于是JsonAware的一个扩展。


反序列化：
如果一个java bean对象要被json库反序列化，那么它应该实现JsonBeanAware接口。
```java
JsonObject jsonObject = Json.parseObject("...");
JsonArray jsonArray = Json.parseArray("...");

VO vo = Json.parseBean("...",VO.class);
List<VO> voList = Json.parseBeanList("...",VO.class);
```

## JsonObject/JsonArray的类型转化

`JsonObject`和`JsonArray`类的getXXX方法支持自动类型转换，例如put时的value类型是int，在调用getString方法时会自动将int转换为String。

例子：

```java
jsonObject.put("num",100);
String str = jsonObject.getString("num"); //自动将int转换为String，结果为"100"
String str2 = (String)jsonObject.get("num");  //强制转换失败，抛出ClassCastException

jsonObject.put("str","100");
int num = jsonObject.getInt("str"); //自动转换为数字100.
```

如果自动转换也失败了，则会抛出`JsonCastException`。

## Java Bean对象的例子
```java
class Bean implents JsonObjectAware,JsonBeanAware{
	private int id;
	private String name;
	
	// ...
	// 省略get set 方法
	
	@Override
    public void toJson(JsonObject jsonObject, SerializeFeature feature) {
		//将bean对象的所有属性注入到JsonObject当中。
		jsonObject.put("id",id");
		jsonObject.put("name",name);
	}
	
	@Override
    public void parse(JsonObject jsonObject, DeserializeFeature feature) {
		//从JsonObject中读取出这个类的所有属性。
		this.id = jsonObject.getInt("id");
		this.name = jsonObject.getString("name");
	}
	
}

```


## 如何定制序列化

Json类所有的方法后面都支持feature参数，例如
```java
Json.toJsonString(object,serializeFeature);`
JsonObject jsonObject = Json.parseObject("...",deSerializeFeature);
```

默认的feature实现分别是`DefaultSerializeFeature`和`DefaultDeserializeFeature`，您有两种方式修改序列化/反序列化配置。

方式一，调用DefaultSerializeFeature的各种set方法：

```java
DefaultSerializeFeature serializeFeature = new DefaultSerializeFeature();
serializeFeature.setWriteCollectionAsJson(false);   //是否将集合类型转为JsonArray输出
serializeFeaturesetWritesNullValue(true);   //是否输出value为null的key.
```

方式二：重写DefaultSerializeFeature的接口方法。
```java
SerializeFeature serializeFeature = new DefaultSerializeFeature(){
            @Override
            public boolean writeCollectionAsJson() {
                return false;
            }

            @Override
            public boolean writesNullValue() {
                return true;
            }
        };
```

全局默认配置

`DefaultSerializeFeature.globalDefaultFuture`和`DefaultDeserializeFeature.globalDefaultDeserializeFeature`

## 例子

更多例子请参考：[例子](src/test/java/com/xson)


---
**参考文档**

[**The Definitive ANTLR 4 Reference, 2nd Edition**](http://www.java1234.com/a/javabook/javabase/2015/0923/4973.html)  

[**使用 Antlr 开发领域语言**](http://www.ibm.com/developerworks/cn/java/j-lo-antlr/)