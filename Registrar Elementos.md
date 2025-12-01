Registar:

curl -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" -d "{\\"name\\":\\"Orlando\\",\\"email\\":\\"orlando@email.com\\",\\"password\\":\\"123456\\",\\"role\\":\\"USER\\",\\"plan\\":\\"BASIC\\"}"



Login:

curl -X POST "http://localhost:8080/api/auth/login" -H "Content-Type: application/json" -d "{\\"email\\":\\"orlando@email.com\\",\\"password\\":\\"123456\\"}"



Get Login:

curl -X GET "http://localhost:8080/api/users" -H "Authorization: Bearer **REFRESH\_TOKEN**"



Criar link:

curl -X POST "http://localhost:8080/api/links/user/**USERID**" -H "Content-Type: application/json" -d "{\\"originalUrl\\":\\"https://google.com\\",\\"groupId\\":null}"



Criar Click:

curl -X POST "http://localhost:8080/links/**LINKID**/clicks" -H "Content-Type: application/json" -d "{\\"date\\":\\"2025-11-30\\",\\"clickedAt\\":\\"2025-11-30T12:00:00\\",\\"region\\":\\"SP\\",\\"city\\":\\"São Paulo\\",\\"device\\":\\"PC\\",\\"referer\\":\\"Google\\"}"



Listar link:

curl -X GET "http://localhost:8080/links/**LINKID**/clicks"



Criar histórico:

curl -X POST "http://localhost:8080/api/movement-history" -H "Content-Type: application/json" -d "{\\"linkId\\":\\"**LINKID**\\",\\"movementDate\\":\\"2025-11-30\\",\\"totalClicks\\":1,\\"action\\":\\"UPDATE\\",\\"description\\":\\"Teste\\",\\"userName\\":\\"Orlando\\"}"





Listar Hisotrico:

curl -X GET "http://localhost:8080/api/movement-history"



Criar  Grupo:

curl -X POST "http://localhost:8080/groups" -H "Content-Type: application/json" -d "{\\"name\\":\\"Meu Grupo\\"}"



Criar Membro:

curl -X POST "http://localhost:8080/group-members" -H "Content-Type: application/json" -d "{\\"userId\\":\\"**USERID**\\",\\"groupId\\":\\"**GROUPID**\\"}"



Listar membros:

curl -X GET http://localhost:8080/group-members



