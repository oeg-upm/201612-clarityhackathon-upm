# README #

### What is this repository for? ###

This repository puts together all the work done during the CLARITY project Sprint Week in December 2016 on the work proposed by the Zaragoza city council on the usage of their open dataset (and API) about public services. Our aim has been to generate the artifacts that will be required in the future to start working on the homogeneisation of the lists of public services that are provided by municipalities in Spain.

### Input material ###

We have started with the open material that is made available by the Zaragoza city council in relation to the public services that they offer, namely:

* JSON data about their public services: https://www.zaragoza.es/sede/servicio/tramite.json 
 * Such a call does not give access to all the data, but only to 50 rows. In order to page them, you can use something like https://www.zaragoza.es/sede/servicio/tramite.json?start=120&rows=10, or specify directly that the number of rows should be equal to 800 (since they have less than 800 services available)
* JSON-LD data about their public services (this work will be improving in the future, thanks to the work done over this sprint): https://www.zaragoza.es/sede/servicio/tramite.jsonld
* CSV data about their public services (in HTML and Markdown): 
 * curl -X GET --header "Accept: text/csv" "https://www.zaragoza.es/sede/servicio/tramite?rf=html&results_only=false&rows=800"
 * curl -X GET --header "Accept: text/csv" "https://www.zaragoza.es/sede/servicio/tramite?rf=markdown&results_only=false&rows=800&fl=id,title,descabre" (not that this one does not seem to be working right now - this has been reported to the corresponding people, see https://github.com/zaragoza-sedeelectronica/zaragoza-sedeelectronica.github.io/issues/46)
* XML files obtained from the SOLR service
 * Obtaining "temas and subtemas" (topics and subtopics): https://www.zaragoza.es/buscador/select?q=*:*%20AND%20-tipocontenido_s:estatico%20AND%20category:Tr\u00e1mites&rows=800&fl=temas_smultiple,subtemas_smultiple
 * Obtaining "dirigidoA" (agents): https://www.zaragoza.es/buscador/select?q=*:*%20AND%20-tipocontenido_s:estatico%20AND%20category:Tr\u00e1mites&rows=800&fl=dirigidoa_smultiple


Some initial inspirational examples are also provided here:
* Service Map in Helsinki: https://servicemap.hel.fi/
* Codelists, including a list of Local Government public services, in the United Kingdom: http://standards.esd.org.uk/?uri=list%2FenglishAndWelshServices&tab=details


### Generated material ###
* CPSV-AP Ontology formalised in OWL and translated into Spanish: 
  * Documentation: https://rawgit.com/opencitydata/sector-publico-servicio/master/OnToology/cpsvap.owl/documentation/index-en.html
  * Ontology evaluation results: https://rawgit.com/opencitydata/sector-publico-servicio/master/OnToology/cpsvap.owl/evaluation/oopsEval.html
  * https://github.com/opencitydata/sector-publico-servicio
* Several SKOS thesauri have been generated (they will be maintained at the https://github.com/opencitydata/sector-publico-servicio repository
 * http://vocab.linkeddata.es/page/datosabiertos/kos/sector-publico/servicio/tipo-agente 
 * http://vocab.linkeddata.es/datosabiertos/kos/sector-publico/servicio/tipo-servicio 
 * http://vocab.linkeddata.es/page/datosabiertos/kos/sector-publico/servicio 
