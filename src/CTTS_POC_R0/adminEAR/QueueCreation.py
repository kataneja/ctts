resourceAdampterId = AdminConfig.getid("/Server:server1/J2CResourceAdapter:SIB JMS Resource Adapter" )
node = AdminControl.getNode()
serverId = AdminConfig.getid("/Server:server1" )

server = AdminControl.queryNames('node='+AdminControl.getNode( )+',type=Server,*') 
serverName = AdminControl.getAttribute(server, "name") 
nodeName = AdminControl.getNode( ) 
cellName = AdminControl.getCell( ) 
scopeConfig = 'server(cells/'+cellName+'/nodes/'+nodeName+'/servers/'+serverName+'|server.xml)'

AdminTask.createSIBus('[-bus appBus -description [ctts Bus] -busSecurity false ]')
AdminTask.addSIBusMember('[-bus appBus -server server1 -node ' + node + '  ]')

AdminTask.createSIBJMSConnectionFactory (scopeConfig,'[ -name ctts_F -jndiName jms/ctts_F -busName appBus -type queue ]')
AdminTask.createSIBDestination('[-server server1 -node ' + node + ' -bus appBus -name ctts_Dest -type QUEUE ]')
AdminTask.createSIBJMSQueue(serverId, '[-name ctts_Q -jndiName jms/ctts_Q -busName appBus -queueName ctts_Dest]')
AdminTask.createSIBJMSActivationSpec(serverId, '[ -name cttsActivationSpec -jndiName jms/cttsActivationSpec -destinationJndiName jms/ctts_Q -busName appBus]')

AdminTask.createSIBJMSConnectionFactory (scopeConfig,'[ -name audit_F -jndiName jms/audit_F -busName appBus -type queue ]')
AdminTask.createSIBDestination('[-server server1 -node ' + node + ' -bus appBus -name audit_Dest -type QUEUE ]')
AdminTask.createSIBJMSQueue(serverId, '[-name audit_Q -jndiName jms/audit_Q -busName appBus -queueName audit_Dest]')
AdminTask.createSIBJMSActivationSpec(serverId, '[ -name auditActivationSpec -jndiName jms/auditActivationSpec -destinationJndiName jms/audit_Q -busName appBus]')

AdminConfig.save()