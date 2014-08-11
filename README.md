# ProtocolBuffer to JSON
[ProtocolBuffers](https://developers.google.com/protocol-buffers/docs/overview) are an excellent way for sub systems to communicate. 

### Google's protocol buffer documentation 
"Protocol buffers are a flexible, efficient, automated mechanism for serializing structured data – think XML, but smaller, faster, and simpler. You define how you want your data to be structured once, then you can use special generated source code to easily write and read your structured data to and from a variety of data streams and using a variety of languages. You can even update your data structure without breaking deployed programs that are compiled against the "old" format."

Since Protocol buffers are primarly meant for internal RPC communication (binary); the base implementation doesnt provide a way to stream the Message as a text stream which is commonly used when dealing with web applications.

This project distribution allows one to convert a message to its JSON representation. 

The simplest way to use this project is 

```java
    import thoughtwok.protobuf.to.json.ProtoBufToJson;
    
    ....
    ...
    ....
    
    String jsonString = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
```