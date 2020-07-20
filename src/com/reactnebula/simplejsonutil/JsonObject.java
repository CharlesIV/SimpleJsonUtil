package com.reactnebula.simplejsonutil;

import java.util.HashMap;

/**
 *
 * @author Charles
 */
public class JsonObject extends JsonValue {
    
    int startLength, lastPos, count;
    String indent = "    ";
    
    HashMap<Integer, JsonObject> jObjects = new HashMap<>();
    
    public JsonObject(String name) {
        sb.append('"').append(name).append('"').append(":");
        sb.append("{\n");
        startLength = sb.length();
    }
    
    @Override
    public void put(String name, double number) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, number);
    }
    
    @Override
    public void put(String name, String string) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, string);
        
    }
    
    @Override
    public void put(String name, boolean bool) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, bool);
    }
    
    @Override
    public void put(String name, char character) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, character);
    }
    
    @Override
    public void put(String name) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name);
    }
    
    @Override
    public void put(String name, double[] numbers) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, numbers);
    }
    
    @Override
    public void put(String name, String[] strings) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, strings);
    }
    
    @Override
    public void put(String name, boolean[] bools) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, bools);
    }
    
    @Override
    public void put(String name, char[] characters) {
        if(sb.length() > startLength)
            super.appendEnding();
        sb.append(indent);
        super.put(name, characters);
    }
    
    public JsonObject putObject(String name) {
        JsonObject jo = new JsonObject(name);
        jo.sb.insert(0, indent);
        jo.indent = indent + "    ";
        jObjects.put(sb.length()+count++, jo);
        jo.startLength+=indent.length();
        return jo;
    }
    
    @Override
    String writeLast() {
        write();
        sb.deleteCharAt(sb.length()-2);
        return sb.toString();
    }
    
    @Override
    String write() {
        sb.append("\n").append(indent.substring(4, indent.length())).append("},\n");
        lastPos = 0;
        jObjects.forEach((i, jo) -> {
            int ii = i + lastPos--;
            if(sb.charAt(ii)!=',')
                sb.insert(ii, ",");
            sb.insert(ii+2, jo.write());
            if(sb.charAt(ii+jo.sb.length()+indent.length()-2) == '}') 
                sb.deleteCharAt(ii+jo.sb.length());
            lastPos += jo.sb.length();
        });
        return sb.toString();
    }
    
    @Override
    protected void appendEnding() {};
}
