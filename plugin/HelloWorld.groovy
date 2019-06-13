//groovy 高级特性

//1 可选的类型定义
def version = 1

//2 assert
assert version == 2

//3 括号是可选
println version

//4 字符串
def s1 = 'imooc'
def s2 = "gradle verson is ${version}"
def s3 = ''' this
             is
             imooc '''

//5 集合API
//5.1 list
def buildTools = ['ant', 'maven']
buildTools << 'gradle'
//buildTools.add('gradle')

println buildTools.get(2)
println buildTools.toString()

//5.2 map
def v
def buildYears = ['ant': 2000, 'maven': 2004, v: 'version']
buildYears.gradle = 2009
println buildYears.ant
println buildYears['gradle']
println buildYears.toString()

//6 闭包
//6.1 定义闭包
def c1 = {
    ver -> print ver
}
def c2 = {
    print 'hello'
}

//6.2 使用闭包的方法
def method1(Closure closure) {
    closure('param   ')
}

def method2(Closure closure) {
    closure()
}

method1(c1)
method2 c2

//7 自定义任务

def createDir = {
    path ->
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
}

task makeJavaDir() {
    def paths = ['src/main/java', 'src/main/res', 'src/test/java', 'src/test/res']
    doFirst {
        paths.forEach(createDir)
    }
}

task makeWebDir() {
    dependsOn 'makeJavaDir'
    def paths = ['src/main/webapp', 'src/test/webapp']
    doLast {
        paths.forEach(createDir)
    }
}

//8 构建生命周期

//   1,初始化: 参与构建的project
//   2,配置: task的依赖关系和执行顺序
//   3,执行: 执行动作代码action


//9 依赖管理

//   1,工件坐标: group, name, version
//   2,常用仓库: mavenCentral, jcenter, 自定义maven仓库, 文件仓库
//   3,阶段配置: compile, runtime
//   4,仓库依赖
   repositories {
    maven {
        url ''
        }
    mavenLocal()
    mavenCentral()
    }
