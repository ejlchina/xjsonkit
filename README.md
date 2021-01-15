# JsonKit

## 介绍

超轻量级 JSON **正序列化** 与 **反序列化** 的 **门面框架**，不依赖具体的 JSON 实现，让业务代码可以在不同的 JSON 实现（如：Jackson、Gson、Fastjson 等）之间 **切换自如**！

#### 将于 2021年1月20日 前正式发布到 Maven 中央仓库，敬请 Star 关注。

## 安装教程

### Maven

```xml
<dependency>
    <groupId>com.ejlchina</groupId>
    <artifactId>jsonkit</artifactId>
    <version>1.0.0</version>
</dependency>
```

使用 Jackson 实现：

```xml
<dependency>
    <groupId>com.ejlchina</groupId>
    <artifactId>jsonkit-jackson</artifactId>
    <version>1.0.0</version>
</dependency>
```

使用 Gson 实现：

```xml
<dependency>
    <groupId>com.ejlchina</groupId>
    <artifactId>jsonkit-gson</artifactId>
    <version>1.0.0</version>
</dependency>
```

使用 Fastjson 实现：

```xml
<dependency>
    <groupId>com.ejlchina</groupId>
    <artifactId>jsonkit-fastjson</artifactId>
    <version>1.0.0</version>
</dependency>
```

以上依赖添加一个即可。

### Gradle

```groovy
implementation 'com.ejlchina:jsonkit:1.0.0'
```

使用 Jackson 实现：

```groovy
implementation 'com.ejlchina:jsonkit-jackson:1.0.0'
```

使用 Gson 实现：

```groovy
implementation 'com.ejlchina:jsonkit-gson:1.0.0'
```

使用 Fastjson 实现：

```groovy
implementation 'com.ejlchina:jsonkit-fastjson:1.0.0'
```

以上依赖添加一个即可。

## 使用说明

### 反序列化 toMapper

```java
String json = "{\"name\":\"Jack\",\"age\":20}";
// 转换为具有映射结构的 Mapper 对象
Mapper mapper = JSONKit.toMapper(json);

// 第一层的键集合大小
int size = mapper.size();               // 2
// 第一层的键值集合
Set<String> keys = mapper.keySet();     // ["name", "age"]
// 按键名访问 String 属性
String name = mapper.getString("name"); // Jack
// 按键名访问 int 属性
int age = mapper.getInt("age");         // 20
    
// 遍历 Mapper 对象
mapper.forEach((key, data) -> {
    System.out.println(key);            // 依次输出 name 和 age
    System.out.println(data);           // 依次输出 Jack 和 20
});

System.out.println(mapper);             // 输出 {"name":"Jack","age":20}
```

### 反序列化 toArray

```java
String json = "[20,{\"name\":\"Jack\"},\"JsonKit\"]";
// 转换为具有数组结构的 Array 对象
Array array = JSONKit.toArray(json);

// 数组大小
int size = array.size();                // 3
// 按下标获取 int 数据
int value = array.getInt(0);            // 20
// 按下标获取 Mapper 数据
Mapper mapper = array.getMapper(1);     // {"name":"Jack"}
// 按下标获取 String 数据
String string = array.getString(2);     // JsonKit

// 遍历 Array 对象
array.forEach(((index, data) -> {
    System.out.println(index);          // 依次输出 0、 1 和 2
    System.out.println(data);           // 依次输出 20、 {"name":"Jack"} 和 JsonKit
}));

System.out.println(array);              // 输出 [20,{"name":"Jack"},"JsonKit"]
```

### 反序列化 toBean

```java
String json = "{\"name\":\"Jack\",\"age\":20}";
// 根据类型 转换为 Java Bean
User user = JSONKit.toBean(User.class, json);

String name = user.getName();           // Jack
int name = user.getAge();               // 20
```

### 反序列化 toList

```java
String json = "[{\"name\":\"Jack\",\"age\":20}, {\"name\":\"Tom\",\"age\":21}]";
// 根据类型 转换为 Java List
List<User> list = JSONKit.toList(User.class, json);

int size = list.size();                 // 2
User user1 = list.get(0);               // {"name":"Jack","age":20}
User user2 = list.get(1);               // {"name":"Tom","age":21}
```

### 正序列化 toJson

```java
User user = new User();
user.setName("Jack");
user.setAge(20);

String json = JSONKit.toJson(user);     // 转换为 JSON 字符串

System.out.println(json);               // 输出 {"age":20,"name":"Jack"}
```

### 配置 JSONKit

如果已经添加了如 `jsonkit-jackson` 的适配包，默认不用配置即可使用。

如果需要特殊配置，例如使用 Jackson 时可在项目启动时做如下配置：

```java
ObjectMapper objectMapper = new ObjectMapper();

// 对 ObjectMapper 进行配置...
    
// 对 JSONKit 进行初始化
JSONKit.init(new JacksonDataConvertor(ObjectMapper));
```

使用其它扩展包也类似操作。

## 参与贡献

1. Star Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

