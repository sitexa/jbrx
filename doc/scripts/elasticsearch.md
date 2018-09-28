#Elasticsearch 

ref: https://cuiqingcai.com/6214.html

##  installation & start

```
/home/xnpeng/Application/elasticsearch-6.4.1

./bin/elasticsearch 

```

``` curl http://localhost：9200/```

``` 
{
  "name" : "KWAbd0Y",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "UZJKIwptSSKgTlJkLQMCAA",
  "version" : {
    "number" : "6.4.1",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "e36acdb",
    "build_date" : "2018-09-13T22:18:07.696808Z",
    "build_snapshot" : false,
    "lucene_version" : "7.4.0",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
  },
  "tagline" : "You Know, for Search"
}
```

##  Elasticsearch 相关概念

### Node 和 Cluster

### Index

### Document

### Type

### Fields

``` 
Elasticsearch -> Indices   -> Types  -> Documents -> Fields
```

##  kibana

Kibana是一个开源的分析和可视化平台，旨在与 Elasticsearch 合作。Kibana 提供搜索、查看和与存储在 Elasticsearch 索引中的数据进行交互的功能。开发者或运维人员可以轻松地执行高级数据分析，并在各种图表、表格和地图中可视化数据。

/home/xnpeng/Application/kibana-6.4.1-linux-x86_64/

```vi config/kibana.conf```
elasticsearch.url: "http://localhost:9200"

##  分词器

``` 
./bin/elasticsearch-plugin install file:/home/xnpeng/Download/elasticsearch-analysis-ik-6.4.1.zip

```

分词测试：

``` 
curl -XGET -H 'Content-Type:application/json' 'http://localhost:9200/_analyze?pretty' -d '{"analyzer":"ik_max_word","text":"中华人民共和国"}'
```
结果：
``` 
{
  "tokens" : [
    {
      "token" : "中华人民共和国",
      "start_offset" : 0,
      "end_offset" : 7,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "中华人民",
      "start_offset" : 0,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "中华",
      "start_offset" : 0,
      "end_offset" : 2,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "华人",
      "start_offset" : 1,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 3
    },
    {
      "token" : "人民共和国",
      "start_offset" : 2,
      "end_offset" : 7,
      "type" : "CN_WORD",
      "position" : 4
    },
    {
      "token" : "人民",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 5
    },
    {
      "token" : "共和国",
      "start_offset" : 4,
      "end_offset" : 7,
      "type" : "CN_WORD",
      "position" : 6
    },
    {
      "token" : "共和",
      "start_offset" : 4,
      "end_offset" : 6,
      "type" : "CN_WORD",
      "position" : 7
    },
    {
      "token" : "国",
      "start_offset" : 6,
      "end_offset" : 7,
      "type" : "CN_CHAR",
      "position" : 8
    }
  ]
}

```