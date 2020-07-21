package com.reactnebula.simplejsonutil;

import java.util.ArrayList;

/**
 *
 * @author Charles
 */
public class JsonObjectArray extends JsonData {
    
    ArrayList<JsonObject> jObjects = new ArrayList<>();
    String indent = "    ";
    
    public JsonObjectArray(String name) {
        sb.append('"').append(name).append('"');
        sb.append(":");
        sb.append("[\n");
    }
    
    public JsonObject putObject(String name) {
        JsonObject jo = new JsonObject(name);
        jo.sb.insert(0, indent);
        jo.startLength+=indent.length();
        jo.indent+=indent;
        jObjects.add(jo);
        return jo;
    }
    
    public void putObject(JsonObject jo) {
        jo.sb.insert(0, indent);
        jo.startLength+=indent.length();
        jo.indent+=indent;
        jObjects.add(jo);
    }
    
    @Override
    String write() {
        jObjects.forEach(jo -> {
            if(jo == jObjects.get(jObjects.size()-1)) 
                sb.append(jo.writeLast());
            else
                sb.append(jo.write());
        });
        sb.append("],\n");
        return sb.toString();
    }
    
    @Override
    String writeLast() {
        jObjects.forEach(jo -> {
            if(jo == jObjects.get(jObjects.size()-1)) 
                sb.append(jo.writeLast());
                 else
                sb.append(jo.write());
        });
        sb.append("]\n");
        return sb.toString();
    }
}
