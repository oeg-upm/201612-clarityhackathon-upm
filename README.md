# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* datos tramites en JSON: https://www.zaragoza.es/sede/servicio/tramite.json . In order to page them, you can use something like https://www.zaragoza.es/sede/servicio/tramite.json?start=120&rows=10
* datos tramites en JSON-LD: https://www.zaragoza.es/sede/servicio/tramite.jsonld
* datos tramites en CSV (en HTML y Markdown): 
 * curl -X GET --header "Accept: text/csv" "https://www.zaragoza.es/sede/servicio/tramite?rf=html&results_only=false&rows=800"
 * curl -X GET --header "Accept: text/csv" "https://www.zaragoza.es/sede/servicio/tramite?rf=markdown&results_only=false&rows=800"
* ontologia: https://github.com/opencitydata/sector-publico-servicio
* seeAlso: https://servicemap.hel.fi/
* seeAlso: http://standards.esd.org.uk/?uri=list%2FenglishAndWelshServices&tab=details

* Obtaining "temas and subtemas" (topics and subtopics): https://www.zaragoza.es/buscador/select?q=*:*%20AND%20-tipocontenido_s:estatico%20AND%20category:Tr\u00e1mites&rows=800&fl=temas_smultiple,subtemas_smultiple
* Obtaining "dirigidoA" (agents): https://www.zaragoza.es/buscador/select?q=*:*%20AND%20-tipocontenido_s:estatico%20AND%20category:Tr\u00e1mites&rows=800&fl=dirigidoa_smultiple
* Version


### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
