# liferay-camunda-acm

**Liferay 6.2 Portlets to show and interact with Camunda cases**

*Requires the Shindig server's secret token. (ACM Stakeholders Portlet)*

**Portlets included**

* ACM Case Overview
* ACM Instance Activities
* ACM Instance Stakeholders

**Installation**

Configuration: /docroot/WEB-INF/src/portlet.properties

Build .war:

1. Project has to be placed in a folder called "CamundaACM-portlet" in the *portlets* folder of a Liferay Plugins SDK.
2. Import in Liferay IDE using "Liferay project from existing source"
3. Edit configuration file.
4. Right click on project and execute > Liferay > SDK > war

Deploy:

4. Put generated war in Liferay's "deploy" folder
5. Optionally restart Liferay