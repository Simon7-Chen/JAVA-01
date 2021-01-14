# 学习笔记
## 第一次课
### 1.JVM基础知识
 Java通过JVM实现跨平台。
 
 JVM通过类加载器读取Java字节码文件生成Java对象实例。

### 2.Java字节码技术
 字节码定义了在JVM中栈操作、程序流程操作、对象操作、算术运算及类型转换所需要的指令。
 
 .java文件通过javac命令编译成.class字节码文件。
 
 理解jvm如何读取字节码实现代码功能。

### 3.JVM类加载器*
 类的生命周期（7步）。
 
 类的加载时机（8种）。
 
 3类加载器：启动类加载器；拓展类加载器；应用类加载器。
 
 加载方式：双亲委派。
 
 可自定义类加载器加载特殊处理的类文件。
 
### 4.JVM内存模型*
 重点是堆、JVM栈。
 
 各个结构的组成关系。

### 5.JVM启动参数
 主要是内存分配的相关参数。
 
 jstat、jstack、jmap命令的使用。

## 第二次课
 jps、jstat、jmap、jstack、jmc工具的使用。
 
 堆内存回收的原理。
 
 理解串行GC、并行GC，Java8默认使用并行GC。
 
 CMS GC（6个阶段）。
 
 G1 GC（2048个块）。
 
 ZGC Linux JDK11/Mac、Windows JDK15。
 
 ShennandoahGC 类似ZGC。
 
 ZGC/ShennandoahGC STW最短的GC。
