# API Gateway

Este projeto configura uma API **SSO** que pode ser utilizado para autenticar um usuário em diversas aplicações, utilizando o mesmo token. A API é construída utilizando recursos do spring-boot e executado dentro de um container Docker. 

## Pré-requisitos

1. **Docker e Docker Compose**: Certifique-se de ter o Docker e o Docker Compose instalados no seu sistema.
   
2. **Certificados SSL**: Por questões de segurança, os certificados SSL não são versionados neste repositório. Você precisará gerar ou obter o certificado e a chave privada antes de realizar o deploy.

## Passos para Configuração

### 1. Criando o Arquivo `.env`

Na raiz do projeto, crie um arquivo chamado `.env` com as seguintes configurações:

```ini
# Configurações do Banco de Dados
DB_URL=jdbc:mysql://<host>:<port>/<database>
DB_USER=<db_user>
DB_PASS=<db_password>

# Configurações de JWT
JWT_SECRET_KEY=<jwt_secret_key>

# Configurações do domínio e proxy
ENV_DOMAIN_NAME=lucaslabs.com
VIRTUAL_HOST=api-gateway
VIRTUAL_PORT=443
VIRTUAL_DOMAIN=api-gateway.lucaslabs.com

# Configurações do certificado
KEY_STORE_PASSWORD=<key_store_password>
KEY_ALIAS_NAME=<key_alias_name>

```

- Sobre o arquivo `.env`: 
    * DB_URL: URL de conexão com o banco de dados.
    * DB_USER e DB_PASS: Credenciais para acessar o banco de dados.
    * JWT_SECRET_KEY: Chave secreta para gerar e validar tokens JWT.
    * VIRTUAL_HOST: Nome do host utilizado para o proxy reverso.
    * VIRTUAL_PORT: Porta utilizada pelo proxy reverso.
    * KEY_STORE_PASSWORD: Senha para o keystore.
    * KEY_ALIAS_NAME: Nome do alias do certificado no keystore.

### 2. **Configuração dos Certificados SSL**
Os certificados não são versionados por questões de segurança. Você precisará fornecer o certificado e a chave privada no formato keystore.p12.

- Estrutura de Pastas:
    * Crie uma pasta chamada certificados em algum diretório do seu computador. (Certifique-se de atualizar o arquivo docker-compose.yml com o diretório criado)
    * Dentro da pasta certificados, crie uma subpasta (Ex.:.lucaslabs) onde os arquivos do certificado e chave serão armazenados.
    * O caminho completo seria:
      ```ini
      caminho/da/pasta/certificados/.lucaslabs/keystore.p12
    * Gere os arquivos .`crt` e `.key` na pasta. (Para gerar os certificados, você pode utilizar o mkcert (https://github.com/FiloSottile/mkcert)
    * Gere também o arquivo `.p12` (O mkcert também faz isso)

### 4. **Build e Deploy**
Execute o comando 
```
docker-compose up -d
```

### 5. **Acessando a aplicação**
A aplicação será visível a partir de um api-gateway, reconhecido através do eureka-server configurado

### 6. Gerenciamento de Certificados e Truststore
Durante a construção da imagem, o script init.sh inclui o comando keytool para adicionar o certificado .pem ao truststore do Java. Isso garante que o API Gateway confie no certificado utilizado para SSL.
```ini
keytool -import -alias "$alias_name" -file "$pem" -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt
```


### 7. Importação inicial de data
O arquivo data.sql possui um simples insert de dados para deixar a aplicação disponível para uso. 
Usuário:
```
username: Admin
password: @dm1n
```