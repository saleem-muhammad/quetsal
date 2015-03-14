## <font color='blue'>A Query Federation Suite for SPARQL </font> ##


QUETSAL can be checkout from [here](https://code.google.com/p/quetsal/source/checkout)
### Datasets Availability ###

All the datasets and corresponding virtuoso SPARQL endpoints can be downloaded from the links given below. For SPARQL endpoint federation systems, we strongly recommend to directly download the endpoints as some of the datadumps are quite big and require a lot of upload time. You may start a SPARQL endpoint from bin/start.bat (for windows) and bin/start\_virtuoso.sh (for linux).

| **Dataset** | **Data-dump** | **Windows Endpoint** | **Linux Endpoint** | **Local Endpoint Url**| **Live Endpoint Url**|
|:------------|:--------------|:---------------------|:-------------------|:----------------------|:---------------------|
| [ChEBI](https://www.ebi.ac.uk/chebi/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Vk81dGVkNVNuY1E/edit?usp=sharing/)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-TUR6RF9jX2xoMFU/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Wk5LeHBzMUd3VHc/edit?usp=sharing)|your.system.ip.address:8890/sparql | http://chebi.bio2rdf.org/sparql |
| [DBPedia-Subset](http://DBpedia.org/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-QWk5MVJud3cxUXM/edit?usp=sharing/)|  [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-WjNkZEZrTTZzbW8/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-OEgyXzBUVmlMQlk/edit?usp=sharing)|your.system.ip.address:8891/sparql |http://dbpedia.org/sparql |
| [DrugBank](http://www.drugbank.ca/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-cVp5QV9VUWRuYkk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-QmMyOE9RWV9oNHM/edit?usp=sharing/)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-U0V5Y0xDWXhzam8/edit?usp=sharing/)|your.system.ip.address:8892/sparql | http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql|
| [Geo Names](http://www.geonames.org/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-WEZZb2VwOG5vZkU/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-VC1HWmhBMlFncWc/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByd3hJcHBPeHZhejA/edit?usp=sharing/) |your.system.ip.address:8893/sparql | http://factforge.net/sparql|
| [Jamendo](http://dbtune.org/jamendo/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-cWpmMWxxQ3Z2eVk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-YXV6U0ZzLUF0S0k/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-V3JMZjdfRkZxLUU/edit?usp=sharing/) |your.system.ip.address:8894/sparql  | http://dbtune.org/jamendo/sparql/|
| [KEGG](http://www.genome.jp/kegg/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-TUdUcllRMGVJaHM/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-c1BNQ0dVWTVkUEU/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-R1dKbDlHNXZ6blk/edit?usp=sharing/) |your.system.ip.address:8895/sparql |http://cu.kegg.bio2rdf.org/sparql |
| [Linked MDB](http://linkedmdb.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-bU5VN25NLXZXU0U/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-eXpVSjd2Y25PaVk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-NjVTVERvajJUcGc/edit?usp=sharing/) |your.system.ip.address:8896/sparql |http://www.linkedmdb.org/sparql |
| [New York Times](http://data.nytimes.com/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-dThoTm9DSmY4Wms/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-VDhmNWJmZVcybm8/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RG9GeVdxbDR4YjQ/edit?usp=sharing/) |your.system.ip.address:8897/sparql | - |
| [Semantic Web Dog Food](http://data.semanticweb.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RjBWZXYyX2FDT1E/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-c2h4al9VREF6bDg/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-UW5HaF9rekdialU/edit?usp=sharing/) |your.system.ip.address:8898/sparql | http://data.semanticweb.org/sparql|

### SPARQL Endpoints Specification ###

The specification of the systems used in our evaluation is given below.


Note: We hosted each of the SPARQL endpoint on a separate physical machine.

| **Endpoint.** | **CPU** | **RAM** | **Hardisk** |
|:--------------|:--------|:--------|:------------|
|1. ChEBI-virtuoso1|   2.2, i3 |      4GB |   300 GB |
|2. DrugBank-virtuoso2|   2.2, i3 |      4GB |   300 GB |
|3. Jamendo-virtuoso3|	2.6, i5	  | 4 GB  |	150 GB |
|4. KEGG-Virtuoso4|	2.53, i5  | 4 GB|	300 GB |
|5. NYT (New York Times|	2.3, i5	  | 4 GB|	500 GB |
|6. LinkedMDB-virtuoso6|	2.53, i5  | 4 GB|	300 GB |
|7. SWDF (Semantic Web Dog Food)-virtuoso7|	2.9, i7	  | 8 GB|	450 GB |
|8. GeoNames-Virtuoso8|	 2.9, i7	|   16 GB |	500 GB  |
|9. DbpediaSubset-virtuoso9|	 2.9, i7	|   16 GB |	500 GB  |

### Complete Results and Runtime Errors ###
Our complete evaluation results can be found [here](https://drive.google.com/file/d/0B1tUDhWNTjO-bU5vVUszbEtpYWc/view?usp=sharing) and the runtime errors can be found [here](http://goo.gl/nluPBg)