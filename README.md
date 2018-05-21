# flume-sequoiadb-sink
  flume sequoiadb sink，支持sequoiadb增删改操作

# 版本要求
sequoiadb-driver-2.8.5.jar
flume-1.6.0以上

# 数据格式
## 标识说明
数据分隔符	","
操作分隔符	"|"
key-value分隔符	":"
## 数据操作符号
插入	I
更新	U
删除	D

## 数据样例
### 插入
数据操作符（I） + 操作分隔符（|） + 值列表
样例：

I|34406a98-e150-461f-9ae9-ff2ae2a5e0ac,name_34406a98-e150-461f-9ae9-ff2ae2a5e0ac,29,dept_34406a98-e150-461f-9ae9-ff2ae2a5e0ac,专科,男,1983-2,hometown_34406a98-e150-461f-9ae9-ff2ae2a5e0ac,country_34406a98-e150-461f-9ae9-ff2ae2a5e0ac,nation_34406a98-e150-461f-9ae9-ff2ae2a5e0ac,null,已婚,健康,2006-2,优秀,Addr:34406a98-e150-461f-9ae9-ff2ae2a5e0ac,TelNo:34406a98-e150-461f-9ae9-ff2ae2a5e0ac,34406a98-e150-461f-9ae9-ff2ae2a5e0ac@mail.com,job_34406a98-e150-461f-9ae9-ff2ae2a5e0ac,1526883625012

### 更新
数据操作符（I）+ 操作分隔符（|）+ 条件（key：value）+操作分隔符（|）+更新数据（key：value）
样例：
U|empNo:efafbbe0-a1c8-4baf-962c-0fe4945b15b3|empAge:38

### 删除
数据操作符（I）+ 操作分隔符（|）+ 删除条件（key：value）
D|empNo:1a041a45-2dec-4844-99b6-ceabf99716e5

# 配置样例
agent.sinks.sdbSink.type = org.apache.flume.sink.sequoiadb.SequoiaDBOneSink
agent.sinks.sdbSink.sequoiadb.servers=172.20.10.13:11810
agent.sinks.sdbSink.sequoiadb.collectionSpace=space
agent.sinks.sdbSink.sequoiadb.collection=collection
agent.sinks.sdbSink.sequoiadb.collection.field.delimiter=,
agent.sinks.sdbSink.sequoiadb.collection.fields=empNo,empName,empAge,deptId,empXL,empGender,empBirthday,empHometown,empCountry,empNation,empId,empMarriage,empHealth,empStartWorkDate,empState,empHomeAddr,empTeleNo,empEmail,jobId,timestamp
agent.sinks.sdbSink.sequoiadb.collection.field.types=string,string,int,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,long


