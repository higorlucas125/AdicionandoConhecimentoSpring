# AdicionandoConhecimentoSpring
Aprendendo a utilizar o Spring da melhor forma possivel

## Detalhes importantes

 * [JSON](https://www.json.org/json-pt.html)


## Detalhamento de exemplos para o projeto

```bash
POST localhost:8080/medicos
content-type: application/json

{
  "nome": "João das neves",
  "email": "nevou##@hotmail.com",
  "crm": "2354",
  "telefone": "99999-9999",
  "especialidade": "ORTOPEDIA",
  "endereco": {
    "logradouro": "Rua das flores",
    "bairro": "Centro",
    "cep": "38408288",
    "numero": "125",
    "complemento": "Casa",
    "cidade": "São Paulo",
    "uf": "SP"

  }
}

### GET
GET localhost:8080/medicos?size=1


### GET ORDENACAO
GET localhost:8080/medicos?sort=crm,desc
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWRpYyIsInN1YiI6InRlc3QiLCJleHAiOjE3NDYxNjMwMjl9.n_M9ZNCiu0Fh3FxcIUUB42NEsEOV8AV2PZz-uaNhf4A

### GET PAGINADO COM PAGEBLO DEFAULT
GET localhost:8080/medicos
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWRpYyIsInN1YiI6InRlc3QiLCJleHAiOjE3NDYxNjMwMjl9.n_M9ZNCiu0Fh3FxcIUUB42NEsEOV8AV2PZz-uaNhf4A

### PUT
PUT localhost:8080/medicos
content-type: application/json

{
  "id": "3",
  "nome": "DANTINHO"
}

### PATCH STATUS
PATCH localhost:8080/medicos/3


### GET ID
GET localhost:8080/medicos/31


### POST COM ERRO
POST localhost:8080/medicos
content-type: application/json

{
  "especialidade": "ORTOPEDIA",
  "endereco": {
    "logradouro": "Rua das flores",
    "bairro": "Centro",
    "cep": "38408288",
    "numero": "125",
    "complemento": "Casa",
    "cidade": "Belo Horizonte",
    "uf": "MG"

  }
}


### LOGIN
POST localhost:8080/login
content-type: application/json

{
  "login": "test",
  "senha": "432852"
}


### Cadastro
POST localhost:8080/cadastrar
content-type: application/json

{
  "login": "test",
  "senha": "432852"
}

```