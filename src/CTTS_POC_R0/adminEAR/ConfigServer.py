AdminTask.setJVMProperties('[-serverName server1 -initialHeapSize 1024]')
AdminTask.setJVMProperties('[-serverName server1 -maximumHeapSize 1580]')

classpathEntries = (
  "C:/ctts/src/CTTS_POC_R0/adminAPI/src/main/webapp/WEB-INF/resources;"
  "C:/ctts/src/CTTS_POC_R0/adminAPI/src/main/webapp/WEB-INF/resources/environments/dev;"
)

AdminTask.setJVMProperties('[-serverName server1 -classpath "' + classpathEntries + '"]')

AdminConfig.save()