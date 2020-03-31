package br.com.global.model;

public interface Model<U extends Model<U>> {

    String getId();

    void setId(String id);

}
