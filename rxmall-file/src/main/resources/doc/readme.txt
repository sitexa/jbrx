mvn install:install-file -Dfile=fastdfs-client-java-1.27.0.0.jar -DgroupId=com.fastdfs -DartifactId=fastdfs -Dversion=1.27.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=tfs-javaclient-2.1.1.jar -DgroupId=com.tfs -DartifactId=tfs -Dversion=2.1.1 -Dpackaging=jar
		<dependency>
			<groupId>com.fastdfs</groupId>
			<artifactId>fastdfs</artifactId>
			<version>1.27.0.0</version>
		</dependency>	
		<dependency>
			<groupId>com.tfs</groupId>
			<artifactId>tfs</artifactId>
			<version>2.1.1</version>
		</dependency>	

1.部署将wfile.zip放在tomcat下
2.执行wfile.sql
3.配置存储方式config.properties
4.修改数据库配置dbbus_pro.xml


tfs
http://code.taobao.org/svn/tfs-client-java/tags/release-2.2.6/



--------------------------------------------------------------------------------------------------------------------------------------
1.
strFileDomain=http://localhost:80/
strLocalFilePath=/

2.tomcat配置访问路径
<Context path="/file" docBase="/file/" debug="0" privileged="true"></Context>

3.在根路径下创建文件夹file