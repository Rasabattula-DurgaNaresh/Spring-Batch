
Spring Batch Example – XML File To MongoDB Database
----------------------------------------------------
In this tutorial, we will show you how to configure a Spring Batch job to read data from an XML file (XStream library) into a no SQL database (MongoDB). In additional, create a unit test case to launch and test the batch jobs.

Tools and libraries used
-----------------------
-Maven 3.3.x
- Eclipse 4.2 or STS
- JDK 1.8
- Spring Core 4.2.4.RELEASE
- Spring Batch 3.0.6.RELEASE
- Spring Batch Test 3.0.6.RELEASE
- Spring OXM 4.2.4.RELEASE
- MongoDB Java Driver 3.1.1
- MongoDB 2.2.3
- jUnit 4.12
- TestNG 6.8.5
P.S This example – XML file (reader) – MongoDB (writer).


```sh
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:batch="http://www.springframework.org/schema/batch" xmlns:task="http://www.springframework.org/schema/task"
	xmlns:util="http://www.springframework.org/schema/util" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/batch
		http://www.springframework.org/schema/batch/spring-batch-2.2.xsd
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/util 
		http://www.springframework.org/schema/util/spring-util-3.2.xsd">

	<batch:job id="reportJob">
		<batch:step id="step1">
			<batch:tasklet>
				<batch:chunk reader="xmlItemReader" writer="mongodbItemWriter" commit-interval="1">
				</batch:chunk>
			</batch:tasklet>
		</batch:step>
	</batch:job>

	<bean id="mongodbItemWriter" class="org.springframework.batch.item.data.MongoItemWriter">
		<property name="template" ref="mongoTemplate" />
		<property name="collection" value="report" />
	</bean>
	
	<bean id="xmlItemReader" class="org.springframework.batch.item.xml.StaxEventItemReader">
		<property name="fragmentRootElementName" value="record" />
		<property name="resource" value="classpath:xml/report.xml" />
		<property name="unmarshaller" ref="reportUnmarshaller" />
	</bean>

	<bean id="reportUnmarshaller" class="org.springframework.oxm.xstream.XStreamMarshaller">

		<property name="aliases">
			<util:map id="aliases">
				<entry key="record" value="com.mkyong.model.Report" />
				<!-- 
				<entry key="date" value="java.lang.String" />
				<entry key="impression" value="java.lang.Long" />
				<entry key="clicks" value="java.lang.Integer" />
				<entry key="earning" value="java.math.BigDecimal" />
				 -->
			</util:map>
		</property>

		<property name="converters">
			<array>
				<ref bean="reportConverter" />
			</array>
		</property>
	</bean>

	<bean id="reportConverter" class="com.mkyong.converter.ReportConverter" />
</beans>
```