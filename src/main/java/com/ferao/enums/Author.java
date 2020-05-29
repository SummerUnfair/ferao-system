package com.ferao.enums;/*
 * @author Ferao
 * @date
 * @discription
 */

public enum  Author {

    AUTHOR("1","ferao","https://blog.csdn.net//qq_21561501");
    private String code;
    private String name;
    private String description;

    /**
     * @param code 编号
     * @param name  名称
     * @param description   描述
     */
    Author(String code,String name, String description) {
        this.code=code;
        this.name = name;
        this.description = description;
    }
    public String getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public static Author getAuthor(String code){

        Author[] values = Author.values();
        for (Author author : values) {
            if (author.getCode().equals(code)){
                return author;
            }
        }
        return null;
    }
}
