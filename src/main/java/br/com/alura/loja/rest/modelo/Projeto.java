package br.com.alura.loja.rest.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by apq on 03/03/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

    private String nome;
    private long id;
    private int anoDeInicio;

    public Projeto(){}

    public Projeto(long id,String nome,  int anoDeInicio) {
        this.nome = nome;
        this.id = id;
        this.anoDeInicio = anoDeInicio;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toXml(){
        return new XStream().toXML(this);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
