# tools-java

## 这是一个JAVA开发人员使用的工具
它需要部署
基于springboot搭建
## 目前主要功能：
  1. 通过建表语句生成对应的 model， mapper， sql 数据
## 注意：
  1. 建表语句推荐使用 show create table后 得到的标准化建表语句，自己写的会存在不兼容的问题，希望大家理解
  2. 生成后的数据，需要大家自己拷贝到已经建好的文件中
  3. 对于model中使用的数据类型，目前只支持 Date， Integer， String， BigDecimal， 拷贝后需要大家引入对应的jar包， alt+回车
  4. 对于sql.xml数据中的路径，目前已xxx.xxx.xxx.xxx代替，需要大家自己替换相应的路径，毕竟不是神，不知道大家的包路径啊

其他功能还在思考中，有更新会添加，希望喜欢的可以右上star一下
