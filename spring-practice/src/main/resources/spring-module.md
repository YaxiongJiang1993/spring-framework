Spring Framework 模块功能详解
核心容器模块（Core Container）
1. spring-core
   功能：核心工具类与基础设施
   内容：
   IoC 容器基础
   资源访问（Resource、ResourceLoader）
   类型转换（Converter、Formatter）
   工具类（StringUtils、CollectionUtils 等）
   内嵌 CGLIB 和 Objenesis（类增强与实例化）
   支持 Kotlin、Groovy、Reactor 等
2. spring-beans
   功能：Bean 定义与依赖注入
   内容：
   BeanFactory 接口与实现
   Bean 定义（BeanDefinition）
   依赖注入（构造器、setter、字段注入）
   Bean 作用域（singleton、prototype 等）
   支持 Groovy 和 Kotlin 的 Bean 定义
3. spring-context
   功能：应用上下文与高级特性
   内容：
   ApplicationContext 接口与实现
   事件发布与监听（ApplicationEvent）
   国际化（MessageSource）
   环境抽象（Environment、Profile）
   注解驱动（@Component、@Autowired 等）
   支持 JSR-330（@Inject）、JSR-250（@PostConstruct）
4. spring-expression (SpEL)
   功能：Spring 表达式语言
   内容：
   运行时表达式求值
   支持对象图导航、方法调用、集合操作
   用于 @Value、@ConditionalOnExpression 等
   AOP 模块
5. spring-aop
   功能：面向切面编程
   内容：
   代理机制（JDK 动态代理、CGLIB）
   切点（Pointcut）、通知（Advice）
   切面（Aspect）
   与 AspectJ 集成
6. spring-aspects
   功能：AspectJ 切面支持
   内容：
   @Configurable（非 Spring 管理的对象注入）
   @Async（异步方法）
   @Transactional（事务）
   JCache 支持
   使用 AspectJ 编译时织入
   数据访问模块（Data Access）
7. spring-jdbc
   功能：JDBC 抽象层
   内容：
   JdbcTemplate（简化 JDBC）
   数据源抽象（DataSource）
   异常转换（DataAccessException）
   命名参数支持
8. spring-tx
   功能：声明式事务管理
   内容：
   编程式与声明式事务
   @Transactional
   事务管理器抽象（PlatformTransactionManager）
   支持 JTA、JDBC、JPA 等
   响应式事务支持（Reactor）
9. spring-orm
   功能：ORM 框架集成
   内容：
   Hibernate、JPA 集成
   异常转换
   会话管理
   延迟加载支持
10. spring-oxm
    功能：对象/XML 映射
    内容：
    支持 JAXB、XStream、JiBX、Castor
    统一抽象接口（Marshaller、Unmarshaller）
    XML 与 Java 对象互转
11. spring-jms
    功能：Java 消息服务
    内容：
    JmsTemplate（简化 JMS）
    消息监听器容器
    与 Spring Messaging 集成
12. spring-r2dbc
    功能：响应式数据库访问
    内容：
    R2DBC 规范支持
    响应式数据库操作
    基于 Reactor 的非阻塞 API
    Web 模块
13. spring-web
    功能：Web 基础功能
    内容：
    HTTP 客户端抽象（RestTemplate、WebClient）
    文件上传（MultipartResolver）
    HTTP 消息转换（HttpMessageConverter）
    支持 JSON、XML、Protobuf 等
    Servlet API 集成
14. spring-webmvc
    功能：Spring MVC（Servlet 栈）
    内容：
    DispatcherServlet
    控制器（@Controller、@RestController）
    视图解析（ViewResolver）
    拦截器（HandlerInterceptor）
    支持 JSP、FreeMarker、Thymeleaf 等
    RESTful 支持
15. spring-webflux
    功能：响应式 Web 框架
    内容：
    基于 Reactor 的非阻塞 Web
    RouterFunction、HandlerFunction
    WebClient（响应式 HTTP 客户端）
    支持 Netty、Undertow、Tomcat 等
16. spring-websocket
    功能：WebSocket 支持
    内容：
    WebSocket API 抽象
    STOMP 消息协议
    与 Spring MVC、WebFlux 集成
    支持 Tomcat、Jetty、Undertow
    消息传递模块
17. spring-messaging
    功能：消息传递抽象
    内容：
    消息通道（MessageChannel）
    消息处理器（MessageHandler）
    消息转换器（MessageConverter）
    为 WebSocket、JMS、RSocket 等提供基础
    其他支持模块
18. spring-context-support
    功能：上下文扩展支持
    内容：
    缓存抽象（CacheManager）
    邮件支持（JavaMail）
    任务调度（Quartz、CommonJ）
    模板引擎（FreeMarker）
    支持 Caffeine、EhCache 等
19. spring-context-indexer
    功能：编译时组件索引
    内容：
    编译时生成组件索引
    加快应用启动
    通过 @Indexed 标记组件
20. spring-instrument
    功能：类加载器工具
    内容：
    Java Agent 支持
    类加载时增强
    用于加载时织入（Load-Time Weaving）
21. spring-jcl
    功能：日志桥接
    内容：
    Jakarta Commons Logging 桥接
    自动适配 Log4j2、SLF4J
    统一日志接口
22. spring-test
    功能：测试支持框架
    内容：
    TestContext 框架
    @SpringBootTest、@WebMvcTest 等
    Mock 对象支持
    集成测试工具
    支持 JUnit 4/5、TestNG
    特殊模块
23. spring-practice
    功能：学习示例模块（自定义）
    内容：
    Bean 生命周期示例
    AOP 示例
    扩展点示例（BeanPostProcessor、BeanFactoryPostProcessor）
    MyBatis 集成示例
    事务管理示例
    模块依赖关系
    spring-core (基础)    ↓spring-beans (依赖 spring-core)    ↓spring-context (依赖 spring-beans, spring-core, spring-expression, spring-aop)    ↓spring-web (依赖 spring-beans, spring-core)    ↓spring-webmvc (依赖 spring-web, spring-context, spring-aop)spring-webflux (依赖 spring-web, spring-beans, spring-core)
    使用建议
    最小化：spring-core + spring-beans + spring-context
    Web 应用：+ spring-web + spring-webmvc
    响应式应用：+ spring-web + spring-webflux
    数据访问：+ spring-jdbc + spring-tx（或 spring-orm）
    测试：+ spring-test
    各模块可独立使用，按需引入。