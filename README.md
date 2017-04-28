# StaticMapServlet - Version 0.1.1
Generate StaticMap from JAVA EE Servers (Tomcat, Jetty, JBoss, Weblogic, ...)

## Maven Build
mvn package

## Deployment
Use JAVA Admin console to deploy new WAR file or copy WAR file to your Servers's deploy folder

## Example of use
http://{javaEEserver}:{javaEEport}/StaticMapServlet-{version}/servlet/StaticMapServlet?apikey={yourThunderforetApiKey}&address={addressLabel}&coord={latitude},{longitude}&maptype={cycle|transport|landscape|outdoors|transport-dark|spinal-map|pioneer|mobile-atlas|neighbourhood}&zoom={1->16}&size={widthInPixels}x{heightInPixels}

http://{javaEEserver}:{javaEEport}/StaticMapServlet-{version}/servlet/StaticMapServlet?apikey={yourThunderforetApiKey}&address=9%20rue%20Jeanne%20Braconnier%2092360%20MEUDON&coord=48.7897645,2.2117242&maptype=cycle&zoom=14&size=512x512

