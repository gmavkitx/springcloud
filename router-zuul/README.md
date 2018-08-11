# 基于Zuul的网关路由

## 文件上传


如果你有一个路由规则zuul.routes.customers=/customers/**, 那么就可以使用/zuul/customers/*上传文件,一般情况下我们还需要设置下超时时间,

```yaml
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 60000
ribbon:
  ConnectTimeout: 3000
  ReadTimeout: 60000
```

上传大文件时要在头信息里添加chunked,示例

```bash
curl -v -H "Transfer-Encoding: chunked" -F "file=@mylarge.iso" localhost:9999/zuul/simple/file
``` 

## 多个Filter间数据的共享方式

使用RequestContext进行共享数据, RequestContext使用了ThreadLocal. RequestContext中还保存了HttpServletRequest和HttpServletResponse,
RequestContext继承了ConcurrentHashMap

## @EnableZuulProxy和@EnableZuulServer的区别

Spring Cloud Netflix安装了许多过滤器，具体取决于使用哪个注释来启用Zuul。 @EnableZuulProxy是@EnableZuulServer的超集。 
换句话说，@ EnableZuulProxy包含@EnableZuulServer安装的所有过滤器。 如果你想要一个“空白”Zuul，你应该使用@EnableZuulServer。

## zuul的错误处理

如果在Zuul过滤器生命周期的任何部分期间抛出异常，则执行SendErrorFilter。仅当RequestContext.getThrowable（）不为null时，才会运行SendErrorFilter。
然后，它在请求中设置特定的javax.servlet.error。*属性，并将请求转发到Spring Boot错误页面。