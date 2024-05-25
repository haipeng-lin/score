# 基于SpringBoot的积分权益服务平台
后端核心代码
### 2.项目代码组织结构

```java
|—— score-server
	|—— score-api		//通用的枚举类、自定义异常、基础DO、基础DTO、VO
	|—— score-core		//核心工具/组件，如工具包util，通用的组件
	|—— score-service	//业务模块
	|—— score-web		//web模块，包括权限身份校验、全局异常处理
```

#### 2.1项目详细分层结构

```java
|—— score-api
	|—— src
		|—— main
			|—— java
				|—— com.github.score.api.model
					|—— context
						|—— ReqInfoContext 	//请求上下文信息
					|—— entity
						|—— BaseDO			//基础领域对象
						|—— BaseDTO			//基础数据传输对象
					|—— enums
						|—— site			//站点统计枚举
						|—— StatusEnum		//状态码枚举
					|—— event
						|—— ConfigRefreshEvent	//配置变更消息事件
					|—— exception
						|—— ScoreException	//自定义异常
						|—— ExceptionUtil	//异常工具类
					|—— vo
						|—— ResVo			//响应视图对象
						|—— Status			//状态对象
					
|—— score-core
	|—— src
		|—— main
			|—— java
				|—— com.github.score.core
					|—— cache				//缓存包
					|—— util				//工具包
					|—— common				//通用常量
					|—— permission			//权限
					
|—— score-service
	|—— src
		|—— main
			|—— java
				|—— com.github.score.service
					|—— sitemap			//站点统计业务
						|—— constants		//常量
						|—— model			//模型
						|—— service			//业务
					|—— user			//用户业务
					
|—— score-web
	|—— src
		|—— main
			|—— java
			|	|—— com.github.score.web
			|		|—— admin			//后台管理接口
			|		|—— common			//通用接口
			|		|—— config			//配置接口
            |       |—— error			//异常接口
            |       |—— front			//前台接口
            |       |—— global			//全局接口
            |        	|—— vo
            |        		|—— GlobalVo		//全局视图对象
            |        	|—— GlobalInitService	//全局初始业务
            |        	|—— ScoreExceptionHandle	//全局异常处理器
            |       |—— hook			//勾子
            |        	|—— filter		//过滤器（获取用户信息）
            |        	|—— interceptor	//拦截器（权限认证）
			|—— resource				//资源信息
			|—— resource-env			//环境配置
				|—— dev		//本地环境
				|—— prod	//线上环境
				|—— test	//测试环境
```

