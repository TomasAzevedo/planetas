# API REST Planetas

Uma REST API para incluir, consultar, alterar, listar e excluir um Planeta no servidor. A quantidade de filmes em que o planeta aparece é obtida através da API pública https://swapi.co/api/planets.

## Início

Essas instruções farão com que você tenha uma cópia do projeto em execução na sua máquina local para fins de desenvolvimento e teste. 

### Pré-requisitos

* Java 8
* Maven 4.0.0
* Spring Boot 2.1.1
* MongoDB

### Instalação

Após clonar ou fazer o download do projeto é possível rodar os testes ou startar a aplicação utilizando os comandos do Maven ou usando sua IDE favorita.

## Recursos

A documentação detalhada da API pode ser encontrada [aqui](https://app.swaggerhub.com/apis-docs/TomasAzevedo/api-rest_planetas/1.0#/planetas-controller).

| Recurso | Método | Descrição |
|:-----------|:---------------|-------------|
| `/planetas` | `POST` | Cria um planeta no servidor. |
| `/planetas` | `PUT` | Atualiza um planeta no servidor. |
| `/planetas/?nome=Nome do Planeta` | `GET` | Recupera um planeta pelo nome. |
| `/planetas/lista?page=0&size=10` | `GET` | Lista utilizando paginação os planetas do servidor. |
| `/planetas/{id}` | `GET` | Recupera um planeta pelo id. |
| `/planetas/{id}` | `DELETE` | Exclui um planeta do servidor. |

### Models

PlanetaDTO

```
{
	clima	   string
	id	   string
	nome	   string
	qtdFilmes  integer($int32)
	terreno	   string
}
```

Page<PlanetaDTO>
	
```
{
	content	          [PlanetaDTO{...}]
	empty	          boolean
	first	          boolean
	last	          boolean
	number	          integer($int32)
	numberOfElements  integer($int32)
	pageable	  Pageable {
				offset	integer($int64)
				pageNumber	integer($int32)
				pageSize	integer($int32)
				paged	boolean
				sort	Sort{...}
				unpaged	boolean
			  }
	size	          integer($int32)
	sort	          Sort {
				empty	boolean
				sorted	boolean
				unsorted	boolean
			  }
	totalElements	  integer($int64)
	totalPages	  integer($int32)
}
```
